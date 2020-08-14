package com.alena.litvinova.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alena.litvinova.models.*;

@Repository
public interface IWritterCRUD extends CrudRepository<Writer, Integer>  {

}
