package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.models.UserEntity;

@Repository
public interface IUserDAO extends JpaRepository<UserEntity, Long>{

}
