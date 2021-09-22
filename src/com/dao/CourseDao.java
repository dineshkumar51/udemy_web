package com.dao;

import com.model.Course;
import java.util.List;

public interface CourseDao
{
    int add(Course course);
    void update(Course course);
    Course getCourse(int course_id);
    List<Course> getCourses();
    List<Course> getCourses(String searchString);
    List<Course> getCoursesCreatedBy(int instructor_id);
    List<Course> getCoursesUnder(int topic_id);
}
