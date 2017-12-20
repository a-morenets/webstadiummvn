package ua.training.model.dao.jdbc;

import ua.training.model.dao.CityDao;
import ua.training.model.entities.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Денис on 14.12.2016.
 */
public class JdbcCityDao implements CityDao {

    private static final String SELECT_FROM_CITY = "SELECT * FROM city";
    private static final String SELECT_FROM_CITY_BY_ID = "SELECT * FROM city WHERE city_id = ?";
    private static final String CITY_ID = "city_id";
    private static final String NAME = "name";
    private Connection connection;

    public JdbcCityDao() {
    }

    JdbcCityDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<City> findByName(String name) {
        return null;
    }

    @Override
    public Optional<City> find(int id) {
        Optional<City> result = Optional.empty();
        try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_CITY_BY_ID)){
        	query.setInt( 1 , id);
        	ResultSet resultSet = query.executeQuery();
        	if (resultSet.next()) {
        		result = Optional.of( extractCityFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

	private City extractCityFromResultSet(ResultSet resultSet) throws SQLException {
		return new City.Builder()

		             .setId(resultSet.getInt(CITY_ID))
		             .setName(resultSet.getString(NAME) , resultSet.wasNull() )
		             .build();
	}

    @Override
    public List<City> findAll() {
        List<City> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_CITY)){

            while (resultSet.next()) {
                result.add( extractCityFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(City city) {

        try( PreparedStatement query =
                         connection.prepareStatement("INSERT INTO city (name) VALUES ( ? )"
                                 , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , city.getName());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                city.setId( keys.getInt(1) );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(City city) {

    }

    @Override
    public void delete(int id) {

    }
}
