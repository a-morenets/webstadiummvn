package ua.training.model.services;

import java.util.Optional;

import ua.training.model.dao.DaoConnection;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PersonDao;
import ua.training.model.entities.Person;


public class PersonService {
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private static class Holder{
    	static final PersonService INSTANCE = new PersonService(); 
    }
    
    public static PersonService getInstance(){
    	return Holder.INSTANCE;
    }
    
    
    public Optional<Person> login (String email , String password){
    	try( DaoConnection connection = daoFactory.getConnection() ){
    	     connection.begin();
    		 PersonDao dao = daoFactory.createPersonDao(connection);
    	     return dao.getPersonByEmail(email)
    			  .filter( person-> password.equals(person.getPassword()));
    	}
    }
}
