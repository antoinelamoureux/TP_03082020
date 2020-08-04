package r.afpa.exercices.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j;
import r.afpa.exercices.model.Author;



@Log4j
@Repository
public class AuthorDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final class AuthorMapper implements RowMapper<Author> {
		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author auteur = new Author();
			auteur.setId(rs.getInt("id"));
			auteur.setName(rs.getString("name"));
			return auteur;
		}
	}
	
	public List<Author> getAllAuthors() {
		String query = "SELECT * FROM AUTHORS ";
		return jdbcTemplate.query(query, new AuthorMapper());
	}

	public int setAuthor(String name) {
		try {
			String query = "INSERT INTO AUTHORS (name) VALUES(?)";
			return jdbcTemplate.update(query, name);
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			log.error(e);
			return 0;
		}
	}
	
	public int getAuthor(String name) {
		String query = "SELECT * FROM AUTHORS WHERE NAME = ?";
		return jdbcTemplate.update(query, name);
	}
	
	public int deleteByAuthor(String name) {
		 String query= "DELETE BOOKS, AUTHORS FROM BOOKS INNER JOIN AUTHORS ON AUTHORS.ID = BOOKS.AUTHORID WHERE AUTHORS.NAME = ?";
		 return jdbcTemplate.update(query, name);
	}
	
}
