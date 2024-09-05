package com.example.toyproject1_wst;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectionTest{
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://13.125.104.251:3306/projectWST";
    private static final String USER = "root";
    private static final String PW = "gimpo123";

    @Test
    public void testConnection() {
        try {
            // JDBC 드라이버 로드
            Class.forName(DRIVER);

            // 데이터베이스 연결
            try (Connection conn = DriverManager.getConnection(URL, USER, PW)) {
                assertNotNull(conn, "Connection should not be null");
                System.out.println("Database connection successful: " + conn);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Database connection failed", e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC Driver not found", e);
        }
    }
}

