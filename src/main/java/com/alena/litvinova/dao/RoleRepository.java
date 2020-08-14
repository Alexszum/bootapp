package com.alena.litvinova.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alena.litvinova.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}