package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unicalApplication.models.Course;

public interface ICourseDAO extends JpaRepository<Course, Long> {

}
