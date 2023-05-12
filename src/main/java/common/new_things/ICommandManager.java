package common.new_things;

import common.request.Request;
import common.response.Response;

import java.util.Map;

public interface ICommandManager {

    Map<String, ? extends AbstractCommand> getCommandsClient();
    Map<String, ? extends AbstractCommand> getCommandsServer();

    Request executeClient(String cmAndArgs);

    Response executeServer(Request request);

}