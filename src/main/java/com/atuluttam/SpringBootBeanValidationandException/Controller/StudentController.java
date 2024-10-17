package com.atuluttam.SpringBootBeanValidationandException.Controller;


import com.atuluttam.SpringBootBeanValidationandException.Error.StudentException;
import com.atuluttam.SpringBootBeanValidationandException.Model.Student;
import com.atuluttam.SpringBootBeanValidationandException.Repository.StudentRepo;
import com.atuluttam.SpringBootBeanValidationandException.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("/home")
    public String home(){
        return "Welcome To Home";
    }
//Save A Student
    @PostMapping("/save")
    public ResponseEntity<String> StudentSaving(@Valid @RequestBody Student s)
    {
        studentService.saveAStudent(s);
        return new ResponseEntity<>("Student Saved in Database!!!", HttpStatus.CREATED);
    }

//Retrieve A Student with Id
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id)
    {
       Optional<Student> student = studentService.getAStudentwithId(id);
        if(student.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(student.get(), HttpStatus.FOUND);
    }

//Retrieve All Stduents
@GetMapping("/getStudent")
public ResponseEntity<List<Student>> getAllStudent()
{
    List<Student> studentList = studentService.getAllStudent();
    if(studentList.isEmpty())
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
else
    return new ResponseEntity<>(studentList, HttpStatus.OK);
}

//Delete A student with Id
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteAStudent(@PathVariable Integer id)
{
    Optional<Student> s  = studentService.getAStudentwithId(id);
    if(s.isEmpty())
        return new ResponseEntity<>("Student Does Not Exist!!", HttpStatus.NO_CONTENT);
    else {
            studentService.removeStudentById(id);
           return new ResponseEntity<>("Student Deleted!!!", HttpStatus.OK);
        }
    }

@DeleteMapping("/delete")
public ResponseEntity<String> deleteAllStudentRecords()
{
    long b = studentService.checkStudent();
    if(b>0) {
        studentService.removeAllStudent();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    else
        return new ResponseEntity<>("No Student records to delete",HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException ex)
    {
        return new ResponseEntity<>("Your Input is Invalid", HttpStatus.BAD_REQUEST);
    }
}
