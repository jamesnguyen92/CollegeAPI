package com.takehome.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takehome.models.CollegeInfo;
import com.takehome.repository.CollegeRepository;

@RestController
public class CollegeController {

	@Autowired
	CollegeRepository repository;
	
	// Select, Insert, Delete, Update Operations for an College
	
    @RequestMapping(value = "/college/{name}", method = RequestMethod.GET)
    CollegeInfo getCollege(@PathVariable String name){
        return  repository.findById(name).get();
    }

    @RequestMapping(value = "/college", method = RequestMethod.POST)
    String addCollege(@RequestBody CollegeInfo college){
    	CollegeInfo savedCollege = repository.save(college);
        return "SUCCESS";
    }

    @RequestMapping(value = "/college", method = RequestMethod.PUT)
    CollegeInfo updateEmployee(@RequestBody CollegeInfo college){
    	CollegeInfo updatedCollege = repository.save(college);
        return updatedCollege;
    }

    @RequestMapping(value = "/college", method = RequestMethod.DELETE)
    Map<String, String> deleteCollege(@RequestParam String name){
        Map<String, String> status = new HashMap<>();
        Optional<CollegeInfo> college = repository.findById(name);
        if(college.isPresent()) {
            repository.delete(college.get());
            status.put("Status", "College deleted successfully");
        }
        else {
            status.put("Status", "College not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Colleges

    @RequestMapping(value = "/colleges", method = RequestMethod.GET)
    List<CollegeInfo> getAllColleges(){
        return repository.findAll();
    }

    @RequestMapping(value = "/colleges", method = RequestMethod.POST)
    String addAllColleges(@RequestBody List<CollegeInfo> collegeList){
        repository.saveAll(collegeList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/colleges", method = RequestMethod.DELETE)
    String addAllColleges(){
        repository.deleteAll();
        return "SUCCESS";
    }
	
}
