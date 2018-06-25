package ar.com.civilizations.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.h2.jdbcx.JdbcDataSource;
import org.jvnet.hk2.annotations.Service;

import ar.com.civilizations.model.DayWeather;

@Service
public class WeatherRepositoryImpl implements WeatherRepository {

	@Override
	public void updateRainPeakDayWeather(DayWeather maxAreaDayWeather) {
		// TODO Auto-generated method stub
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(" jdbc:h2:mem:civilizations");
		ds.setUser("sa");
		ds.setPassword("sa");

		try {
			Connection conn = ds.getConnection();
		} catch (SQLException s) {

		}
	}

	@Override
	public DayWeather saveDayWeather(DayWeather dayWeather) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DayWeather getDayWeather(Long dayWeatherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DayWeather getDayWeather(Date dayWeatherDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
