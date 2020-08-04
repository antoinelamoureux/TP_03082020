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
import r.afpa.exercices.model.Book;

@Log4j
@Repository
public class BooksDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Book> getAllBooks() {
		String query = "Select * from books ";
		return jdbcTemplate.query(query, new BooksMapper());
	}

	public static final class BooksMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book livre = new Book();
			livre.setAuthorId(rs.getInt("authorid"));
			livre.setId(rs.getInt("id"));
			livre.setTitle(rs.getString("title"));
			return livre;
		}
	}

	public int setBooks(String title) {
		
		try {
			String query = "INSERT INTO BOOKS ( title) VALUES (?)";
			return jdbcTemplate.update(query, title);
		} catch (DuplicateKeyException e) {
			log.error(e);
			return 0;
		}

	}
	
	public int getNbBooks() {
		String query = "Select count(*) from books ";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	public int deleteBookByAuthor(String name) {
		 String query= "DELETE FROM AUTHOR WHERE NAME = ?";
		 return jdbcTemplate.update(query, name);
	}
	
	
	public String deleteBookByTitle(String title) {
		 String query= "DELETE FROM BOOKS WHERE TITLE = ?";
		 jdbcTemplate.update(query, title);
		 return title;
	}
	
	public List<Book> getBooksByAuthor(String name) {
		String query= "SELECT * FROM BOOKS INNER JOIN AUTHORS ON AUTHORS.ID = BOOKS.AUTHORID WHERE AUTHORS.NAME = ?";
		return jdbcTemplate.query(query, new Object[]{name}, new BooksMapper());
	}
}
