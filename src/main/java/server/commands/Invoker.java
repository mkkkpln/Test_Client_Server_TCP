package server.commands;

import server.utils.Environment;
import server.utils.NoSuchCommandException;
import server.utils.WrongScriptException;

import java.util.HashMap;

public class Invoker {
    private Environment environment;
    private HashMap<String, ICommand> commandHashMap = new HashMap<>();

    /**
     * Class Invoker contains all the logic for linking all classes of the programme.
     * Constructor is adding all commands in ICommand array.
     * @param environment - среда, где хранится история, все команды, коллекция, поток вывода на экран
     * @param commands - команды
     */

    public Invoker(Environment environment, ICommand[] commands){
        this.environment = environment;
        for (ICommand command:commands) {
            commandHashMap.put(command.getName(), command);
        }
    }


    /**
     * User word processing.
     * @param message - сообщение, введённое в консоль
     */
    public void executer(String message) throws WrongScriptException, NoSuchCommandException {
        if (message.split(" ").length > 1) {
            System.setOut(System.out);
            String[] mem = message.split(" ");
            String messageNext = "";
            for (int i = 0; i < mem.length; i++) {
                if (i > 0 && i != mem.length - 1) {
                    messageNext += mem[i] + " ";
                } else {
                    if (i > 0) {
                        messageNext += mem[i];
                    }
                }
            }
            commandHashMap.get(message.split(" ")[0]).execute(environment, messageNext);
        } else {
            System.setOut(System.out);
            commandHashMap.get(message.trim()).execute(environment,"");
        }
    }

}
