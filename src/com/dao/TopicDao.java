package com.dao;

import com.model.Topic;
import java.util.List;

public interface TopicDao
{
     List<Topic> getTopics();
     List<Topic> getTopics(int category_id);
     Topic getTopic(int topic_id);
}
