package com.lenicliu.spring.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;

}