package com.example.demo.dao;

import com.example.demo.model.MessageInfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

public interface MessageInfoMapper {
   

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);


    int updateByExampleSelective(Map map);
    int updateStatusPatch(List<MessageInfo> list);
}