package com.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjc.sms.app.model.Student;
import com.cjc.sms.app.serviceI.StudentServiceI;

@Controller
public class AdmineController {
	
	@Autowired
   StudentServiceI s;
    @RequestMapping("/")
	public String prelogin()
	{
		return "login";
	}
    
    @RequestMapping("/login")
    public String onlogin(@RequestParam String username , @RequestParam String password,Model m)
    {
    	if(username.equals("Admin") && password.equals("Admin123"))
    	{
    		
    		List<Student> sts=s.getAllStudent();
    		m.addAttribute("data", sts);
    		return "adminscreen";
    	}
    	else
    	{
    		m.addAttribute("login_fail","Enter valid login details.");
    		
    		return "login";
    	}
    	
    }
    @RequestMapping("enroll_student")
    public String saveStudent(@ModelAttribute Student student,Model m)
    {
    	s.saveStudentDetailed(student);
    	List<Student> st = s.getAllStudent();
    	m.addAttribute("data", st);
    	return "adminscreen";
    }
    
    @RequestMapping("/delete")
    public String removeStudent(@RequestParam("rollno") int rollno,Model m)
    {
    	s.deleteStudent(rollno);
    	List<Student> st = s.getAllStudent();
    	m.addAttribute("data", st);
    	return "adminscreen";
    }
    
    
    

}
