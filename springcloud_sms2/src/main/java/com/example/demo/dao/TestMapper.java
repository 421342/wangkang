package com.example.demo.dao;

import com.example.demo.model.Test;

public interface TestMapper {


    int insert(Test record);

    int insertSelective(Test record);


  
}