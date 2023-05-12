package server.managers;

import common.request.Request;
import common.response.Response;

public interface CommandManagerInterface {

//    Map<String, ? extends new_things.AbstractCommand> getCommandsClient();
//    Map<String, ? extends new_things.AbstractCommand> getCommandsServer();

    Request executeClient(String cmAndArgs);

    Response executeServer(Request request);

}
