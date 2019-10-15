package com.jdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.sql.*;


@Service
public class PostService {

//	@PersistenceContext(name = "")
//	EntityManager entityManager;

	@Autowired
	DatabaseLayer databaseLayer;



	public Post[] getAllPost() {
		Post[] posts = new Post[5];
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver"); //Load and Register the Driver



         // Establish the connection
			connection =
					DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcdemo", "postgres", "postgres");




			Statement statement = connection.createStatement();  // Create the statement


			// Execute The Statement
			//Read about statement.excecuteAndUpdate();
			ResultSet rs = statement.executeQuery("SELECT * FROM posts where id = 1");



			//Process the result
			rs.next();

			Post post = new Post();   //Post p = find(1);
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			//post.setDescription(rs.getString("Description"));

			posts[0] = post;

		//	int index = 0;
//			while (rs.next()) {
//				Post post = new Post();
//				post.setTitle(rs.getString("title"));
//				post.setDescription(rs.getString("Description"));
//				posts[0] = post;
//			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return posts;
	}

	public Post getPostUsingJPA() {
		Post p = new Post();
		return databaseLayer.getPost(1);
	}


	public Post getPostUsingJPA(int id) {

		Post p = new Post();
		return databaseLayer.getPost(id);
	}

	public boolean insertPost() {
		Post post = new Post();
		post.setId(4);
		post.setTitle("New Post 4");
		post.setDescription("This is new post Descritpion");
		return databaseLayer.insertPost(post);
	}


	public boolean updatePost() {
		return databaseLayer.updatePost();
	}

	public boolean deletePost() {
		Post p = databaseLayer.getPost(1);
	    boolean isDeleted =	databaseLayer.delete(p);
		return isDeleted;
	}



}
