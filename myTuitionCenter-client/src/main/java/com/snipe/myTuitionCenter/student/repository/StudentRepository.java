package com.snipe.myTuitionCenter.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.data.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}