package common.request;

import java.io.Serializable;

public class RequestBody implements Serializable{
    private final String[] args;

    public RequestBody(String[] args) {
        this.args = args;
    }

    public String[] getArgs(){
        return args;
    }
}
