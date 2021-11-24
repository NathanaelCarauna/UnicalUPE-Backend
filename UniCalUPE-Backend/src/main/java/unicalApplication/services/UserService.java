package unicalApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import unicalApplication.models.UserEntity;
import unicalApplication.repositories.IUserDAO;

@Service
public class UserService {

	@Autowired
	IUserDAO userDAO;

	public List<UserEntity> getAll() throws NotFoundException {
		List<UserEntity> all = userDAO.findAll();
		if (all.isEmpty()) {
			throw new NotFoundException("Nenhuma notificação encontrada");
		}
		return all;
	}

	public UserEntity add(UserEntity UserEntity) {
		return userDAO.save(UserEntity);
	}

	public UserEntity findByID(Long id) throws NotFoundException {
		Optional<UserEntity> userEntity = userDAO.findById(id);
		if (userEntity.isEmpty())
			throw new NotFoundException("UserEntity não encontrado");
		return userEntity.get();
	}

	public UserEntity delete(Long id) throws NotFoundException {
		UserEntity UserEntity = this.findByID(id);
		userDAO.delete(UserEntity);
		return UserEntity;
	}

	public UserEntity update(long id, UserEntity user) throws NotFoundException {
		UserEntity userEntityInDb = this.findByID(id);
		if (user.getName() != null)
			userEntityInDb.setName(user.getName());
		if (user.getAccountType() != null)
			userEntityInDb.setAccountType(user.getAccountType());
		if (user.getCourse() != null)
			userEntityInDb.setCourse(user.getCourse());

		UserEntity save = userDAO.save(userEntityInDb);
		return save;
	}
}
