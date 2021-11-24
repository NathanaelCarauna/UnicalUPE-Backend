package unicalApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javassist.NotFoundException;
import unicalApplication.enums.Category;
import unicalApplication.models.Event;
import unicalApplication.repositories.IEventDAO;

@Service
public class EventService {

	@Autowired
	IEventDAO eventDAO;

	public List<Event> findByCategory(Category category) throws NotFoundException {
		List<Event> findByCategory = eventDAO.findByCategory(category);
		return findByCategory;
	}

	public List<Event> getAll() {
		return eventDAO.findAll();
	}

	public Event add(Event event) {
		return eventDAO.save(event);
	}

	public Event update(Long id, Event event) throws NotFoundException {
		Event eventInDB = this.findByID(id);
		if (event.getCategory() != null)
			eventInDB.setCategory(event.getCategory());
		if (event.getEndDateTime() != null)
			eventInDB.setEndDateTime(event.getEndDateTime());
		if (event.getStartDateTime() != null)
			eventInDB.setStartDateTime(event.getStartDateTime());
		if (event.getPalestrante() != null)
			eventInDB.setPalestrante(event.getPalestrante());
		if (event.getTitle() != null)
			eventInDB.setTitle(event.getTitle());

		return eventDAO.save(eventInDB);
	}

	public Event findByID(Long id) throws NotFoundException {
		Optional<Event> event = eventDAO.findById(id);
		if (event.isEmpty())
			throw new NotFoundException("Evento não encontrado");
		return event.get();
	}

	public Event delete(Long id) throws NotFoundException {
		Event event = this.findByID(id);
		eventDAO.delete(event);
		return event;
	}
}
