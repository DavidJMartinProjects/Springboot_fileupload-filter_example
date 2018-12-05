package com.microservice.domain;

public class Employee {
	
	private String id;
	private String name;
	private String address;
	private Nationality nationality;	
	private EmploymentStatus employmentStatus;
	
	public Employee() {
	}

	public Employee(String id, String name, String address, Nationality nationality, EmploymentStatus employmentStatus) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.nationality = nationality;
		this.employmentStatus = employmentStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public EmploymentStatus getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(EmploymentStatus employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", nationality=" + nationality
				+ ", employmentStatus=" + employmentStatus + "]";
	}

}
