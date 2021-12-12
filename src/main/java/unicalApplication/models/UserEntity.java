package unicalApplication.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import unicalApplication.enums.AccountType;

@Entity
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	private String name;
	
	@OneToMany(targetEntity = Notification.class)
	private List<Notification> notifications;
	
	private AccountType accountType;
	
	@OneToOne
	private Course course;
}
