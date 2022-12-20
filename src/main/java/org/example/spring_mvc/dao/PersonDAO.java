package org.example.spring_mvc.dao;

import org.example.spring_mvc.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
	private static int PEOPLE_COUNT;

	private static final String URL = "jdbc:postgresql://localhost:5432/alishev";
	public static final String USERNAME = "sahmanmagomadov";
	public static final String PASSWORD = "";

	private static Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Person> index() throws SQLException {
		List<Person> people = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from person");

		while (resultSet.next()) {
			Person person = new Person();

			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setAge(resultSet.getInt("age"));
			person.setEmail(resultSet.getString("email"));

			people.add(person);
		}

		return people;
	}

	public Person show(int id) {
//		return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
	}

	public void save(Person person) throws SQLException {
		Statement statement = connection.createStatement();

		String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
				"'," + person.getAge() + ",'" + person.getEmail() + "')";

		statement.executeUpdate(SQL);
	}

	public void update(int id, Person updatedPerson) {
//		Person personToBeUpdated = show(id);
//
//		personToBeUpdated.setName(updatedPerson.getName());
//		personToBeUpdated.setAge(updatedPerson.getAge());
//		personToBeUpdated.setEmail(updatedPerson.getEmail());
	}

	public void delete(int id) {
		people.removeIf(p -> p.getId() == id);
	}
}
