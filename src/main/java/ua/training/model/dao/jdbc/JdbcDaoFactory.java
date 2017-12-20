package ua.training.model.dao.jdbc;

import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoConnection;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PersonDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.Driver;

/**
 * Created by Денис on 14.12.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

   // private Connection connection;
    private static final String DB_URL = "url";
    
   // @Resource(name="java:comp/env/jdbc/footbal")
	private DataSource dataSource;

    public JdbcDaoFactory() {
        try{
//            InputStream inputStream =
//                DaoFactory.class.getResourceAsStream(DB_FILE);
//            Properties dbProps = new Properties();
//            dbProps.load(inputStream);
//            String url = dbProps.getProperty(DB_URL);
            //new Driver();
            //connection = DriverManager.getConnection(url , dbProps);
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/football");
            //connection = dataSource.getConnection();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public CityDao createCityDao() {
        return null;//new JdbcCityDao(connection);
    }

    @Override
    public PersonDao createPersonDao() {
        return null;//new JdbcPersonDao(connection);
    }

	@Override
	public DaoConnection getConnection() {
			try {
				return new JdbcDaoConnection( dataSource.getConnection() );
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public CityDao createCityDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 
		return new JdbcCityDao( sqlConnection );
				
	}

	@Override
	public PersonDao createPersonDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcPersonDao(sqlConnection);
	}


}
