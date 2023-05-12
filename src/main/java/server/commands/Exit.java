package server.commands;

import server.utils.Environment;

public class Exit implements ICommand{
    @Override
    public void execute(Environment environment, String message) {
        System.out.println("Program is finished.");
        System.exit(0);
        environment.getPrintStream().println("Command finished successfully!");
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit : terminate the program (without saving to a file)";
    }
}
