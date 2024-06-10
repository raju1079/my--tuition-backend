package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.Subjects;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects, Long> {

}
