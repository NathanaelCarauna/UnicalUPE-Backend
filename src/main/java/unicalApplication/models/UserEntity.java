package unicalApplication.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import unicalApplication.enums.AccountType;

@Entity
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String email;
	private String name;
	
	@OneToMany(targetEntity = Notification.class, cascade = CascadeType.ALL)
	private List<Notification> notifications;
	
	private AccountType accountType;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;
}
