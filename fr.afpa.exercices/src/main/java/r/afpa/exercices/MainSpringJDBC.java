package r.afpa.exercices;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j;
import r.afpa.exercices.spring.dao.AuthorDao;
import r.afpa.exercices.spring.config.AppConfiguration;

@Log4j
public class MainSpringJDBC {
	public static void main(String[] args) {

		// Creating a Context Application object by reading
		// the configuration of the 'AppConfiguration' class.
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		//CustomerDao customer= (CustomerDao) ctx.getBean("customerDao");
		//customer.createTable();
		
		log.info("list des commentaires");
		AuthorDao authorDao = (AuthorDao) ctx.getBean("authorDao");
		
		List<AuthorDao> authors = authorDao.getAllComments();
		for (AuthorDao author : authors) {
			System.out.println(author);
		}
		
		log.info("nombres des commentaires");
		System.out.println(cmntsDao.getNbComments());
		
		log.info("insert livres");
		AuthorDao.setBooks();
		AuthorDao.setBooks("mimi", "mimi@yahoo.fr", "http://www.mimi.com", "2009-09-14 10:33:11", "timi Summary", "mimi first comment");
		
		
		log.info("delet commentaires");
		AuthorDao.deleCommentById(19);
		AuthorDao.deleCommentByName("picsou");
	}
}
