package ar.com.civilizations.repository;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jvnet.hk2.annotations.Service;

import ar.com.civilizations.mapper.WeatherMapper;
import ar.com.civilizations.model.DayWeather;

@Service
public class WeatherRepositoryImpl implements WeatherRepository {
	private SqlSessionFactory sqlSessionFactory;

	public WeatherRepositoryImpl() {
		this.sqlSessionFactory = DatabaseUtils.getInstance().getSqlSessionFactory();
	}

	@Override
	public void updateRainPeakDayWeather(DayWeather maxAreaDayWeather) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			mapper.updateRainPeakDayWeather(maxAreaDayWeather);
			sqlSession.commit();
		}
	}

	@Override
	public void saveDayWeather(DayWeather dayWeather) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			mapper.saveDayWeather(dayWeather);
			sqlSession.commit();
		}
	}

	@Override
	public DayWeather getDayWithRainPeakDayWeather() {
		DayWeather result = null;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			result = mapper.getDayWithRainfallPeakDayWeather();
		}

		return result;
	}

	@Override
	public DayWeather getDayWeatherById(Long dayWeatherId) {
		DayWeather result = null;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			result = mapper.getDayWeatherById(dayWeatherId);
		}

		return result;
	}

	@Override
	public DayWeather getDayWeatherByDate(Date dayWeatherDate) {
		DayWeather result = null;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			result = mapper.getDayWeatherByDate(dayWeatherDate);
		}

		return result;
	}
	
	@Override
	public DayWeather getDayWeatherByDay(Long dayWeatherDay) {
		DayWeather result = null;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			result = mapper.getDayWeatherByDay(dayWeatherDay);
		}

		return result;
	}
	
	@Override
	public Long getAmountOfRainyDays() {
		Long amountOfRainyDays = 0L;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			amountOfRainyDays = mapper.getAmountOfRainyDays();
		}

		return amountOfRainyDays;
	}
	
	@Override
	public Long getAmountOfDryDays() {
		Long amountOfRainyDays = 0L;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			amountOfRainyDays = mapper.getAmountOfRainyDays();
		}

		return amountOfRainyDays;
	}
	
	@Override
	public Long getAmountOfOptimalConditionDays() {
		Long amountOfOptimalConditionDays = 0L;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
			amountOfOptimalConditionDays = mapper.getAmountOfOptimalConditionDays();
		}

		return amountOfOptimalConditionDays;
	}
}
