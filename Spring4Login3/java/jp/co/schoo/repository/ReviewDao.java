package jp.co.schoo.repository;

import java.sql.ResultSet;

//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//
import com.mysql.jdbc.PreparedStatement;

@Repository
public class ReviewDao {
//
	@Autowired
	JdbcTemplate data;

	private final String INSERT_REVIEW_QUERY =
			"insert into review(title, description) values (?, ?)";

	private ResultSet  rs  = null;
	private PreparedStatement ps = null;

	public void review(String title, String description) {

		try {

			// SQL文を生成
			data.update(INSERT_REVIEW_QUERY, new Object[]{title, description});


		}
			catch(DataAccessException ce) {


				ce.printStackTrace();
		}
	}

}


