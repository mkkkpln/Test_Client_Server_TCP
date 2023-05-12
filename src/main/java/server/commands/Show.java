package server.commands;

import server.utils.Environment;

public class Show implements ICommand {
    @Override
    public void execute(Environment environment, String message) {
        if(!environment.getCollectionManager().getPeople().isEmpty()){
            environment.getPrintStream().println(environment.getCollectionManager().toString());
            environment.getPrintStream().println("Command finished successfully!");
        }
        else{
            environment.getPrintStream().println("Collection is empty!");
        }
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show : output to the standard output stream all the elements of the collection in a string representation";
    }
}

