package com.spring.hibernate.repositories;

import com.spring.hibernate.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student s where s.lastName = ?1")
    List<Student> findByLastName(String lastName);

    @Query("from Student s where s.email like concat('%', ?1)")
    List<Student> findByEmail(String email);
}