package common.new_things;

import common.request.*;

import java.io.Serializable;

public class MessageRequest implements IRequest, Serializable {
    private RequestBody requestBody;
    public MessageRequest(RequestBody requestBody){
        this.requestBody = requestBody;
    }
    @Override
    public RequestBody getRequestBody() {
        return requestBody;
    }
}
