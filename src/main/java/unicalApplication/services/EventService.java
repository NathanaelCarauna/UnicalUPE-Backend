package unicalApplication.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.enums.Category;
import unicalApplication.models.Course;
import unicalApplication.models.Event;
import unicalApplication.models.Notification;
import unicalApplication.models.UserEntity;
import unicalApplication.repositories.ICourseDAO;
import unicalApplication.repositories.IEventDAO;
import unicalApplication.repositories.INotificationDAO;
import unicalApplication.repositories.IUserDAO;

@Service
public class EventService {

	@Autowired
	IEventDAO eventDAO;
	@Autowired
	ICourseDAO courseDAO;
	@Autowired
	IUserDAO userDAO;
	@Autowired
	INotificationDAO notificationDAO;

	public List<Event> findByCategory(Category category) throws NotFoundException {
		List<Event> findByCategory = eventDAO.findByCategory(category);
		return findByCategory;
	}

	public List<Event> findByCourse(long courseId) {
		Optional<Course> course = courseDAO.findById(courseId);
		List<Event> findByCourse = eventDAO.findByCourse(course.get());
		return findByCourse;
	}

	public List<Event> findByDate(String date) throws NotFoundException {
		List<Event> findByDate = eventDAO.findByStartDate(date);
		return findByDate;
	}

	public List<Event> getAll() throws NotFoundException {
		List<Event> all = eventDAO.findAll();
		if (all.isEmpty()) {
			throw new NotFoundException("Nenhum evento encontrado");
		}
		return all;
	}

	public Event add(Event event) {
		if (event.getCourse() != null) {
			List<UserEntity> findByCourse = userDAO.findByCourse(event.getCourse());
			Event eventInDB = eventDAO.save(event);
			for (int i = 0; i < findByCourse.size(); i++) {
				Notification notification = new Notification();
				notification.setTitle(eventInDB.getTitle());
				notification.setCreationTime(new Date());
				notification.setCategory(eventInDB.getCategory());
				notification.setVisualized(false);
				notification.setUser(findByCourse.get(i));
				findByCourse.get(i).getNotifications().add(notification);
				notification.setEvent(eventInDB);
				notificationDAO.save(notification);
			}
			userDAO.saveAll(findByCourse);
		}
		return eventDAO.save(event);
	}

	public Event update(Long id, Event event) throws NotFoundException {
		Event eventInDB = this.findByID(id);
		if (event.getCourse() != null)
			eventInDB.setCourse(courseDAO.getById(event.getCourse().getId()));
		if (event.getCategory() != null)
			eventInDB.setCategory(event.getCategory());
		if (event.getLocal() != null)
			eventInDB.setLocal(event.getLocal());
		if (event.getEndDate() != null)
			eventInDB.setEndDate(event.getEndDate());
		if (event.getEndHour() != null)
			eventInDB.setEndHour(event.getEndHour());
		if (event.getStartDate() != null)
			eventInDB.setStartDate(event.getStartDate());
		if (event.getStartHour() != null)
			eventInDB.setStartHour(event.getStartHour());
		if (event.getPresentor() != null)
			eventInDB.setPresentor(event.getPresentor());
		if (event.getTitle() != null)
			eventInDB.setTitle(event.getTitle());
		if (event.getDescription() != null)
			eventInDB.setDescription(event.getDescription());
		if (event.getLink() != null)
			eventInDB.setLink(event.getLink());

		Event save = eventDAO.save(eventInDB);
		return save;
	}

	public Event findByID(Long id) throws NotFoundException {
		Optional<Event> event = eventDAO.findById(id);
		if (!event.isPresent())
			throw new NotFoundException("Evento não encontrado");
		return event.get();
	}

	public Event delete(Long id) throws NotFoundException {
		Event event = this.findByID(id);
		eventDAO.delete(event);
		return event;
	}

}
