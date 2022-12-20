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
	public List<Person> index() {
		List<Person> people = new ArrayList<>();

		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from person");

			while (resultSet.next()) {
				Person person = new Person();

				person.setId(resultSet.getInt("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));

				people.add(person);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return people;
	}

	public Person show(int id) {
		Person person = null;

		PreparedStatement preparedStatement =
				null;
		try {
			preparedStatement = connection.prepareStatement("select * from person where id=?");
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			resultSet.next();

			person = new Person();

			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setAge(resultSet.getInt("age"));
			person.setEmail(resultSet.getString("email"));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		return person;
	}

	public void save(Person person) {
		PreparedStatement preparedStatement =
				null;
		try {
			preparedStatement = connection.prepareStatement("insert into person values(1, ?, ?, ?)");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void update(int id, Person updatedPerson) {
		PreparedStatement preparedStatement =
				null;
		try {
			preparedStatement = connection.prepareStatement("update person set name=?, age=?, email=? where id=?");

			preparedStatement.setString(1,updatedPerson.getName());
			preparedStatement.setInt(2, updatedPerson.getAge());
			preparedStatement.setString(3, updatedPerson.getEmail());
			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		PreparedStatement preparedStatement =
				null;
		try {
			preparedStatement = connection.prepareStatement("delete from person where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
