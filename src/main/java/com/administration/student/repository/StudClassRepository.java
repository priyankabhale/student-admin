package com.administration.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administration.student.entity.StudClass;

@Repository
public interface StudClassRepository extends JpaRepository<StudClass, Long>{

}
