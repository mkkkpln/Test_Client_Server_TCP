package server.commands;

import server.managers.CollectionManager;
import server.utils.Environment;

public class Info implements ICommand {

    @Override
    public void execute(Environment environment, String message) {
        System.out.println("Below is all the information about the collection:");
        CollectionManager collectionManager = environment.getCollectionManager();
        System.out.println("Collection type: " + collectionManager.getClass() + ".");
        System.out.println("Date of collection initialization: " + collectionManager.getCreationDate() + ".");
        System.out.println("Number of elements: " + collectionManager.getPeople().size() + ".");
        environment.getPrintStream().println("Command finished successfully!");
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : output information about the collection (type, initialization date, number of items, etc.) to the standard output stream.";
    }
}
