package ar.com.civilizations.repository;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseUtils {
	private static DatabaseUtils databaseUtils;
	private SqlSessionFactory sqlSessionFactory;
	
	private DatabaseUtils() {
	}
	
	public static DatabaseUtils getInstance() {
		if(databaseUtils == null) {
			databaseUtils = new DatabaseUtils();
		}
		
		return databaseUtils;
	}
	
	public void initDatabase() {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// throw new ;
		}
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
