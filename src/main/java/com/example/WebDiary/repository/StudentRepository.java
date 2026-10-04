package com.example.WebDiary.repository;

import com.example.WebDiary.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("""
            SELECT s
            FROM STUDENT s
            ORDER BY s.name ASC
            """)
    List<Student> OrderedByName();

    @Query("""
            SELECT s
            FROM STUDENT s
            ORDER BY s.presence ASC
            """)
    List<Student> OrderedByPresence();

    Optional<Student> findByName(String name);

    Optional<Student> findById(UUID id);

    Page<Student> findAll(Pageable pageable);
}
