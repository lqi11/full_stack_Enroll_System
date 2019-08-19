package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.dto.CourseDto;
import com.mycompany.myapp.domain.dto.CourseWithTNDto;
import com.mycompany.myapp.service.CourseService;
import com.mycompany.myapp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@Api(value="Course Service Controller", description = "Controller for find couses information")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/api/course/findAllCourses", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCourses() {

        List<CourseDto> allCourses = courseService.findAllCourses();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
    @GetMapping(path = "/api/course/findAllCoursesDto", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCoursesDto(){
        List<CourseDto> allCourses = courseService.findAllCoursesDtoFromDB();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
    @GetMapping(path = "/api/course/findAllCoursesWithTNDto/{id}", produces = "application/json")
    public HttpEntity<List<CourseWithTNDto>> findAllCourseswithEnrolledDto(@PathVariable String id) {

        List<CourseWithTNDto> allEnrolledCourses = courseService.findAllCoursesById(id);

        return new ResponseEntity<>(allEnrolledCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/user/findId", produces = "application/json")
    public HttpEntity<String> findId(){
        Optional<User> curUser = userService.getUserWithAuthorities();
        if (curUser.isPresent()) {
            return new ResponseEntity<>(String.valueOf(curUser.get().getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/api/course/registerCourse/{courseName}", produces = "application/json")
    public HttpStatus registerCourse(@PathVariable String courseName) {
        try {

            courseService.registerCourse(courseName);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
    }

    @PostMapping(path = "/api/course/addCourse", produces = "application/json")
    public HttpStatus addCourse(@RequestBody @NotNull CourseDto course) {
        try {
            CourseDto.CourseDtoBuilder builder = CourseDto.builder();
            builder.courseName(course.getCourseName());
            builder.courseContent(course.getCourseContent());
            builder.courseLocation(course.getCourseLocation());
            builder.teacherId(Long.valueOf(course.getTeacherId()));
            CourseDto courseDto = builder
                .build();


            courseService.addCourse(courseDto);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @PutMapping(path = "/api/course/updateCourse", produces = "application/json")
    public HttpStatus updateCourse(@RequestBody @NotNull CourseDto course) {
        try {
            courseService.updateCourse(course);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path="/api/course/deleteCourse/{courseName}", produces = "application/json")
    public HttpStatus deleteCourse(@NotNull @PathVariable("courseName") String courseName) {

        try {
            courseService.unRegisterCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path="/api/course/unRegisterCourse/{courseName}", produces = "application/json")
    public HttpStatus unRegisterCourse(@NotNull @PathVariable("courseName") String courseName) {

        try {
            courseService.unRegisterCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(path = "/api/course/findAllCoursesWithTNDto/{id}'", produces = "application/json")
    public HttpEntity<List<CourseWithTNDto>> findAllCoursesWithTNDto(@PathVariable String id){
        List<CourseWithTNDto> allCourses = courseService.findAllCoursesById(id);

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }









}
