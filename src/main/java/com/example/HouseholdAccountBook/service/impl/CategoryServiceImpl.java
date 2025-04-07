package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.Exception.OurException;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.Category;
import com.example.HouseholdAccountBook.repository.CategoryRepository;
import com.example.HouseholdAccountBook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Response getCategoryAll() {
        Response response = new Response();
        try {
            List<Category> categoryList = categoryRepository.findAll();

        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage("カテゴリーの取得に失敗しました。");
        }
        return response;
    }
}
