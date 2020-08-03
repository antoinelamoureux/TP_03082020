package r.afpa.exercices.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
	
	   @Autowired
	 
	   public GreetingService() {
	 
	   }
	 
	   public void sayGreeting() {
	      
	       String greeting = "Hello Word";
	 
	       System.out.println("Greeting: " + greeting);
	   }
}