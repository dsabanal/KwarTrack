/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labopr;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAuth {
    private static final String FILE = "users.txt";

    // --- Sign up ---
    public static boolean signUp(String username, String password) {
        if (userExists(username)) return false;

        String hashed = hashPassword(password);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(username + ":" + hashed);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // --- Login ---
    public static boolean login(String username, String password) {
        String hashed = hashPassword(password);
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(username + ":" + hashed)) return true;
            }
        } catch (IOException e) {
            // file may not exist yet
        }
        return false;
    }
    // --- Check if username exists ---
    private static boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ":")) return true;
            }
        } catch (IOException e) {
            // file may not exist yet
        }
        return false;
    }
    // --- Hash password ---
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

//are theyre changes at all?