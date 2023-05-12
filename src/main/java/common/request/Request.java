package common.request;

import common.new_things.CommandDTO;

import java.io.Serializable;

public class Request implements ICommandRequest, Serializable {
    private CommandDTO command;
//    private Object data;
    private RequestBody requestBody;

//    public CommandRequest(request.CommandDTO command, String[] args){
    public Request(CommandDTO command, RequestBody requestBody){
        this.command = command;
//        this.args = args;
        this.requestBody = requestBody;
    }

//    @Override
//    public Object getData() {
//        return data;
//    }

    public RequestBody getRequestBody(){
        return requestBody;
    }

    @Override
    public CommandDTO getCommand() {
        return command;
    }


}

