package com.takehome.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takehome.models.CollegeInfo;
import com.takehome.repository.CollegeRepository;

@Service
public class CollegeService {

	@Autowired
	private CollegeRepository colRp;
    public CollegeInfo findCollegeInfoByName(String collegeName) {
        CollegeInfo collegeInfo = new CollegeInfo();
        return collegeInfo;
    }
    
    public List<CollegeInfo> allCollege(){
    	return colRp.findAll();
    }
     
    /****
     * Read csv file
     * skip the first line (header of the file)
     * loop until the end of the file, add to DB
     */
	public void saveCollegeInfo() {
		//String line = "";
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/College.csv"));
			reader.readLine();
			String line = "";
			while((line = reader.readLine()) != null) {
				
				if(line.isBlank() && line.isEmpty()) continue;
				
				String[] data = line.split(",");
				CollegeInfo c = new CollegeInfo();
							
				try {
					c.setCollegeName(data[0]);
				} catch (Exception ex) {}
				//prevent numberformatexception
				try {
					c.setInStateTuition(Double.parseDouble(data[1]));
				} catch (Exception ex) {}
				
				try {
					c.setOutOfStateTuition(Double.parseDouble(data[2]));
				} catch (Exception ex) {}

				try {
					c.setHousing(Double.parseDouble(data[3]));
				} catch (Exception ex) {}
				
				try {
					colRp.save(c);	
				} catch (Exception ex) {}
				
				/*if(data[0].length() > 0) {
					colRp.save(c);			
				}*/
				
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
