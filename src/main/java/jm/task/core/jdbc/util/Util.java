package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static volatile Util instance;
    private String username;
    private String dbURL;
    private String password;

    private Util() {
        username = "student";
        password = "student";
        dbURL = "jdbc:mysql://localhost:3306/schema113";
    }

    public static Util getInstance() {
        Util localInstance = instance;
        if(localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Util();
                }
            }
        }
        return localInstance;
    }

    public Connection connectToDb () throws SQLException{
        return DriverManager.getConnection(dbURL,username,password);
    }
}
