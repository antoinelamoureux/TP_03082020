package r.afpa.exercices.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class AuthorDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Comments> getAllComments() {
		String query = "Select * from Comments ";
		return jdbcTemplate.query(query, new CommentsMapper());
	}

	private static final class CommentsMapper implements RowMapper<Comments> {
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author auteur = new Author();
			auteur.setName(rs.getString("name"));
			auteur.setId(rs.getInt("id"));
			return auteur;
		}
	}

	/**
	 * nombre commentairs
	 * 
	 * @return
	 */
	public int getNbId() {
		String query = "Select count(*) from authors";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	public void setBooks(String name, int id) {
		String query = "INSERT INTO authors (name, id) values (?, ?)";
		jdbcTemplate.update(query, user, email, url, date, summary, Comments);

	}
	
	public void deleteAuthorById(int id) {
		 String query= "delete from authors where id = ?";
		 jdbcTemplate.update(query, id);
	}
	
	public void deleteAuthorByName(String name) {
		 String query= "delete from authors where name = ?";
		 jdbcTemplate.update(query, email);
	}
	

}
