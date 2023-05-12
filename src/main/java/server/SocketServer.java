package server;

import commands.*;
import common.new_things.*;
import new_things.*;
import org.xml.sax.SAXException;
import common.request.Request;
import common.response.Response;
import server.managers.CollectionManager;
import server.commands.*;
import server.utils.Environment;
import server.utils.IO;
import server.utils.WrongArgumentException;
import server.utils.XmlUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class SocketServer {
    static InetAddress host;
    static int port = 6789;
    SocketAddress address;
    SocketChannel channel;

    public static void main(String[] args) throws DeserializeException {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            SocketChannel socketChannel = null;
            SocketAddress socketAddress = new InetSocketAddress(1234);
            server.bind(socketAddress);
            System.out.println("Connected to server");

            CommandsManager commandsManager = new CommandsManager();
            CollectionManager manager = new CollectionManager();
//            commandsManager.fillServerMap(collectionManager);
//            commandsManager.fillServerMap(manager);




            //Загрузим коллекцию из файла
            if (args.length > 0) {
                String link = args[0];
                File f = new File(link);

                if (f.exists() && !f.isDirectory()) {

                    try {
                        manager = XmlUtil.XMLParser(link);

                    } catch (ParserConfigurationException | IOException e) {
                        System.out.println("IO exception. (No file or not allowed to use it)\ncollection will be empty!");
                    } catch (SAXException e) {
                        System.out.println("Incorrect file, collection will be empty");
                        try {
                            throw new WrongArgumentException();
                        } catch (WrongArgumentException ex) {
                            System.out.println("Incorrect file, collection will be empty");
                        }
                    }
                }
            }

            //Генерируем все необходимое для паттерна "Command"
            ArrayList<String> history = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream writer = new PrintStream(System.out);
            ICommand[] commands = new ICommand[]{new Help(), new Info(), new Exit(), new Clear(), new Show(), new Insert(), new SumOfImpactSpeed(), new RemoveKey(), new RemoveGreater(), new ReplaceIfLower(), new ExecuteScript(), new PrintFieldDescendingMood(), new Save(), new UpdateID(), new PrintAscending(), new RemoveLowerKey()};
            Environment environment = new Environment(manager, reader, writer, history, commands);
            Invoker invoker = new Invoker(environment, commands);

            //запускаем программу
            IO.commandReader(environment, invoker);







            while (true) {
                socketChannel = server.accept();
                System.out.println("New client connected: " + socketChannel.getRemoteAddress());
                while (true) {
                    ByteBuffer byteInBuffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(byteInBuffer);
                    if (bytesRead == -1) {
                        System.out.println("Client disconnected");
                        break;
                    }
                    byte[] bytes = new byte[bytesRead];
                    System.arraycopy(byteInBuffer.array(), 0, bytes, 0, bytesRead);

                    Request request = Deserializer.deserializeRequest(bytes);
                    Response response = commandsManager.executeServer(request);
                    ByteBuffer byteOutBuffer = ByteBuffer.wrap(Serializer.serializeResponse(response));
                    socketChannel.write(byteOutBuffer);
                    byteOutBuffer.clear();
                    System.out.println("Received in client: " + request.getCommand());
                    byteInBuffer.clear();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error when receiving a request from the client. It may be temporarily unavailable.");
        } catch (SerializeException e) {
            throw new RuntimeException(e);
        }
    }
}


