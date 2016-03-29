package com.lenicliu.spring.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * <pre>
 * GET http://localhost:8888/users
 * POST http://localhost:8888/users {"name":"renwoxing"}
 * PUT http://localhost:8888/users/4 {"name":"renwoxing","email":"renwoxing@sina.com"}
 * DELETE http://localhost:8888/users/4
 * </pre>
 * 
 * @author lenic 2014-12-31
 *
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * <pre>
	 * http://localhost:8888/users/search/findByName?name=zhangsan
	 * </pre>
	 * 
	 * @param name
	 * @return
	 */
	User findByName(@Param("name") String name);

	/**
	 * <pre>
	 * http://localhost:8888/users/search/findByNameLikeOrEmailLike?name=%25163%25&email=%25163%25
	 * </pre>
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	List<User> findByNameLikeOrEmailLike(@Param("name") String name, @Param("email") String email);
}