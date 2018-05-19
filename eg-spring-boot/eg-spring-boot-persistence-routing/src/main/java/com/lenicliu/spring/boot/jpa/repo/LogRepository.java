package com.lenicliu.spring.boot.jpa.repo;

import com.lenicliu.spring.boot.DataSourceRouting;
import com.lenicliu.spring.boot.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

@DataSourceRouting(db = "log")
public interface LogRepository extends JpaRepository<Log, Long> {
}
