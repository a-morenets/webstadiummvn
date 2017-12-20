package ua.training;

import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entities.City;
import ua.training.model.services.CityService;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        CityService service = new CityService();
        System.out.println(service.getAllCities());

        DaoFactory factory = DaoFactory.getInstance();
        CityDao dao = factory.createCityDao();
        System.out.println(dao.findAll());
        City city = new City.Builder().setName("Kharkiv").build();
        dao.create(city);
        System.out.println(city);
    }

}
