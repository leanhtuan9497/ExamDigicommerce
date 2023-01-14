package com.exam.demo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Tester extends Employee{
	@Column(name = "Automated", nullable = true)
	private boolean automated;
	
	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public boolean isAutomated() {
		return automated;
	}

	public void setAutomated(boolean automated) {
		this.automated = automated;
	}

	@Override
	public String toString() {
		return "Tester [firstName=" + getFirstName() + ", lastName=" + getLastName() + ", monthlySalary=" + getMonthlySalary()
				+ ", automated=" + automated + "]";
	}
	
}
