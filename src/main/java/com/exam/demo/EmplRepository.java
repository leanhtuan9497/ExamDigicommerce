package com.exam.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;



public interface EmplRepository extends JpaRepository<Employee, Long> {
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("update Employee set monthlySalary = monthlySalary + (monthlySalary*(?1/100))")
	public void updateMonthlySalary(double rate);

}
