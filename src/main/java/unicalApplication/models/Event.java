package unicalApplication.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;
	private String local;
	private String presentor;
	@Lob
	private String description;
	private String link;
	private String startDate;
	private String startHour;
	private String endDate;
	private String endHour;
}
