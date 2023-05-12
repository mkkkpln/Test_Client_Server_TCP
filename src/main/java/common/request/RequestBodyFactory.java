package common.request;

public class RequestBodyFactory {

    public RequestBodyFactory(){
    }

    public static RequestBody createRequestBody(String[] args){
        return new RequestBody(args);
    }
}
