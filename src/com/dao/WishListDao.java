package com.dao;

import com.model.Course;
import java.util.List;

public interface WishListDao
{
     List<Course> getCourses(int learner_id);
     int addToWishList(int course_id,int instructor_id);
     boolean checkFromWishList(int course_id, int learner_id);
}
