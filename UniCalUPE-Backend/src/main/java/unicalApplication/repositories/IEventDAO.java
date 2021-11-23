package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.Event;

public interface IEventDAO extends JpaRepository<Event, Long>{

}
