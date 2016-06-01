package com.lenicliu.spring.boot;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * GET		/posts
 * 
 * POST		/posts
 * GET		/posts/{id}
 * PUT		/posts/{id}
 * DELETE	/posts/{id}
 * 
 * Simple CRUD Hypemedia-Driven RESTfull Service
 * 
 * @author lenicliu
 *
 */
@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<Post>>> get() {
		List<Post> posts = postService.findAll();
		if (posts == null || posts.isEmpty()) {
			return blank();
		}
		Link _self = linkTo(methodOn(getClass()).get()).withSelfRel();
		return ok(new Resources<Resource<Post>>(linkPosts(posts), _self));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<Resource<Post>> get(@PathVariable Long id) {
		Post post = postService.find(id);
		if (post == null || post.getId() == null) {
			return notFound();
		}
		return ok(linkPost(post));
	}

	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<Resource<Post>> post(@RequestBody Post post) {
		postService.create(post);
		return ok();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Resource<Post>> put(@PathVariable Long id, @RequestBody Post post) {
		postService.modify(id, post);
		return ok();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<Resource<Post>> delete(@PathVariable Long id) {
		Post post = postService.find(id);
		if (post == null || post.getId() == null) {
			return notFound();
		}
		postService.delete(id);
		return ok();
	}
	
	private <T> HttpEntity<T> ok(T value) {
		return new ResponseEntity<T>(value, HttpStatus.OK);
	}

	private <T> HttpEntity<T> ok() {
		return new ResponseEntity<T>(HttpStatus.OK);
	}

	private <T> HttpEntity<T> blank() {
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}

	private <T> HttpEntity<T> notFound() {
		return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
	}

	private Resource<Post> linkPost(Post post) {
		return linkPost(post, null, null);
	}

	private Resource<Post> linkPost(Post post, Post next, Post prev) {
		Resource<Post> resource = new Resource<Post>(post);
		resource.add(linkTo(methodOn(getClass()).get(post.getId())).withSelfRel());
		resource.add(linkTo(methodOn(getClass()).get()).withRel("list"));
		resource.add(linkTo(methodOn(getClass()).post(post)).withRel("create"));
		resource.add(linkTo(methodOn(getClass()).put(post.getId(), post)).withRel("modify"));
		resource.add(linkTo(methodOn(getClass()).delete(post.getId())).withRel("remove"));
		if (next != null && next.getId() != null) {
			resource.add(linkTo(methodOn(getClass()).get(next.getId())).withRel("next"));
		}
		if (prev != null && prev.getId() != null) {
			resource.add(linkTo(methodOn(getClass()).get(prev.getId())).withRel("prev"));
		}
		return resource;
	}

	@SuppressWarnings("serial")
	private Collection<Resource<Post>> linkPosts(final List<Post> posts) {
		return new ArrayList<Resource<Post>>() {
			{
				for (int i = 0, n = posts.size(); i < n; i++) {
					Post post = posts.get(i);
					Post next = i + 1 < n ? posts.get(i + 1) : null;
					Post prev = i - 1 < 0 ? null : posts.get(i - 1);
					add(linkPost(post, next, prev));
				}
			}
		};
	}
}