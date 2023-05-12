package server.commands;

import server.utils.Environment;
import server.utils.WrongScriptException;
import server.validators.Validator;


public class RemoveKey implements ICommand {
    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {
        if(Validator.keyParser(environment, message)==null){
            System.out.println("Key is null - error.");
            return;
        }
        long id = Validator.keyParser(environment, message);
        environment.getCollectionManager().removeById(id);
        environment.getPrintStream().println("Command finished successfully!");

    }

    @Override
    public String getName() {
        return "removeKey";
    }

    @Override
    public String getDescription() {
        return "removeKey null : delete an item from the collection by its key";
    }
}