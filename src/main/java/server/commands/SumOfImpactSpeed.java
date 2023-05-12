package server.commands;

import common.data.HumanBeing;
import server.utils.Environment;

import java.util.Map;

public class SumOfImpactSpeed implements ICommand {
    @Override
    public void execute(Environment environment, String message) {
        double sum = 0;
        for (Map.Entry<Long, HumanBeing> entry : environment.getCollectionManager().getPeople().entrySet()){
            sum += entry.getValue().getImpactSpeed();
        }
        environment.getPrintStream().printf("Sum of impact speed is : %.3f\n", sum);
        environment.getPrintStream().println("Command finished successfully!");
    }

    @Override
    public String getName() {
        return "sumOfImpactSpeed";
    }

    @Override
    public String getDescription() {
        return "sumOfImpactSpeed : print the sum of the values of the impactSpeed field for all elements of the collection";
    }
}