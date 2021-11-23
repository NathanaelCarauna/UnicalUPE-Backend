package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.models.Event;

@Repository
public interface IEventDAO extends JpaRepository<Event, Long>{

}
