package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.Notification;

public interface INotificationDAO extends JpaRepository<Notification, Long>{

}
