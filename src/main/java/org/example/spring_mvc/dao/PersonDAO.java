package org.example.spring_mvc.dao;

import org.example.spring_mvc.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
	private static int PEOPLE_COUNT;
	private List<Person> people;

	{
		people = new ArrayList<>();

		people.add(new Person(++PEOPLE_COUNT, "Tom"));
		people.add(new Person(++PEOPLE_COUNT, "Max"));
		people.add(new Person(++PEOPLE_COUNT, "Dima"));
		people.add(new Person(++PEOPLE_COUNT, "Gye"));
	}

	public List<Person> index() {
		return people;
	}

	public Person show(int id) {
		return people.stream()
				.filter(p -> p.getId() == id)
				.findAny()
				.orElse(null);
	}

	public void create(String name) {
		people.add(new Person(++PEOPLE_COUNT, name));
	}

	public void save(Person person) {
		person.setId(++PEOPLE_COUNT);
		people.add(person);
	}
}
