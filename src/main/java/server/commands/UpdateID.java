package server.commands;

import common.data.HumanBeing;
import server.utils.BuilderException;
import server.utils.Environment;
import server.utils.WrongScriptException;
import server.validators.HumanBuilder;
import server.validators.Validator;

public class UpdateID implements ICommand {

    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {

        // Переводим строку в число
        if(Validator.keyParser(environment, message)==null){
            return;
        }
        long id = Validator.keyParser(environment, message);
        if(environment.getCollectionManager().findById(id)==null){
            environment.getPrintStream().println("No such element \nCommand finished unsuccessfully!");
            return;
        }

        HumanBuilder humanBuilder = new HumanBuilder();
        HumanBeing human = null;
        try {
            human = humanBuilder.buildHuman(environment);
        } catch (BuilderException e) {
            return;
        }
        human.setId(id);
        environment.getCollectionManager().removeById(id);
        environment.getCollectionManager().addPerson(human);
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : update the value of a collection item whose id is equal to the specified one";
    }
}