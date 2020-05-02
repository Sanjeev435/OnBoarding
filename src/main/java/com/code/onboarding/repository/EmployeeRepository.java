package com.code.onboarding.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.code.onboarding.entity.Employee;

@Repository
public interface EmployeeRepository extends AbstractCommonRepository<Employee, Integer>, Serializable {
}
