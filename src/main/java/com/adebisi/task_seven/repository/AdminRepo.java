package com.adebisi.task_seven.repository;

import com.adebisi.task_seven.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

  Optional<Admin> findAdminByEmailAndPassword(String email, String password);
}
