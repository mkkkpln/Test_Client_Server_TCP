package server.commands;

import server.utils.Environment;
import server.utils.WrongScriptException;
import server.utils.XmlUtil;

public class Save implements ICommand {
    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {

        if (XmlUtil.saveXmlFile(environment)) {
           environment.getPrintStream().println("Collection is saved. Command finished successfully.");
           }
        else {
            environment.getPrintStream().println("Collection is not saved. Command finished unsuccessfully");
        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save : save the collection to the file";
    }
}
