package common.request;

import common.new_things.CommandDTO;

import java.io.Serializable;

public class RequestFactory implements Serializable{
    public RequestFactory(){}

    public static Request createCommandRequest(String commandName, String[] args){
        return new Request(new CommandDTO(commandName), RequestBodyFactory.createRequestBody(args));
    }
}
