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

	@GetMapping("/{email}")
	public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) {
		UserEntity findByID;
		try {
			findByID = userService.findByEmail(email);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findByID);
	}

	@PostMapping
	public ResponseEntity<UserEntity> add(@Valid @RequestBody UserEntity notification) throws Exception {
		UserEntity add = userService.add(notification);
		return ResponseEntity.ok(add);
	}

	@PutMapping("/{email}")
	public ResponseEntity<UserEntity> update(@PathVariable String email, @RequestBody UserEntity user) throws Exception{
		UserEntity update;
		try {
			update = userService.update(email, user);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<UserEntity> delete(@PathVariable String email){
		UserEntity delete;
		try {
			delete = userService.delete(email);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(delete);
	}

}
