package unicalApplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import unicalApplication.enums.Category;
import unicalApplication.models.Event;
import unicalApplication.services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventService;

	@GetMapping
	public ResponseEntity<List<Event>> findAll(){
		List<Event> all;
		try {
			all = eventService.getAll();
		} catch (NotFoundException e) {			
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(all);
	}

	@GetMapping("/findbycategory/{category}")
	public ResponseEntity<List<Event>> findByCategory(@PathVariable Category category){
		List<Event> byCategory;
		try {
			byCategory = eventService.findByCategory(category);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(byCategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Event> findById(@PathVariable long id) {
		Event findByID;
		try {
			findByID = eventService.findByID(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findByID);
	}

	@PostMapping
	public ResponseEntity<Event> add(@Valid @RequestBody Event event) {
		Event add = eventService.add(event);
		return ResponseEntity.ok(add);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Event> update(@PathVariable long id, @RequestBody Event event){
		Event update;
		try {
			update = eventService.update(id, event);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Event> delete(@PathVariable long id){
		Event delete;
		try {
			delete = eventService.delete(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(delete);
	}

}
