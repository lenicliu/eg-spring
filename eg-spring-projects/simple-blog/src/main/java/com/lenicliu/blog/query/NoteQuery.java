package com.lenicliu.blog.query;

import com.lenicliu.blog.model.Note;
import com.lenicliu.blog.model.Page;

/**
 * Created by lenicliu on 11/11/16.
 */
public class NoteQuery extends Page<Note> {
    public NoteQuery(int page, int rows) {
        super(page, rows);
    }
}
