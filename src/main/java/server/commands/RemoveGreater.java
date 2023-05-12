package server.commands;

import common.data.HumanBeing;
import server.utils.Environment;
import server.utils.WrongScriptException;
import server.validators.Validator;
import utils.*;
import validators.*;

import java.util.HashMap;

public class RemoveGreater implements ICommand {
    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {
        if(Validator.keyParser(environment, message)==null){
            System.out.println("Key is null - error.");
            return;
        }
        long id = Validator.keyParser(environment, message);

        for (HashMap.Entry<Long, HumanBeing> entry : environment.getCollectionManager().getPeople().entrySet()){
            if(entry.getKey()>id){
                environment.getCollectionManager().removeById(entry.getKey());
            }
        }
        environment.getPrintStream().println("Command finished successfully!");

    }

    @Override
    public String getName() {
        return "removeGreater";
    }

    @Override
    public String getDescription() {
        return "removeGreater null : remove all items from the collection that exceed the specified";
    }
}

