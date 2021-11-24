package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.UserEntity;

public interface IUserDAO extends JpaRepository<UserEntity, Long>{

}
