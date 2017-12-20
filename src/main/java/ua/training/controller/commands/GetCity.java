package ua.training.controller.commands;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.model.entities.City;
import ua.training.model.services.CityService;

public class GetCity implements Command {

	private CityService cityService =CityService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		int cityId = Integer.parseInt( path.replaceAll("\\D+", ""));
		Optional<City> city = cityService.getById(cityId);
		city.ifPresent( (city1) -> request.setAttribute("city", city1) );
		return "/WEB-INF/view/editCity.jspx";
	}


}
