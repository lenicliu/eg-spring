package com.lenicliu.spring.boot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Assert;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	private static AtomicLong		ID		= new AtomicLong(1);
	private static Map<Long, Post>	POSTS	= new HashMap<Long, Post>();

	public PostService() {
		Post post1 = new Post();
		post1.setAuthor("lenicliu");
		post1.setCreated(new Date());
		post1.setId(ID.getAndIncrement());
		post1.setState(Post.State.PUBLISH);
		post1.setTitle("Hi, Hateoas");
		POSTS.put(post1.getId(), post1);

		Post post2 = new Post();
		post2.setAuthor("kitty");
		post2.setCreated(new Date());
		post2.setId(ID.getAndIncrement());
		post2.setState(Post.State.DRAFT);
		post2.setTitle("Spring-Boot-Hateoas");
		POSTS.put(post2.getId(), post2);

		Post post3 = new Post();
		post3.setAuthor("lenic & kitty");
		post3.setCreated(new Date());
		post3.setId(ID.getAndIncrement());
		post3.setState(Post.State.PUBLISH);
		post3.setTitle("I don't care");
		POSTS.put(post3.getId(), post3);
	}

	public Post find(Long id) {
		return POSTS.get(id);
	}

	public List<Post> findAll() {
		return new ArrayList<Post>(POSTS.values());
	}

	public void delete(Long id) {
		POSTS.remove(id);
	}

	public void modify(Long id, Post post) {
		Assert.assertEquals(id, post.getId());
		POSTS.put(id, post);
	}
	
	public void create(Post post){
		post.setId(ID.getAndIncrement());
		post.setCreated(new Date());
		POSTS.put(post.getId(), post);
	}
}