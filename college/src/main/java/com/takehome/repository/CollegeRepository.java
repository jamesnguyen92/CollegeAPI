package com.takehome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takehome.models.CollegeInfo;

public interface CollegeRepository extends JpaRepository<CollegeInfo, String> {

}
