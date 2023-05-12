package server.commands;

import server.utils.Environment;

import java.util.SortedSet;
import java.util.TreeSet;

public class PrintAscending implements ICommand {
    @Override
    public void execute(Environment environment, String message) {
        SortedSet<Long> keys = new TreeSet<>(environment.getCollectionManager().getPeople().keySet());
        for (Long key : keys){
            environment.getPrintStream().println(environment.getCollectionManager().findByKey(key).toString());
        }

        environment.getPrintStream().println("Command finished successfully!");

    }

    @Override
    public String getName() {
        return "printAscending";
    }

    @Override
    public String getDescription() {
        return "printAscending : output the elements of the collection in ascending order";
    }
}
