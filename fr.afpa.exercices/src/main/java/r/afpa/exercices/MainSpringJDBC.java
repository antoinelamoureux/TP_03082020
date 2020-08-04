package r.afpa.exercices;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j;
import r.afpa.exercices.spring.dao.BooksDao;
import r.afpa.exercices.model.Book;
import r.afpa.exercices.spring.dao.AuthorDao;
import r.afpa.exercices.spring.config.AppConfiguration;

@Log4j
public class MainSpringJDBC {
	public static void main(String[] args) {

		// Creating a Context Application object by reading
		// the configuration of the 'AppConfiguration' class.
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		AuthorDao authorDao = (AuthorDao) ctx.getBean("authorDao");
		
		log.info("Nombres de livres: ");
		System.out.println(booksDao.getNbBooks());
		
		log.info("Liste des livres: ");
		List<Book> books = booksDao.getAllBooks();
		for (Book book : books) {
			System.out.println(book);
		}
		
		log.info("Auteur ajouté: ");
		System.out.println(authorDao.setAuthor("Sabri Louatah"));
		
		log.info("Livres ajoutés: ");
		System.out.println(booksDao.setBooks("Les Sauvages Tome 1"));
		System.out.println(booksDao.setBooks("Les Sauvages Tome 2"));
		System.out.println(booksDao.setBooks("Les Sauvages Tome 3"));
		System.out.println(booksDao.setBooks("Les Sauvages Tome 4"));
		
		log.info("Livres supprimés: ");
		System.out.println(booksDao.deleteBookByTitle("Les Sauvages Tome 1"));
		System.out.println(booksDao.deleteBookByTitle("Les Sauvages Tome 4"));
		
		log.info("Livres supprimés: ");
		System.out.println(authorDao.deleteByAuthor("Honore de Balzac"));
	}
}
