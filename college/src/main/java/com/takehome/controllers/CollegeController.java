package com.takehome.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takehome.services.CollegeService;
import com.takehome.models.CollegeInfo;
import com.takehome.models.CollegeInfoDTO;
import com.takehome.repository.CollegeRepository;

@RestController
public class CollegeController {

	@Autowired
	CollegeRepository repository;
	
	@Autowired
	private CollegeService collegeService;
	
	//import data from csv
	@RequestMapping(path = "feed_college_data")
	public void setData() {
		collegeService.saveCollegeInfo();
	}
	
	// Select, Insert, Delete, Update Operations for an College
	/*
	 * Return request and error as a String and http status code.
	 */
	/*@RequestMapping(value = "/college", method = RequestMethod.GET)
    ResponseEntity<String> getCollege(@RequestParam(value = "name") String name, @RequestParam(value = "housing", required = false) Boolean housing){   		
		CollegeInfo college;
		String response = "";
		HttpStatus status;
		if (name == "" || name == null) {
			response = "Error: College name is required";
			status = HttpStatus.BAD_REQUEST;
			return  new ResponseEntity<>(response,status);
		}
		try {
			college = repository.findById(name).get();
			CollegeInfoDTO colDTO = new CollegeInfoDTO(college, housing);
			response = "College Name: " + colDTO.getCollegeName() + ", Total Cost: $" + String.valueOf(colDTO.getCost());
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			response = "Error: College not found";
			status = HttpStatus.BAD_REQUEST;
		}	
		return  new ResponseEntity<>(response,status); 
    }*/
	
	/*
	 * Return error as a List of Strings and http status code
	 */
	@RequestMapping(value = "/college", method = RequestMethod.GET)
    ResponseEntity<List<String>> getCollege(@RequestParam(value = "name") String name, @RequestParam(value = "housing", required = false) Boolean housing){   		
		CollegeInfo college;
		List<String> response = new ArrayList<String>();
		HttpStatus status;
		if (name == "" || name == null) {
			response.add("Error: College name is required");
			status = HttpStatus.BAD_REQUEST;
			return  new ResponseEntity<List<String>>(response,status); 
		}
		try {
			college = repository.findById(name).get();	
			CollegeInfoDTO colDTO = new CollegeInfoDTO(college, housing);
			response.add("College Name: " + colDTO.getCollegeName());
			response.add("Total Cost: $" + String.valueOf(colDTO.getCost()));
			status = HttpStatus.OK;
		} catch (Exception e) {
			response.add("Error: College not found");
			status = HttpStatus.BAD_REQUEST;
		}		
		return  new ResponseEntity<List<String>>(response,status); 
    } 
	
	/*
	 * Return request as JSON and http status code.
	 */
	/*
	@RequestMapping(value = "/college", method = RequestMethod.GET)
	ResponseEntity<CollegeInfoDTO> getCollege(@RequestParam(value = "name") String name, @RequestParam(value = "housing", required = false) Boolean housing){   				
		CollegeInfo college;
		if (name == "" || name == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			college = repository.findById(name).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		CollegeInfoDTO colDTO = new CollegeInfoDTO(college, housing);			
		return new ResponseEntity<>(colDTO, HttpStatus.OK); 
    }*/

    @RequestMapping(value = "/college", method = RequestMethod.POST)
    String addCollege(@RequestBody CollegeInfo college){
    	CollegeInfo savedCollege = repository.save(college);
        return "SUCCESS";
    }

    @RequestMapping(value = "/college", method = RequestMethod.PUT)
    CollegeInfo updateCollege(@RequestBody CollegeInfo college){
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
    /*
    @RequestMapping(value = "/colleges", method = RequestMethod.GET)
    List<CollegeInfo> getAllColleges(){
        return repository.findAll();
    }*/
    /*
     * Return List of DTO and http status code
     */
    @RequestMapping(value = "/colleges", method = RequestMethod.GET)
    ResponseEntity<List<CollegeInfoDTO>> getAllColleges(@RequestParam(value = "housing", required = false) Boolean housing){   				
    	List<CollegeInfo> college;
    	List<CollegeInfoDTO> colDTO = new ArrayList<CollegeInfoDTO>(); ;
    	CollegeInfoDTO tempDTO ;
    	int counter = 0;
    	
    	if (repository.findAll().isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	
		try {
			college = repository.findAll();
			for(CollegeInfo col : college) {
			tempDTO = new CollegeInfoDTO(col, housing); 
			colDTO.add(counter++, tempDTO);
		}
			return new ResponseEntity<List<CollegeInfoDTO>>(colDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		//return new ResponseEntity<List<CollegeInfoDTO>>(colDTO, HttpStatus.OK);
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
