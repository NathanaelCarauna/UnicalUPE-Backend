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
import unicalApplication.models.Course;
import unicalApplication.services.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> findAll(){
		List<Course> all;
		try {
			all = courseService.getAll();
		} catch (NotFoundException e) {			
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(all);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@PathVariable long id) {
		Course findByID;
		try {
			findByID = courseService.findByID(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findByID);
	}

	@PostMapping
	public ResponseEntity<Course> add(@Valid Course notification) {
		Course add = courseService.add(notification);
		return ResponseEntity.ok(add);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable long id, Course course){
		Course update;
		try {
			update = courseService.update(id, course);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Course> delete(@PathVariable long id){
		Course delete;
		try {
			delete = courseService.delete(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(delete);
	}

}
