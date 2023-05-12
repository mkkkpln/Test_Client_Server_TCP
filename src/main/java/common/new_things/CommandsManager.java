package common.new_things;

import common.request.Request;
import common.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandsManager implements ICommandManager {

    private HashMap<String, AbstractCommand> commandsMapClient;

    private HashMap<String, AbstractCommand> commandsMapServer;


    public CommandsManager(){
        this.commandsMapClient = new HashMap<>();
        this.commandsMapServer = new HashMap<>();
    }

    @Override
    public Map<String, ? extends AbstractCommand> getCommandsClient() {
        return commandsMapClient;
    }
    @Override
    public Map<String, ? extends AbstractCommand> getCommandsServer() {
        return commandsMapServer;
    }
    @Override
    public Request executeClient(String cmAndArgs) {
        String[] args = cmAndArgs.trim().split("\\s+");
        String commandName = args[0];
        AbstractCommand command;
        int commandArgumentsStartPosition = 1;
        args = Arrays.copyOfRange(args, commandArgumentsStartPosition, args.length);
        command = commandsMapClient.get(commandName);
        return command.toRequest(args);
    }

    @Override
    public Response executeServer(Request request) {
//        return commandsMapServer.get(request.getCommand().getName()).execute(request.getRequestBody());
        return commandsMapServer.get(request.getCommand().getName());
    }



//    public void fillClientMap(){
//        HashMap<String, new_things.AbstractCommand> commandsMapClient = Arrays.asList(
//                new Info()
//        ).stream().collect(Collectors.toMap(new_things.AbstractCommand::getCommandName,
//                Function.identity(),
//                (existing, replacement) -> existing,
//                HashMap::new));
//        this.commandsMapClient = commandsMapClient;
//    }


//    public void fillServerMap(CollectionManager manager){
//        HashMap<String, new_things.AbstractCommand> commandsMapServer = Arrays.asList(
//                new Info(manager)
//        ).stream().collect(Collectors.toMap(new_things.AbstractCommand::getCommandName,
//                Function.identity(),
//                (existing, replacement) -> existing,
//                HashMap::new));
//        this.commandsMapServer = commandsMapServer;
//    }


}
