package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

public class CourseService {
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  //private final StudentService studentService;
  private final StudentRepository studentRepository;

  public CourseService(CourseCommandRepository courseCommandRepository, CourseQueryRepository courseQueryRepository, StudentRepository studentRepository) {
    this.courseCommandRepository = courseCommandRepository;
    this.courseQueryRepository = courseQueryRepository;
    this.studentRepository = studentRepository;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentRepository.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto);
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courseList = courseRepository.getCourseDayOfWeek(dayOfWeek);
    /*ArrayList<CourseInfoDto> courseInfoDtoArrayList = new ArrayList<>();
    for(Course course : courseList){
      CourseInfoDto courseInfoDto = new CourseInfoDto(course);
      courseInfoDtoArrayList.add(courseInfoDto);
    }
    return courseInfoDtoArrayList;*/
    return courseList.stream().map(CourseInfoDto::new).toList();
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    List<Course> courses = courseRepository.getCourseListByStudent(studentName);
    /*Student student = studentService.getStudent(studentName);
    ArrayList<Course> changeList = new ArrayList<>();
    for(Course course : courseList){
      course = new Course(student, course.getCourseName(), fee, course.getDayOfWeek(), course.getCourseTime());
      changeList.add(course);
    }
    courseRepository.saveCourses(changeList);*/
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCoursesFee(fee);
  }
}
