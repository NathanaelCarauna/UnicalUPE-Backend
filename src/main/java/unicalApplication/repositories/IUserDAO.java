package unicalApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.UserEntity;

public interface IUserDAO extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
}
