package com.jdbcdemo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class DatabaseLayer {

	@PersistenceUnit(unitName = "xyz")
	EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;


	public Post getPost(int id) {
	//	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager = entityManagerFactory.createEntityManager();
		Post p = entityManager.find(Post.class, id);
		TypedQuery<Post> query = entityManager.createQuery("SELECT p from Post p", Post.class);
		List<Post> resultList = query.getResultList();
		return p;
	}

	public boolean insertPost(Post post) {
		boolean isInserted = false;
		post.setDescription("This is new post Descritpion");
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager = entityManagerFactory.createEntityManager();
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


	public boolean delete(Post post) {
		boolean isDeleted = false;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Post p1 = entityManager.find(Post.class, 4);
		try {
			transaction.begin();
			entityManager.remove(p1);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return isDeleted;
	}


	public  boolean updatePost() {
//		Post post1 = new Post();
//		post1.setId(10);
//		post1.setTitle("This is title");
//		post1.setDescription("This is desc");
		boolean isUpdated = false;
		entityManager = entityManagerFactory.createEntityManager();
		Post p1 = entityManager.find(Post.class, 2);
		p1.setTitle("Post 2 updated");
		p1.setDescription("Post 2 updated");
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(p1);
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return isUpdated;
	}
}
