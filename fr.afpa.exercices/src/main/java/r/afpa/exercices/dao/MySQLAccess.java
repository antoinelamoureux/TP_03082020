package r.afpa.exercices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import lombok.extern.log4j.Log4j;

@Log4j
public class MySQLAccess {
	
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
	private String url = "jdbc:mysql://localhost:3306/feedback?useSSL=false&serverTimezone=UTC";
	private String user = "testuser";
    private String password = "RjqpoN5oLmiD7nEJ";
    
    public void readDataBase() throws Exception {
    	log.info("Lecture dans la bbd");
        try {
            
        	// This will load the MySQL driver, each DB has its own driver
        				//com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Setup the connection with the DB
            connect = DriverManager.getConnection(url, user, password);
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            //readVersion();

            //readTableComments();

            insertTableComments();

            //selectTableComments();
            
            //removeTableComments();
            
            //selectAllTableComments();
            

        } catch (Exception e) {
        	log.error(e);
            throw e;
        } finally {
            close();
        }

    }

    

    
     private void readVersion() throws SQLException{
    	 log.info("Lecture de la version");
    	 
    	 ResultSet rs = statement.executeQuery("SELECT VERSION()");

             if (rs.next()) {
                 System.out.println("Version de mysql "+rs.getString(1));
             }
     }

	/**
     * 
     * @throws SQLException
     */
    private void insertTableComments(){
    	log.info("DEBUT: Insertion");
    	
    	try {
			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement("insert into  feedback.Comments values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Title");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Probleme de bdd"+e);
		}
    	
    	log.info("FIND: Insertion");
    }
    
    /**
     * 
     * @throws SQLException
     */
    private void readTableComments() throws SQLException {
    	// Result set get the result of the SQL query
        resultSet = statement.executeQuery("select * from feedback.Comments");
        writeResultSet(resultSet);
    }
    
    /**
     * 
     * @throws SQLException
     */
    private void selectTableComments() throws SQLException{
    	preparedStatement = connect.prepareStatement("SELECT myuser,  datum, COMMENTS from feedback.Comments");
        resultSet = preparedStatement.executeQuery();
      
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	//int id = resultSet.getInt("id");
            String user = resultSet.getString("myuser");
            //String website = resultSet.getString("webpage");
            //String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user+" Date: " + date+" Comment: " + comment);
       
            
            
        }
		
	}
    
    /**
     * 
     * @throws SQLException
     */
    private void removeTableComments()  throws SQLException{
    	// Remove again the insert comment
        preparedStatement = connect.prepareStatement("delete from feedback.Comments where myuser= ? ; ");
        preparedStatement.setString(1, "Test");
        preparedStatement.executeUpdate();
		
	}
    
    
    /**
     * 
     * @throws SQLException
     */
	private void selectAllTableComments() throws SQLException{
		resultSet = statement.executeQuery("select * from feedback.Comments");
        writeMetaData(resultSet);
		
	}

	/**
     * 
     * @param resultSet
     * @throws SQLException
     */
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }
    
  

    /**
     * 
     * @param resultSet
     * @throws SQLException
     */
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	//int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            System.out.println("Title: " + title);
       
            
            
        }
        
        
    }

    // You need to close the resultSet
    private void close() {
    	log.info("Fermeture de la connection a la bbd");
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    

}
