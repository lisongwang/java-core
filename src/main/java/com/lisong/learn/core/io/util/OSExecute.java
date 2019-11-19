package com.lisong.learn.core.io.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OSExecute {

    public static List<String> command(String command) {
        boolean hasError = false;
        List<String> result = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = in.readLine()) != null)
                result.add(s);
            BufferedReader err = new BufferedReader((new InputStreamReader(process.getErrorStream())));
            while((s = err.readLine()) != null) {
                hasError = true;
                System.err.println(s);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(hasError)
            throw new OSExecuteException("Errors executing: " + command);
        return result;
    }
}