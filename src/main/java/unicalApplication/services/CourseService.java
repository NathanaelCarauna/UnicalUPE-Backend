package unicalApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.models.Course;
import unicalApplication.repositories.ICourseDAO;

@Service
public class CourseService {

	@Autowired
	ICourseDAO courseDAO;

	public List<Course> getAll() throws NotFoundException {
		List<Course> all = courseDAO.findAll();
		if(all.isEmpty()) {
			throw new NotFoundException("Nenhuma notificação encontrada");
		}
		return all;
	}

	public Course add(Course Course) {
		return courseDAO.save(Course);
	}

	public Course findByID(Long id) throws NotFoundException {
		Optional<Course> Course = courseDAO.findById(id);
		if (!Course.isPresent())
			throw new NotFoundException("Course não encontrado");
		return Course.get();
	}

	public Course delete(Long id) throws NotFoundException {
		Course Course = this.findByID(id);
		courseDAO.delete(Course);
		return Course;
	}

	public Course update(long id, Course course) throws NotFoundException {
		Course CourseInDb = this.findByID(id);
		CourseInDb.setName(course.getName());
		Course save = courseDAO.save(CourseInDb);
		return save;
	}
}
