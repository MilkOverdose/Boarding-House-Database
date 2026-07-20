package com.example.boardinghouse;

import java.sql.Connection;

public interface IDatabaseConnection {
    Connection getConnection();
}