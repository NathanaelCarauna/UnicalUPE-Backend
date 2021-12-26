package unicalApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.models.Event;
import unicalApplication.models.Notification;
import unicalApplication.models.UserEntity;
import unicalApplication.repositories.INotificationDAO;
import unicalApplication.repositories.IUserDAO;

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

	public List<Notification> getAll() throws NotFoundException {
		List<Notification> all = notificationDAO.findAll();
		if(all.isEmpty()) {
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

	public Notification delete(Long id) throws NotFoundException {
		Notification Notification = this.findByID(id);
		notificationDAO.delete(Notification);
		return Notification;
	}

	public Notification update(long id, Notification notification) throws NotFoundException {
		Notification notificationInDb = this.findByID(id);
		if (notification.getEvent() != null)
			notificationInDb.setEvent(notification.getEvent());
		if (notification.getTitle() != null)
			notificationInDb.setTitle(notification.getTitle());
		if(notification.isVisualized())
			notificationInDb.setVisualized(true);
		if(notification.getDescription()!= null)
			notificationInDb.setDescription(notification.getDescription());

		Notification save = notificationDAO.save(notificationInDb);
		return save;
	}
}
