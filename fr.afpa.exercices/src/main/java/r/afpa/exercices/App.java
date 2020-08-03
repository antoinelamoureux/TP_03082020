package r.afpa.exercices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j;
import r.afpa.exercices.bdd.mysql.MySQLAccess;
import r.afpa.exercices.model.Book;
import r.afpa.exercices.spring.config.AppConfiguration;

@Log4j
public class App {
	//@Log4j à la place de  : private static final Logger log = Logger.getLogger(AppMain.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		log.info("--------------BEANS Mysql ---------------");
		MySQLAccess m = (MySQLAccess) ctx.getBean("mysql");
		try {
			m.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		lancerMysql();
	}
	
	
	private static void lancerMysql() {
		log.info("Mysql");

		try {
			MySQLAccess dao = new MySQLAccess();
			dao.readDataBase();
		} catch (Exception e) {
			log.error(e);
		}

	}
	
	
	
	private static void affichePerson() {
		
		log.debug("entree dans la methode main");
		
		log.info("Creation Livre");
		Book book = Book.builder()
				.name("albert")
				.title("Fred")
				.id(13)
				.build();
				
		log.info("Book: "+book.toString());
	}
}