package com.example.demo.Repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.UserEntity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RepoImpl implements UserRepo {

  @Autowired
  private EntityManager entityManager;
  
  @Override
  @Transactional
  public void save(User theUser) {

    System.out.println("User saving...");
    entityManager.persist(theUser);
  }

  @Override
  public List<User> getAll() {
    TypedQuery<User>theQuery = entityManager.createQuery("From User",User.class);
    return theQuery.getResultList();
  }

  @Override
  public User findById(int id) {
    User theUser = entityManager.find(User.class,id);
    return theUser;
  }

  @Override
  public List<User> sortAllUser(String field) {
    TypedQuery<User> theQuery = entityManager.createQuery("From User Order by "  + field  + " asc" ,User.class);
    return theQuery.getResultList();
  }

  @Override
  @Transactional
  public void update(User user) {

    entityManager.merge(user);
  }

  @Override
  @Transactional
  public void delete(int id) {
    User theUser = entityManager.find(User.class,id);
    entityManager.remove(theUser);
  }

  @Override
  @Transactional
  public int deleteAll() {
    int numRowsDeleted = entityManager.createQuery("Delete From User",User.class).executeUpdate();
    return numRowsDeleted;
    }
  
}
