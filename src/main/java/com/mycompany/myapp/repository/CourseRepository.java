package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.dto.CourseDto;
import com.mycompany.myapp.domain.dto.CourseWithTNDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
//    @Query("SELECT Course FROM course c  WHERE c.name = :courseName")
//    Course findCourseByCourseName(@Param("courseName") String courseName);

    @Query("SELECT new com.mycompany.myapp.domain.dto.CourseDto(c.courseName, c.courseLocation, c.courseContent, c.teacherId) from Course c")
    List<CourseDto> findAllCoursesDto();

    @Query("SELECT new com.mycompany.myapp.domain.dto.CourseWithTNDto(c.courseName, c.courseLocation, c.courseContent, u.login) from Course c left join User u on c.teacherId = u.id")
    List<CourseWithTNDto> findAllCoursesDtoWithTeacherName();

    @Query("SELECT new com.mycompany.myapp.domain.dto.CourseWithTNDto(uc.course.courseName, uc.course.courseLocation, uc.course.courseContent, uc.user.login) from UserCourse uc join User u on uc.course.teacherId = u.id where uc.user.id = :currId ")
    List<CourseWithTNDto> findAllCoursesbyId(@Param("currId") Long id);

    Optional<Course> findCourseByCourseName(String courseName);


//    List<Course> findCourseBycourse_idin();
}
