package unicalApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.enums.Category;
import unicalApplication.models.Event;

@Repository
public interface IEventDAO extends JpaRepository<Event, Long>{
	List<Event> findByCategory(Category category);	
}
