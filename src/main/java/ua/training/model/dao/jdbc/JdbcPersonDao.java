package ua.training.model.dao.jdbc;

import ua.training.model.dao.PersonDao;
import ua.training.model.entities.Person;
import ua.training.model.entities.Person.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Денис on 14.12.2016.
 */
public class JdbcPersonDao implements PersonDao {
    
	private static final String SELECT_PERSON_BY_LOGIN =
			"SELECT * FROM person WHERE lower(email) = ?";
	private Connection connection;
	
	
	JdbcPersonDao(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
    public Optional<PersonDao> find(int id) {
        return null;
    }

    @Override
    public List<PersonDao> findAll() {
        return null;
    }

    @Override
    public void create(PersonDao personDao) {

    }

    @Override
    public void update(PersonDao personDao) {

    }

    @Override
    public void delete(int id) {

    }

	@Override
	public Optional<Person> getPersonByEmail(String email) {
		Optional<Person> result = Optional.empty();
		
		try(PreparedStatement query =
			connection.prepareStatement(SELECT_PERSON_BY_LOGIN) ){
			query.setString( 1, email.toLowerCase() );
			ResultSet rs = query.executeQuery();
			if( rs.next() ){
				Person person = getPersonFromResultSet(rs);
				result = Optional.of(person);
			}
			
		}catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		
        return result;

	}

	private Person getPersonFromResultSet(ResultSet rs) throws SQLException {
		Person person = new Person.Builder()
		       .setId(rs.getInt("id"))
		       .setName(rs.getString("name"))
		       .setEmail(rs.getString("email"))
		       .setRole(Role.valueOf(rs.getString("role")))
		       .setPassword(rs.getString("password"))
		       .setBirthdate( rs.getTimestamp("birthdate") , rs.wasNull()
		    		           )
		       .build();
		return person;
	}
}
