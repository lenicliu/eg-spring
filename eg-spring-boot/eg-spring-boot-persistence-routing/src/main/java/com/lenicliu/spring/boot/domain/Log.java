package com.lenicliu.spring.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity(name = "TB_USER_LOG")
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    private Long id;
    @Column
    private Long uid;
    @Column
    private String content;
}