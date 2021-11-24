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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import unicalApplication.enums.Category;
import unicalApplication.models.Event;
import unicalApplication.models.Notification;
import unicalApplication.models.UserEntity;
import unicalApplication.services.EventService;
import unicalApplication.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	NotificationService notificationService;

	@GetMapping
	public ResponseEntity<List<Notification>> findAll(){
		List<Notification> all;
		try {
			all = notificationService.getAll();
		} catch (NotFoundException e) {			
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(all);
	}

	@GetMapping("/findByUser/{category}")
	public ResponseEntity<List<Notification>> findbYUser(@PathVariable UserEntity user){
		List<Notification> byUser;
		try {
			byUser = notificationService.findByUser(user);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(byUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Notification> findById(@PathVariable long id) {
		Notification findByID;
		try {
			findByID = notificationService.findByID(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findByID);
	}

	@PostMapping
	public ResponseEntity<Notification> add(@Valid Notification notification) {
		Notification add = notificationService.add(notification);
		return ResponseEntity.ok(add);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Notification> update(@PathVariable long id, Notification notification){
		Notification update;
		try {
			update = notificationService.update(id, notification);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Notification> delete(@PathVariable long id){
		Notification delete;
		try {
			delete = notificationService.delete(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(delete);
	}

}
