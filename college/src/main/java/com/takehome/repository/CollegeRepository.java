package com.takehome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.takehome.models.CollegeInfo;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeInfo, String> {
	/*
	@Query(value = "SELECT Name, in_state, out_state, housing from college where (:name is null or name=:name)", nativeQuery=true)
	List<CollegeInfo> getCollegeInfoWithHousing(@Param("name")String name);
	*/
}
