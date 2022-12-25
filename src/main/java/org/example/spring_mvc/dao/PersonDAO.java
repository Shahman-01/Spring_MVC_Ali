package org.example.spring_mvc.dao;

import org.example.spring_mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static int PEOPLE_COUNT;


	public List<Person> index() {
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person show(int id) {
		return jdbcTemplate.query(
				"select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
				.stream().findAny().orElse(null);
	}

	public void save(Person person) {
		jdbcTemplate.update("insert into person(name, age, email) values (?, ?, ?)",
				person.getName(), person.getAge(), person.getEmail());
	}

	public void update(int id, Person updatedPerson) {
		jdbcTemplate.update("update person set name=?, age=?, email=? where id=?",
				updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
	}

	public void delete(int id) {
		jdbcTemplate.query("delete frim person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
	}
}
