package com.dao;

import com.model.Course;
import java.util.List;

public interface EnrollmentDao
{
     List<Course> getEnrolledCourses(int learner_id);
     int addEnrollment(int course_id,int learner_id);
     boolean checkForEnrollment(int course_id, int learner_id);
}
