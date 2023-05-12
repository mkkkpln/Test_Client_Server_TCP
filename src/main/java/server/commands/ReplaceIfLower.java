package server.commands;

import common.data.HumanBeing;
import server.utils.BuilderException;
import server.utils.Environment;
import server.utils.WrongScriptException;
import utils.*;
import server.validators.HumanBuilder;
import server.validators.Validator;

public class ReplaceIfLower implements ICommand {
    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {
        if(Validator.keyParser(environment, message)==null){
            return;
        }
        long id = Validator.keyParser(environment, message);


        environment.getPrintStream().println("Enter new Id");
        long newKey = Validator.keyReaderParser(environment);

        if(id>newKey){
            HumanBuilder humanBuilder = new HumanBuilder();
            HumanBeing human = null;
            try {
                human = humanBuilder.buildHuman(environment);
            } catch (BuilderException e) {
                return;
            }
            human.setId(newKey);
            environment.getCollectionManager().removeById(id);
            environment.getCollectionManager().addPerson(human);
            environment.getPrintStream().println("Command finished!!!");
        }
        else {
            environment.getPrintStream().println("id <= new value\nCommand finished. ");
            return;
        }
    }

    @Override
    public String getName() {
        return "replaceIfLower";
    }

    @Override
    public String getDescription() {
        return "replaceIfLower null {element} : replace the value by key if the new value is less than the old one";
    }
}

