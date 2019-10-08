package com.jdbcdemo;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class PostService {


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
			ResultSet rs = statement.executeQuery("SELECT * FROM posts");



			//Process the result
			rs.next();

			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setDescription(rs.getString("Description"));

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
}
