package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.Event;

public interface IEventRepository extends JpaRepository<Event, Long>{

}
