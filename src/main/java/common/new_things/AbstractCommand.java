package common.new_things;

import common.request.Request;
import common.request.RequestBody;
import common.request.RequestFactory;
import common.response.Response;

abstract public class AbstractCommand {
    private final String commandName;

    protected AbstractCommand(String commandName) {
        this.commandName = commandName;
    }
    abstract public Response execute(RequestBody requestBody);

    public Request toRequest(String[] args){
        return RequestFactory.createCommandRequest(getCommandName(), args);
    }

    abstract String getUsage();

    public String getCommandName(){
        return commandName;
    }
}