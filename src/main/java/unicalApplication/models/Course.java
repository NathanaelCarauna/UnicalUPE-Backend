package unicalApplication.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course")
    private List<UserEntity> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private List<Event> events;
}
