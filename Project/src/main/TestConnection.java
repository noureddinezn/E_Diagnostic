package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestConnection {
    public static void main(String[] args) {
        
        System.out.println("Attempting to connect to the E_Diagnostic_serv database...");
        
        try {
            // 1. Read configuration from hibernate.cfg.xml
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            
            // 2. Open a database session
            Session session = factory.openSession();
            
            // If the code reaches this line without throwing an exception, the connection is successful
            System.out.println("🎉 Success! Connection to PostgreSQL is working 100%");
            System.out.println("You can now continue working on your medical tele-expertise platform.");
            
            // 3. Close the session and factory to free up resources
            session.close();
            factory.close();
            
        } catch (Exception e) {
            // If there is an issue with the credentials, URL, or server status, it will be caught here
            System.out.println("❌ Connection failed! See the error details below:");
            e.printStackTrace();
        }
    }
}