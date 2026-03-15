package org.example.dao;

import org.example.model.Person;

import java.util.List;

public interface PersonDao {

	void save(Person person);

	Person findById(int id);

	List<Person> findAll();

	void delete(int id);

}