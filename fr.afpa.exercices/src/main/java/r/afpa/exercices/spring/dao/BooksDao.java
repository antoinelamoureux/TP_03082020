package r.afpa.exercices.spring;

@Repository
public class CommentsDao {

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
		
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book livre = new Book();
			livre.setAuthorId(rs.getInt("authorid"));
			livre.setId(rs.getInt("id"));
			livre.setTitle(rs.getString("title"));
			return livre;
		}
	}

	/**
	 * nombre commentairs
	 * 
	 * @return
	 */
	public int getNbId() {
		String query = "Select count(*) from books ";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	public void setBooks(String user, String email, String url, String date, String summary, String Comments) {
		String query = "INSERT INTO books (authorid, id, title) values (?, ?, ?)";
		jdbcTemplate.update(query, user, email, url, date, summary, Comments);

	}
	
	public void deleCommentById(int id) {
		 String query= "delete from books where id = ?";
		 jdbcTemplate.update(query, id);
	}
	
	public void deleCommentByEmail(String email) {
		 String query= "delete from books where email = ?";
		 jdbcTemplate.update(query, email);
	}
	
	public void deleCommentByUserId(String myuser) {
		 String query= "delete from books where myuser = ?";
		 jdbcTemplate.update(query, myuser);
	}


}
