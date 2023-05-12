package server.commands;

import common.data.HumanBeing;
import common.data.Mood;
import server.utils.Environment;
import server.utils.WrongScriptException;

import java.util.*;

public class PrintFieldDescendingMood implements ICommand {

    @Override
    public void execute(Environment environment, String message) throws WrongScriptException {

        List<Mood> moodList = new ArrayList<>(List.of(Mood.values()));
        moodList.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));

        for (Mood mood : moodList) {
            for (HashMap.Entry<Long, HumanBeing> entry : environment.getCollectionManager().getPeople().entrySet()) {
                if (entry.getValue().getMood().equals(mood)) {
                    environment.getPrintStream().println(entry.getValue().toString());
                }
            }
            environment.getPrintStream().println();
        }

        environment.getPrintStream().println("Command finished successfully");
    }

    @Override
    public String getName() {
        return "printFieldDescendingMood";
    }

    @Override
    public String getDescription() {
        return "printFieldDescendingMood : output the values of the mood field of all elements in descending order";
    }
}
