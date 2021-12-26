package unicalApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.Course;
import unicalApplication.models.UserEntity;

public interface IUserDAO extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
	List<UserEntity> findByCourse(Course course);
}
