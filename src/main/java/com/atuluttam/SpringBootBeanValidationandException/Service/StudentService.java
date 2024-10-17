package com.atuluttam.SpringBootBeanValidationandException.Service;

import com.atuluttam.SpringBootBeanValidationandException.Error.StudentException;
import com.atuluttam.SpringBootBeanValidationandException.Model.Student;
import com.atuluttam.SpringBootBeanValidationandException.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    @Autowired
    StudentRepo studentRepo;
    public void saveAStudent(Student student) {

         studentRepo.save(student);
    }

    public Optional<Student> getAStudentwithId(Integer id) {

        return studentRepo.findById(id);
        //return studentRepo.findById(id).orElseThrow(()-> new StudentException("Student not found!!!"));

    }

    public List<Student> getAllStudent()
    {
        return studentRepo.findAll();
    }

    public void removeStudentById(Integer id)
    {
        studentRepo.deleteById(id);
    }

    public void removeAllStudent()
    {
        studentRepo.deleteAll();
    }
    public Student updateAStudent(Integer id, Student s)
    {
        Optional<Student> s1 = studentRepo.findById(id);
        if(s1.isEmpty())
        {
            throw new StudentException("Student Not found!!!!");
        }
        s.setEmail(s1.get().getEmail());
        s.setName(s1.get().getName());
        s.setContactno(s1.get().getContactno());
        return studentRepo.save(s);
    }


    public long checkStudent() {
    return studentRepo.count();
    }
}
