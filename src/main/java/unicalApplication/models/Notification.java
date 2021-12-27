package unicalApplication.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import unicalApplication.enums.Category;

@Entity
@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String title;

	@OneToOne(cascade = CascadeType.MERGE)
	private Event event;
	
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserEntity user;

	private Date creationTime;

	private boolean visualized;

}
