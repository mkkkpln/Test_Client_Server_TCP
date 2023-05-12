package common.new_things;

import common.request.Request;
import common.response.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {

    public static Request deserializeRequest(byte[] serializedObject) throws DeserializeException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Request) objectInputStream.readObject();
        }catch (IOException |  ClassNotFoundException ex){
            throw new DeserializeException("unable to deserialize request");
        }
    }
    public static Response deserializeResponse(byte[] serializedObject) throws DeserializeException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Response) objectInputStream.readObject();
        }catch (IOException |  ClassNotFoundException ex){
            throw new DeserializeException("unable to deserialize response");
        }
    }
}

