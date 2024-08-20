package com.example.demo.Repo;

import java.util.List;

import com.example.demo.UserEntity.User;

public interface UserRepo {
  
    void save(User theUser);

  List<User>getAll();

  User findById(int id);

  List<User>sortAllUser(String field);

  void update(User user);

  void delete(int id);
  
  int deleteAll();
}
