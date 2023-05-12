package server.commands;

import server.utils.Environment;
import server.utils.NoSuchCommandException;
import server.utils.WrongScriptException;

public interface ICommand {

    void execute(Environment environment, String message) throws WrongScriptException, NoSuchCommandException;
    String getName();
    String getDescription();
}

