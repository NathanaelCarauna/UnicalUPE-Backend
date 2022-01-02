package unicalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.enums.Category;
import unicalApplication.models.Course;
import unicalApplication.models.Event;
import unicalApplication.models.UserEntity;

@Repository
public interface IEventDAO extends JpaRepository<Event, Long>{
	List<Event> findByCategory(Category category);	
	List<Event> findByStartDate(String startDate);
	List<Event> findByCourse(Course course);
	List<Event> findByUser(UserEntity user);
}
