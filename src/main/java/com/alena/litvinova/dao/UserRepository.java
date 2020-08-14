package com.alena.litvinova.dao;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import com.alena.litvinova.models.*;
//
//@Repository
//public interface UserRepository extends CrudRepository<User, Long> {}

import org.springframework.data.jpa.repository.JpaRepository;

import com.alena.litvinova.models.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}