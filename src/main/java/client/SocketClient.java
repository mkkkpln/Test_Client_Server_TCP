package client;

import common.new_things.*;
import new_things.*;
import common.request.Request;
import common.response.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;


public class SocketClient {

    public static void main(String[] args) {
        try {
//            Scanner scanner = new Scanner(System.in);
//            SocketChannel socketChannel;
//            SocketAddress address = new InetSocketAddress("localhost", 1234);
//            socketChannel = SocketChannel.open();
//            socketChannel.connect(address);
//            System.out.println("Connected to server");


            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress("localhost", 1234);
            socket.connect(address);
            System.out.println("Connected to server");
            CommandsManager commandsManager = new CommandsManager();
            commandsManager.fillClientMap();

            while(true){

                String out = scanner.nextLine();
                Request commandRequest = commandsManager.executeClient(out);
                OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                outputStream.write(Serializer.serializeRequest(commandRequest));
                outputStream.flush();
                InputStream inputStream = new BufferedInputStream(socket.getInputStream());
                ByteBuffer byteInBuffer = ByteBuffer.allocate(1024);
                int bytesRead = inputStream.read(byteInBuffer.array());
                byte[] bytes = new byte[bytesRead];
                System.arraycopy(byteInBuffer.array(), 0, bytes, 0, bytesRead);
                Response response = Deserializer.deserializeResponse(bytes);
                System.out.println("Sent to server: " + response.toString());


//                String out = scanner.nextLine();
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

//                command request
//                request.RequestFactory requestFactory = new request.RequestFactory();
//                CommandRequest commandRequest = requestFactory.createCommandRequest("www", new String[]{"1", "2"});

            }
        }
        catch (IOException | SerializeException e) {
//            e.printStackTrace();
            System.out.println("Error when receiving a response from the server. It may be temporarily unavailable.");
        } catch (DeserializeException e) {
            throw new RuntimeException(e);
        }
    }
}








