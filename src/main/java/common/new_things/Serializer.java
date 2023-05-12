package common.new_things;

import common.request.Request;
import common.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer {

    public static byte[] serializeRequest(Request request) throws SerializeException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }catch (IOException ex){
            throw new SerializeException("unable to serialize request");
        }
    }

    public static byte[] serializeResponse(Response response) throws SerializeException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }catch (IOException ex){
            throw new SerializeException("unable to serialize response");
        }
    }
}

