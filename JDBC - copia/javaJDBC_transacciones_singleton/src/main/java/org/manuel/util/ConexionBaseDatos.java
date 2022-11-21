package org.manuel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/jakarta_curso?serverTimezone=America/Bogota";
    private static final String username = "root";
    private static final String password = "";
    private static Connection connection;

    //Singleton de conexion a la base de datos.
    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(URL,username,password);
        }
        return connection;
    }


}
