package common.response;

public class ResponseFactory {

    public ResponseFactory(){}

    public static Response createResponse(String serverMessage){
        return new Response(serverMessage);
    }
}
