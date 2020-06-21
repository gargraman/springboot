package com.howtodoinjava.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.Employee;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee, Long>
{

}
