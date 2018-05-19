package com.lenicliu.spring.boot.jpa.repo;

import com.lenicliu.spring.boot.DataSourceRouting;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lenicliu.spring.boot.domain.User;

@DataSourceRouting(db = "app")
public interface UserRepository extends JpaRepository<User, Long> {
}
