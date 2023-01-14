package com.exam.demo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Developer extends Employee{
	
	public Developer() {
		// TODO Auto-generated constructor stub
	}

	@Column(name = "ProgrammingLanguage", length = 50, nullable = true)
	private String programmingLanguage;
	
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	@Override
	public String toString() {
		return "Developer [firstName=" + getFirstName() + ", lastName=" + getLastName() + ", monthlySalary=" + getMonthlySalary()
				+ ", programmingLanguage=" + programmingLanguage + "]";
	}
	
}
