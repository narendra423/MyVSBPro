package com.stg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.user.entity.Permissions;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Integer> {

}
