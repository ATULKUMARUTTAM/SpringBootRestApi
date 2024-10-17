package com.atuluttam.SpringBootBeanValidationandException.Repository;

import com.atuluttam.SpringBootBeanValidationandException.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
   public long count();
}
