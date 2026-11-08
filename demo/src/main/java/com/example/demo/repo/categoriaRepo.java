package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Department;

public interface categoriaRepo extends JpaRepository<Department, String> {
}
