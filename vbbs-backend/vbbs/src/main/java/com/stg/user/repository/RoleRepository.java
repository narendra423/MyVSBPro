package com.stg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.user.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
