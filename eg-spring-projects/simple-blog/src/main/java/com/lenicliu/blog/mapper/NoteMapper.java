package com.lenicliu.blog.mapper;

import com.lenicliu.blog.model.Note;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lenicliu on 11/11/16.
 */
public interface NoteMapper {
    @Select("SELECT * FROM NOTE ORDER BY LEVEL DESC, CREATED DESC LIMIT #{offset}, #{rows}")
    List<Note> find(@Param("offset") int offset,@Param("rows") int rows);

    @Select("SELECT COUNT(*) FROM NOTE")
    int count();
}
