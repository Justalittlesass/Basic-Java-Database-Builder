/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author c_bar
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;
import java.sql.PreparedStatement;
public class Database {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
      // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "username";
   static final String PASS = "password";
 
   
    public static void main(String[] args) throws SQLException {
        
  Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
      //STEP 4: Execute a query
      System.out.println("Creating database...");

      stmt = conn.createStatement();
     
      String sql = "CREATE DATABASE ANIMALS";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");
        String url = "jdbc:mysql://localhost/animals?user=root&password=pass";
        String username = "root";
        String password = "pass";
       Connection connection = DriverManager.getConnection(url, username, password);
      
      Statement info = connection.createStatement();


      
      String tbl = "CREATE TABLE animals"+
                    "(name VARCHAR (255)," +
                    "class VARCHAR (255)," +
                    "diet VARCHAR (255)," + 
                    "movement VARCHAR (255));";
      
      info.executeUpdate(tbl);
      System.out.println("Table animals created...");
      
      char quit = 'Y';
        while (quit =='Y'){
            String ins = "INSERT INTO `animals`.`animals` (`name`, `class`, `diet`, `movement`)" + " VALUES (?, ?, ?, ?);";
      
            PreparedStatement add = connection.prepareStatement(ins);
      
            Scanner sc = new Scanner(System.in);
      
             System.out.println("Please enter animal name: ");
             String name = sc.next();
      
             System.out.println("What class of animal is "+ name+"? ");
             String clss = sc.next();
      
             System.out.println("What sort of diet does "+ name+" have?");
             String diet = sc.next();
      
              System.out.println("How does "+ name + " move?");
              String movement = sc.next();
      
              add.setString(1, name);
              add.setString(2, clss);
              add.setString(3, diet);
              add.setString(4, movement);
      
              add.executeUpdate();
      
              System.out.println("\n Would you like to enter another animal into the database? (Y/N)");
              String word = sc.next();
              word = word.toUpperCase();
              quit= word.charAt(0);
   
      
      
   }
      
      ResultSet rs = stmt.executeQuery("SELECT * FROM `animals`.`animals`");
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();
      String cnam = rsmd.getColumnName(1);
      String cnam1 = rsmd.getColumnName(2);
      String cnam2 = rsmd.getColumnName(3);
      String cnam3 = rsmd.getColumnName(4);
      
      System.out.print("\n"+cnam+" || "+cnam1+" || "+ cnam2+" || "+cnam3+"||\n");
      
      
      
      while(rs.next()){
          for(int i=1; i<=columnsNumber; i++)
          System.out.print(rs.getString(i)+ " || ");
            System.out.println();
      }
    

      
      
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
    }
}
