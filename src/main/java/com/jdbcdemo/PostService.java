package com.jdbcdemo;

import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.Map;

@Service
public class PostService {

//	@PersistenceContext(name = "")
//	EntityManager entityManager;

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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Post p = entityManager.find(Post.class, 1);
		return p;
	}

	public boolean insertPost() {
		boolean isInserted = false;
		Post post = new Post();
		post.setId(3);
		post.setTitle("New Post");
		post.setDescription("This is new post Descritpion");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(post);
			transaction.commit();
			isInserted = true;
		}catch(Exception e) {
			transaction.rollback();
		}
		return isInserted;
	}

}
