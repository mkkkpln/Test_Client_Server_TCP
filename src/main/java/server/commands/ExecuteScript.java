package server.commands;

import server.utils.Environment;
import server.utils.IO;
import server.utils.NoSuchCommandException;

import java.util.ArrayList;

public class ExecuteScript implements ICommand {

    private final ArrayList<String> historyCallsFile = new ArrayList<>();

    @Override
    public void execute(Environment environment, String message) throws NoSuchCommandException {
        IO.scriptReader(environment, message, historyCallsFile);
    }

    @Override
    public String getName() {
        return "executeScript";
    }

    @Override
    public String getDescription() {
        return "executeScript fileName : read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode";
    }
}