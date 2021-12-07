package unicalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.models.Event;
import unicalApplication.models.Notification;
import unicalApplication.models.UserEntity;

@Repository
public interface INotificationDAO extends JpaRepository<Notification, Long>{

	List<Notification> findByUser(UserEntity user);

}
