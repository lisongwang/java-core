package com.lisong.learn.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.prefs.Preferences;

public class Exercise33 {

    public static void main(String[] args) throws IOException {
        Preferences prefs = Preferences.userNodeForPackage(Exercise33.class);
        String value = prefs.get("base directory", "None");
        System.out.println("Current value for base directory: " + value);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String newValue;
        do {
            System.out.print("Please enter a new value for base directory: ");
        }while ((newValue = br.readLine()) == null || "".equals(newValue));
        prefs.put("base directory", newValue.trim());
        System.out.println("New value stored successfully!");
    }
}