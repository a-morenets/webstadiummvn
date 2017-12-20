package ua.training.controller.commands;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.model.entities.Person;
import ua.training.model.services.PersonService;

public class Login implements Command {

	
	public static final String PARAM_LOGIN = "login";
	
	public static final String PARAM_PASSWORD ="password";
	
	private PersonService personService = PersonService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageToGo = "/index.jsp";
		String email = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		if( email != null && password != null ){
			Optional<Person> person;
			person = personService.login(email, password);
			/*Consumer<Person> sessionSetter 
			   = pers -> request
			            .getSession()
			            .setAttribute("user", pers );
			person.ifPresent(sessionSetter);
			
			person.ifPresent(pers -> request
			            .getSession()
			            .setAttribute("user", pers ));
			*/
			if( person.isPresent()  ){
				request.getSession().setAttribute("user", person.get());
			    pageToGo = "/rest/city";
			}
			
		}
		return pageToGo;
	}

}
