package server.utils;

import server.commands.ICommand;
import server.commands.Invoker;

import java.io.*;
import java.util.ArrayList;

public class IO {
    private static final char ctrlD = 0x4;
    private static final char ctrlC = 0x3;
    public static void commandReader(Environment environment, Invoker invoker)  {
        BufferedReader reader = environment.getBufferedReader();
        System.out.println("Type 'help' to see commands");
        while(true){
            System.out.print("-> ");
            try {
                String userTyping = reader.readLine();
                if(userTyping==null){
                    environment.getPrintStream().println("Finishing process.\nProgram finished");
                    System.exit(0);
                }
                for(int i = 0; i < userTyping.length(); i++){
                    if(userTyping.toCharArray()[i]==ctrlD || userTyping.toCharArray()[i]==ctrlC){
                        environment.getPrintStream().println("Finishing process.\nProgram finished");
                        System.exit(0);
                    }
                }
                ICommand tryCommand = null;
                for(ICommand command: environment.getAllCommands()){
                    if(command.getName().equals(userTyping.split(" ")[0])){
                        tryCommand = command;
                        break;
                    }
                }
                if(tryCommand==null){
                    environment.getPrintStream().println("No such command!");
                    continue;
                }
                invoker.executer(userTyping);
            }
            catch (IOException ex){
                System.out.println("Incorrect input and output");
            }
            catch (WrongScriptException ex) {
                System.out.println("Script failed");
            } catch (NoSuchCommandException e) {
                environment.getPrintStream().println("No command!!!");
            }
        }
    }
    public static void scriptReader(Environment environment, String message, ArrayList<String> historyCallsFile) throws NoSuchCommandException {
        incPointer(environment);
        Invoker invoker = new Invoker(environment, environment.getAllCommands());
        try{
            FileInputStream fileInputStream = new FileInputStream(message);
            Reader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            environment.setBufferedReader(bufferedReader);
            String userLine = bufferedReader.readLine();
            while (userLine != null && !userLine.equals("EOF") ){
                for(int i = 0; i < userLine.length(); i++){
                    if(userLine.toCharArray()[i]==ctrlD || userLine.toCharArray()[i]==ctrlC){
                        environment.getPrintStream().println("Finishing process.\nProgram Finished"
                        );System.exit(0);
                    }
                }
                ICommand tryCommand = null;
                for(ICommand command: environment.getAllCommands()){
                    if(command.getName().equals(userLine.split(" ")[0])){
                        tryCommand = command;
                        break;
                    }
                }
                if(tryCommand==null){
                    throw new WrongScriptException();
                }
                if(historyCallsFile.contains(userLine)){
                    throw new RecursionScriptException();
                }
                historyCallsFile.add(userLine);
                invoker.executer(userLine);
                historyCallsFile.remove(historyCallsFile.size()-1);
                userLine = bufferedReader.readLine();
            }

            environment.setPointer(environment.getPointer()-1);

        } catch (FileNotFoundException e) {
            environment.getPrintStream().println("File not found\nCommand finished unsuccessfully!");
            environment.setPointer(environment.getPointer()-1);
        } catch (IOException e) {
            environment.setPointer(environment.getPointer()-1);
            throw new RuntimeException(e);
        } catch (WrongScriptException e) {
            environment.getPrintStream().println("Your script has errors!");
        } catch (RecursionScriptException e) {
            environment.getPrintStream().println("Recursion found!");
            environment.setPointer(environment.getPointer()-1);
        }
    }

    public static void incPointer(Environment environment) {
        environment.setPointer(environment.getPointer()+1);
    }
}