package com.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Long> {

}
