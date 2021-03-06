package unicalApplication.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.enums.Category;
import unicalApplication.models.Event;
import unicalApplication.models.Notification;
import unicalApplication.models.UserEntity;
import unicalApplication.repositories.INotificationDAO;
import unicalApplication.repositories.IUserDAO;

@Transactional
@Service
public class NotificationService {

	@Autowired
	INotificationDAO notificationDAO;
	@Autowired
	IUserDAO userDao;

	public List<Notification> findByEmail(String email) throws NotFoundException {
		Optional<UserEntity> findByEmail = userDao.findByEmail(email);
		List<Notification> findByUser = notificationDAO.findByUser(findByEmail.get());
		return findByUser;
	}

	public List<Notification> findByCategory(String email, Category category) {
		Optional<UserEntity> findByEmail = userDao.findByEmail(email);
		List<Notification> findByUser = notificationDAO.findByUser(findByEmail.get());
		List<Notification> filterByCategory = findByUser.stream()
				.filter((item) -> item.getCategory() != null && item.getCategory().equals(category))
				.collect(Collectors.toList());
		return filterByCategory;
	}

	public List<Notification> getAll() throws NotFoundException {
		List<Notification> all = notificationDAO.findAll();
		if (all.isEmpty()) {
			throw new NotFoundException("Nenhuma notificação encontrada");
		}
		return all;
	}

	public Notification add(Notification Notification) {
		return notificationDAO.save(Notification);
	}

	public Notification findByID(Long id) throws NotFoundException {
		Optional<Notification> Notification = notificationDAO.findById(id);
		if (!Notification.isPresent())
			throw new NotFoundException("Notificationo não encontrado");
		return Notification.get();
	}

	public Notification delete( String email, Long id) throws NotFoundException {
		Optional<UserEntity> findByEmail = userDao.findByEmail(email);
		Notification Notification = this.findByID(id);
		findByEmail.get().getNotifications().remove(Notification);
		userDao.save(findByEmail.get());
		notificationDAO.delete(Notification);
		return Notification;
	}

	public Notification update(long id, Notification notification) throws NotFoundException {
		Notification notificationInDb = this.findByID(id);
		if (notification.getEvent() != null)
			notificationInDb.setEvent(notification.getEvent());
		if (notification.getTitle() != null)
			notificationInDb.setTitle(notification.getTitle());
		if (notification.isVisualized())
			notificationInDb.setVisualized(true);
		if (notification.getCategory() != null)
			notificationInDb.setCategory(notification.getCategory());

		Notification save = notificationDAO.save(notificationInDb);
		return save;
	}
}
