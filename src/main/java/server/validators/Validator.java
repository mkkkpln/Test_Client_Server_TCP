package server.validators;

import common.data.Mood;
import server.utils.*;
import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Validator {
    private static final char maxLetter = 0x7a; // Максимальное значение, которое допустимо при вводе слова (код таблицы ASCII)
    private static final char minLetter = 0x41; // Минимальное значение, которое допустимо при вводе слова (код таблицы ASCII)

    public static Long keyParser(Environment environment, String word) throws WrongScriptException {
        long key;
        try {
            key = Long.parseLong(word);
        } catch (NumberFormatException e) {
            environment.getPrintStream().println("Invalid format\nCommand finished");
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            return null;
        }
        if(environment.getCollectionManager().findByKey(key)==null){
            environment.getPrintStream().println("No such element.\nCommand finished.");
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            return null;
        }
        return key;
    }

    public static Long newKeyParser(Environment environment, String word) throws WrongScriptException {
        long key;
        try {
            key = Long.parseLong(word);
        } catch (NumberFormatException e) {
            environment.getPrintStream().println("Invalid format\nCommand finished");
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            return null;
        }
        if(environment.getCollectionManager().findByKey(key)!=null){
            environment.getPrintStream().println("Element already exists.\nCommand finished.");
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            return null;
        }
        return key;
    }
    public static Long keyReaderParser(Environment environment) throws WrongScriptException {
        try {
            String line = environment.getBufferedReader().readLine().trim();
            return newKeyParser(environment, line);

        } catch (IOException e) {
            if(environment.getPointer()>1){
                throw new WrongScriptException();
            }
            return null;
        }
    }


    public static String nameParser(String name) throws WrongNameException {
        char[] chars = new char[name.length()];

        for (int i = 0; i <chars.length ; i++) {
            chars[i] = name.charAt(i);
            if(((chars[i] < minLetter) && (chars[i]> maxLetter) && chars[i]!=' ') ){
                throw new WrongNameException();
            }
        }

        return name;
    }


    public static boolean boolParser(String word) throws WrongHeroException {
        word = word.trim();
        if(word.equals("yes")){
            return true;
        }
        if(word.equals("no")){
            return false;
        }
        throw new WrongHeroException();
    }

    public static boolean parseBoolWithChecks(Environment environment) throws WrongScriptException, WrongArgumentException {
        boolean variable= false;
        for (int i = 0; i < 3; i++) {
            try {
                variable = Validator.boolParser(environment.getBufferedReader().readLine());
                return variable;
            } catch (WrongHeroException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("The answer is 'yes' or 'no' ");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            } catch (IOException exception) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().println("Command finished!!!");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
            }
        }
        return variable;
    }


    public static Integer integerParser(Environment environment) throws WrongScriptException, WrongArgumentException {
        BufferedReader in = environment.getBufferedReader();
        int y = 0;
        for (int i = 0; i < 3; i++) {
            try {
                if(i>0) {
                    environment.getPrintStream().println("Enter Integer");
                }
                String line = in.readLine();
                line.replace('.',',');
                Scanner tmp = new Scanner(line);
                y = tmp.nextInt();
                if (line.split(" ").length > 1) {
                    environment.getPrintStream().println("Try again!");
                    throw new IOException();

                }
                break;
            } catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }
        return y;
    }
    public static Float floatParser(Environment environment) throws WrongScriptException, WrongArgumentException {
        BufferedReader in = environment.getBufferedReader();
        float x = 0;
        for (int i = 0; i < 3; i++) {
            try {
                if(i>0) {
                    environment.getPrintStream().println("Enter Float");
                }
                String line = in.readLine();
                line.replace('.',',');
                Scanner tmp = new Scanner(line);
                x = tmp.nextFloat();
                if (line.split(" ").length > 1) {
                    environment.getPrintStream().println("Try again!");
                    throw new IOException();

                }
                break;
            } catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }
        return x;
    }

    public static String nameParser(Environment environment) throws WrongScriptException, WrongArgumentException {
        String name = "";

        for (int i = 0; i < 3; i++){
            try {
                name = environment.getBufferedReader().readLine();
                if(name == null || name.isEmpty() || name.split(" ").length == 0){
                    throw new IOException();
                }
                name = Validator.nameParser(name);
                return name;
            } catch (IOException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
                environment.getPrintStream().printf("Invalid input. You have %d attempts\n", 2-i);
            } catch (WrongNameException e) {
                environment.getPrintStream().println("Use only A-Z or a-z");
            }
        }
        return name;
    }

    public static Mood moodParser(Environment environment) throws WrongArgumentException, WrongScriptException, BuilderException {
        Mood mood = Mood.CALM;
        for (int i = 0; i < 3; i++) {
            try {
                String input = environment.getBufferedReader().readLine().trim();
                switch (input){
                    case  ("Sorrow"):
                        return Mood.SORROW;
                    case  ("sorrow"):
                        return Mood.SORROW;
                    case  ("1"):
                        return Mood.SORROW;
                    case ("Gloom"):
                        return Mood.GLOOM;
                    case ("gloom"):
                        return Mood.GLOOM;
                    case ("2"):
                        return Mood.GLOOM;
                    case ("Apathy"):
                        return Mood.APATHY;
                    case ("apathy"):
                        return Mood.APATHY;
                    case ("3"):
                        return Mood.APATHY;
                    case ("Calm"):
                        return Mood.CALM;
                    case ("calm"):
                        return Mood.CALM;
                    case ("4"):
                        return Mood.CALM;
                }
                mood = Mood.valueOf(input);
                break;
            } catch (IOException | NullPointerException | IllegalArgumentException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }
        return mood;
    }

    public static String liteNameParser(Environment environment) throws WrongScriptException, WrongArgumentException {
        String liteName = "";
        for (int i = 0; i < 3; i++){
            try {
                liteName = environment.getBufferedReader().readLine();
                if(liteName == null || liteName.isEmpty() || liteName.split(" ").length == 0){
                    throw new IOException();
                }
                return liteName;

            } catch (IOException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new WrongArgumentException();
                }
                environment.getPrintStream().printf("Invalid input. You have %d attempts\n", 2-i);
            }
        }
        return liteName;
    }
}