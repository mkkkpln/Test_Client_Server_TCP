package server.commands;

import common.data.HumanBeing;
import server.utils.BuilderException;
import server.utils.Environment;
import server.utils.WrongScriptException;
import utils.*;
import server.validators.HumanBuilder;


public class Insert implements ICommand {
    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {
        HumanBuilder humanBuilder = new HumanBuilder();
        HumanBeing human = null;
        try {
            human = humanBuilder.buildHuman(environment);
        } catch (BuilderException e) {
            return;
        }
        environment.getCollectionManager().addPerson(human);
        environment.getPrintStream().println("Command finished successfully!");
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "insert : add a new element with the specified key";
    }
}