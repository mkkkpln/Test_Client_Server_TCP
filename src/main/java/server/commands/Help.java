package server.commands;

import server.utils.Environment;

public class Help implements ICommand{

    @Override
    public void execute(Environment environment, String message) {
        for (int i = 0; i <environment.getAllCommands().length; i++){
            environment.getPrintStream().println(environment.getAllCommands()[i].getDescription());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help : output help for available commands";
    }

}
