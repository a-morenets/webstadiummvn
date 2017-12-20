package ua.training.controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.model.entities.City;
import ua.training.model.services.CityService;

public class GetCities implements Command{
	
	private CityService cityService =CityService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<City> cities = cityService.getAllCities();
		request.setAttribute("citiesList", cities);
		return "/WEB-INF/view/cityList.jspx";
	}
	
}
