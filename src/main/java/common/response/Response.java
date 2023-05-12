package common.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Response implements Serializable {
    private String serverMessage;
    public Response (String serverMessage){
        this.serverMessage = serverMessage;
    }
    public String getServerMessage(){
        return serverMessage;
    }

    @Override
    public String toString(){
        return serverMessage;
    }
}
