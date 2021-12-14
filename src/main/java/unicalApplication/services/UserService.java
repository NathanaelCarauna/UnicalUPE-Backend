package unicalApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.models.Course;
import unicalApplication.models.UserEntity;
import unicalApplication.repositories.ICourseDAO;
import unicalApplication.repositories.IUserDAO;

@Service
public class UserService {

	@Autowired
	IUserDAO userDAO;
	@Autowired
	ICourseDAO courseDAO;

	public List<UserEntity> getAll() throws NotFoundException {
		List<UserEntity> all = userDAO.findAll();
		if (all.isEmpty()) {
			throw new NotFoundException("Nenhuma notificação encontrada");
		}
		return all;
	}

	public UserEntity add(UserEntity UserEntity){
		userDAO.save(UserEntity);		
		return UserEntity;			
	}

	public UserEntity findByEmail(String email) throws NotFoundException {
		Optional<UserEntity> userEntity = userDAO.findByEmail(email);
		if (!userEntity.isPresent())
			throw new NotFoundException("UserEntity não encontrado");
		return userEntity.get();
	}

	public UserEntity delete(String email) throws NotFoundException {
		UserEntity UserEntity = this.findByEmail(email);
		userDAO.delete(UserEntity);
		return UserEntity;
	}

	public UserEntity update(UserEntity user) throws NotFoundException {
		if (user.getId() == null) {
			return add(user);
		}
		UserEntity userEntityInDb = this.findByEmail(user.getEmail());
		if (user.getName() != null)
			userEntityInDb.setName(user.getName());
		if (user.getEmail() != null)
			userEntityInDb.setEmail(user.getEmail());
		if (user.getAccountType() != null)
			userEntityInDb.setAccountType(user.getAccountType());
		if (user.getCourse() != null) {
			Course course = courseDAO.getById(user.getCourse().getId());
			userEntityInDb.setCourse(course);
		}

		UserEntity save = userDAO.save(userEntityInDb);
		return save;
	}
}
