package com.betterup.codingexercise.managers;

public interface DatabaseManager {
    void openDatabase(final String fileName);

    void closeDatabase();

    public boolean deleteDatabase();
}
