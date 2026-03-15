package org.example;

import org.example.dao.PersonDao;
import org.example.dao.impl.PersonDaoImpl;
import org.example.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		PersonDao personDao = new PersonDaoImpl();

		Person person = new Person(
				30,
				new BigDecimal("2500.50"),
				"AA12345678",
				"Warsaw",
				LocalDate.of(1994,5,10),
				LocalDateTime.now(),
				LocalTime.of(13,0)
		);

		personDao.save(person);

		System.out.println("Person сохранен!");

		List<Person> persons = personDao.findAll();

		System.out.println("Список людей:");

		for (Person p : persons) {
			System.out.println(p.getId() + " " + p.getPassport());
		}

	}
}