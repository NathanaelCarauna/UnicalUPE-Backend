package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.models.Notification;

@Repository
public interface INotificationDAO extends JpaRepository<Notification, Long>{

}
