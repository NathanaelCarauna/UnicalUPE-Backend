package unicalApplication.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import unicalApplication.enums.Category;

@Entity
@Data
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private Category category;
	private String presentor;
	private String course;
	private String description;
	private String link;
	private Date startDateTime;
	private Date endDateTime;
}
