package com.lenicliu.blog.service.impl;

import com.lenicliu.blog.mapper.NoteMapper;
import com.lenicliu.blog.model.Note;
import com.lenicliu.blog.model.Page;
import com.lenicliu.blog.query.NoteQuery;
import com.lenicliu.blog.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenicliu on 11/11/16.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public Page<Note> find(NoteQuery query) {
        Page<Note> notes = new Page<>(query);
        notes.setData(noteMapper.find(query.getOffset(), query.getRows()));
        notes.setTotal(noteMapper.count());
        return notes;
    }
}
