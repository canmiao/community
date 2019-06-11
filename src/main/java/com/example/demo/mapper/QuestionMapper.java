package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname QuestionMapper
 * @Description TODO
 * @Date 2019/6/10 23:29
 * @Created by 池灿淼
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,creator,tag,gmt_create,gmt_modified) values (#{title},#{description},#{creator},#{tag},#{gmtCreate},#{gmtModified},)")
    void create(Question question);
}
