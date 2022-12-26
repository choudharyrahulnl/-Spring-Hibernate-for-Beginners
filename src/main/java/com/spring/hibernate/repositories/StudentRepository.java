package com.spring.hibernate.repositories;

import com.spring.hibernate.entities.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student s where s.lastName = ?1")
    List<Student> findByLastName(String lastName);

    @Query("from Student s where s.email like concat('%', ?1)")
    List<Student> findByEmail(String email);

    /**
     * Spring Data JPA Default Methods
     */


    /**
     * CRUD Repository Methods
     */
    @Override
    <S extends Student> S save(S entity);

    @Override
    Optional<Student> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Student entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Student> entities);

    @Override
    void deleteAll();

    /**
     * List CRUD Repository Methods
     *
     */
    @Override
    <S extends Student> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Student> findAll();

    @Override
    List<Student> findAllById(Iterable<Long> longs);


    /**
     * List Paging and Sorting Repository Methods
     */
    @Override
    List<Student> findAll(Sort sort);

    /**
     * Paging and Sorting
     */
    @Override
    Page<Student> findAll(Pageable pageable);

    /**
     * JPA Repository
     */
    @Override
    void flush();

    @Override
    <S extends Student> S saveAndFlush(S entity);

    @Override
    <S extends Student> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<Student> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Student getReferenceById(Long aLong);

    @Override
    <S extends Student> List<S> findAll(Example<S> example);

    @Override
    <S extends Student> List<S> findAll(Example<S> example, Sort sort);

    /**
     * Query by Example Executor
     */
    @Override
    <S extends Student> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Student> long count(Example<S> example);

    @Override
    <S extends Student> boolean exists(Example<S> example);

    @Override
    <S extends Student, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);


}