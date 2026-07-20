package com.example.boardinghouse;

import java.io.*;

public class SessionManager {

    private static final String SESSION_FILE = "session.dat";

    // CREATE — called after successful login
    public static void createSession(Session session) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(session);
            System.out.println("Session created for: " + session.getUsername());
        } catch (IOException e) {
            System.out.println("Failed to create session: " + e.getMessage());
        }
    }

    // READ — used to check if someone is currently logged in
    public static Session getSession() {
        File file = new File(SESSION_FILE);
        if (!file.exists()) {
            return null; // no active session
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSION_FILE))) {
            return (Session) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to read session: " + e.getMessage());
            return null;
        }
    }

    // DELETE — called on logout
    public static void clearSession() {
        File file = new File(SESSION_FILE);
        if (file.exists()) {
            boolean deleted = file.delete();
            System.out.println(deleted ? "Session file deleted." : "Failed to delete session file.");
        }
    }

    // Quick check — is anyone logged in?
    public static boolean isLoggedIn() {
        return getSession() != null;
    }
}