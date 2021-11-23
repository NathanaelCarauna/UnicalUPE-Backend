package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long>{

}
