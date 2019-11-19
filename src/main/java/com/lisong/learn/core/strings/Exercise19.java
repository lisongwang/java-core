package com.lisong.learn.core.strings;

import com.lisong.learn.core.strings.regex.RegexCommand;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 17, exercise 18, exercise 19.
 */
public class Exercise19 {

    public static void main(String[] args) {

        if(args.length < 2) {
            print("Usage: java Exercise19 file command"); // command: com.lisong.learn.core.strings.regex.Comments
            System.exit(0);
        }

        try {
            /* load command from command line */
            Class clazz = Class.forName(args[1]);
            Object obj = clazz.newInstance();
            if(obj instanceof RegexCommand) {
                RegexCommand command = (RegexCommand)obj;
                command.execute(args[0]);
            }else {
                print("Command class must implement interface RegexCommand!");
                System.exit(1);
            }
        }catch(ClassNotFoundException e) {
            print("Command class can't be found!");
            System.exit(1);
        }catch(InstantiationException e) {
            print("Command class can't be instantiation!");
            System.exit(1);
        }catch(IllegalAccessException e) {
            print("Command class can't be access!");
            System.exit(1);
        }
    }
}