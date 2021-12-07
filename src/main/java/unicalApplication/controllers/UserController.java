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
import unicalApplication.models.UserEntity;
import unicalApplication.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserEntity>> findAll(){
		List<UserEntity> all;
		try {
			all = userService.getAll();
		} catch (NotFoundException e) {			
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(all);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable long id) {
		UserEntity findByID;
		try {
			findByID = userService.findByID(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findByID);
	}

	@PostMapping
	public ResponseEntity<UserEntity> add(@Valid UserEntity notification) {
		UserEntity add = userService.add(notification);
		return ResponseEntity.ok(add);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> update(@PathVariable long id, UserEntity course){
		UserEntity update;
		try {
			update = userService.update(id, course);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserEntity> delete(@PathVariable long id){
		UserEntity delete;
		try {
			delete = userService.delete(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(delete);
	}

}
