package org.manuel.util;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/jakarta_curso?serverTimezone=America/Bogota";
    private static final String username = "root";
    private static final String password = "";
    private static BasicDataSource pool;

    //Singleton de pool a la base de datos.
    public static BasicDataSource getPool(){
        if(pool == null){
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getPool().getConnection();
    }




}
