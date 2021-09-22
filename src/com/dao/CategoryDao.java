package com.dao;

import com.model.Category;

import java.util.List;

public interface CategoryDao
{
    List<Category> getCategories();
    Category getCategory(int category_id);

}
