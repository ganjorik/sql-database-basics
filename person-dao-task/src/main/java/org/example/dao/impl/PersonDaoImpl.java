package org.example.dao.impl;

import org.example.dao.PersonDao;
import org.example.model.Person;
import org.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void save(Person person) {

		String sql = "INSERT INTO persondao (age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, person.getAge());
			ps.setBigDecimal(2, person.getSalary());
			ps.setString(3, person.getPassport());
			ps.setString(4, person.getAddress());
			ps.setDate(5, Date.valueOf(person.getDateOfBirthday()));
			ps.setTimestamp(6, Timestamp.valueOf(person.getDateTimeCreate()));
			ps.setTime(7, Time.valueOf(person.getTimeToLunch()));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person findById(int id) {

		String sql = "SELECT * FROM persondao WHERE id = ?";

		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setAge(rs.getInt("age"));
				person.setSalary(rs.getBigDecimal("salary"));
				person.setPassport(rs.getString("passport"));
				person.setAddress(rs.getString("address"));

				return person;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Person> findAll() {

		List<Person> persons = new ArrayList<>();

		String sql = "SELECT * FROM persondao";

		try (Connection connection = DBConnection.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(sql)) {

			while (rs.next()) {

				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setAge(rs.getInt("age"));
				person.setSalary(rs.getBigDecimal("salary"));
				person.setPassport(rs.getString("passport"));
				person.setAddress(rs.getString("address"));

				persons.add(person);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persons;
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM persondao WHERE id = ?";

		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}