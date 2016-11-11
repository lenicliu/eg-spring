package com.lenicliu.blog.service;

import com.lenicliu.blog.model.Note;
import com.lenicliu.blog.model.Page;
import com.lenicliu.blog.query.NoteQuery;

/**
 * Created by lenicliu on 11/11/16.
 */
public interface NoteService {
    Page<Note> find(NoteQuery query);
}
