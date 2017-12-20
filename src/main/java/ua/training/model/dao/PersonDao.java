package ua.training.model.dao;

import java.util.Optional;

import ua.training.model.entities.Person;

/**
 * Created by Денис on 14.12.2016.
 */
public interface PersonDao extends GenericDao<PersonDao> {

	Optional<Person> getPersonByEmail(String email);
}
