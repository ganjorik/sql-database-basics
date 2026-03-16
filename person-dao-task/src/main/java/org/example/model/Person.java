package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Person {

	private int id;
	private int age;
	private BigDecimal salary;
	private String passport;
	private String address;
	private LocalDate dateOfBirthday;
	private LocalDateTime dateTimeCreate;
	private LocalTime timeToLunch;

	public Person() {
	}

	public Person(int age, BigDecimal salary, String passport, String address,
				  LocalDate dateOfBirthday, LocalDateTime dateTimeCreate, LocalTime timeToLunch) {
		this.age = age;
		this.salary = salary;
		this.passport = passport;
		this.address = address;
		this.dateOfBirthday = dateOfBirthday;
		this.dateTimeCreate = dateTimeCreate;
		this.timeToLunch = timeToLunch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirthday() {
		return dateOfBirthday;
	}

	public void setDateOfBirthday(LocalDate dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}

	public LocalDateTime getDateTimeCreate() {
		return dateTimeCreate;
	}

	public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
		this.dateTimeCreate = dateTimeCreate;
	}

	public LocalTime getTimeToLunch() {
		return timeToLunch;
	}

	public void setTimeToLunch(LocalTime timeToLunch) {
		this.timeToLunch = timeToLunch;
	}
}