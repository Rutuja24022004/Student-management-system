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
    public String removeStudent(@RequestParam("studentId") int studentId,Model m)
    {
    	s.deleteStudent(studentId);
    	List<Student> st = s.getAllStudent();
    	m.addAttribute("data", st);
    	return "adminscreen";
    }
    
    @RequestMapping("/view")
    public String viewpage(@ModelAttribute Student stud,Model m)
    {
      List<Student>st=s.getAllStudent();
      m.addAttribute("data", st);
	return "view";
    }
    
    @RequestMapping("/search")
    public String getStudentBatch(@RequestParam("batchNumber") String batchNumber,@RequestParam("batchMode") String batchMode,Model m)
    {
    	
    	List<Student>result=s.findAllByBatchNumberAndBatchMode(batchNumber,batchMode);
    	
    	if(result.size()>0)
    	{
    		m.addAttribute("data", result);
    	}
    	else
    	{
    		List<Student>list=s.getAllStudent();
    		m.addAttribute("data", list);
    		m.addAttribute("message","No record found for the Batch" + batchNumber + "  " + batchMode);
    		
    	}
    return "view";
    }
    
    @RequestMapping("/fees")
    public String onFees(@RequestParam("studentId") int studentId,Model m)
    {
    	Student stu=s.getSingleStudentt(studentId);
    	m.addAttribute("st", stu);
    	return "fees";
    }
    @RequestMapping("/payfees")
    public String payFees(@RequestParam("studentId")int studentId,@RequestParam("feesPaid") double amount,Model m)
    {
    	s.payFees(studentId,amount);
    	List<Student>list=s.getAllStudent();
		m.addAttribute("data", list);
    	return "view";
    }
    
    @RequestMapping("shift")
    public String shiftBatch(@RequestParam("studentId") int studentId,Model m) 
    {

    	
    	Student st =s.shiftBatch(studentId);
		m.addAttribute("data", st);
    	return "shiftBatch";
       
        
    	
    }
    @RequestMapping("shiftbatch")
    public String shiftStudent(@RequestParam("studentId") int rollno,@RequestParam("newBatchNumber") String newBatchNumber,@RequestParam("newBatchMode") String newBatchMode,Model m)
    {
    	s.shiftBatch( newBatchNumber, newBatchMode,rollno);
        List<Student> students = s.getAllStudent();
        m.addAttribute("data", students);
        return "view";
    }
    
    
}
