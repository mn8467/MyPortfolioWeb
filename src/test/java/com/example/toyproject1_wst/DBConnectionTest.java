package com.example.toyproject1_wst;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest{
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://13.125.104.251:3306/projectWST";
    private static final String USER = "root";
    private static final String PW = "gimpo123";

    @Test
    public void testConnection() throws Exception{
        Class.forName(DRIVER);

        try(Connection conn = DriverManager.getConnection(URL, USER, PW)){

            System.out.println(conn); // 콘솔창에서 연결정보를 출력하여 확인한다.

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

