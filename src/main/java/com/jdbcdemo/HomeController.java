package com.jdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


	@Autowired
	PostService postService;

	@RequestMapping("/")
	@ResponseBody
	public Post getAllPosts() {
		Post[] posts = postService.getAllPost();
		return  posts[0];
	}


	@RequestMapping("/post")
	@ResponseBody
	public Post getPosts() {
		Post post = postService.getPostUsingJPA();
		return  post;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public boolean insertPost() {
		return postService.insertPost();
	}


	@RequestMapping("/delete")
	@ResponseBody
	public boolean deletePost() {
		return postService.deletePost();
	}


	@RequestMapping("/update")
	@ResponseBody
	public boolean updatePost() {
		return postService.updatePost();
	}

	@RequestMapping("/post/{postId}")
	@ResponseBody
	public Post getPostById(@PathVariable("postId") int id) {
		return postService.getPostUsingJPA(id);
	}

	@RequestMapping("/postParam")
	@ResponseBody
	public Post getPostById(@RequestParam("id") Integer id) {
		return postService.getPostUsingJPA(id);
	}





}
