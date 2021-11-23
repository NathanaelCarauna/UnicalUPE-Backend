package unicalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicalApplication.models.Course;

@Repository
public interface ICourseDAO extends JpaRepository<Course, Long> {

}
