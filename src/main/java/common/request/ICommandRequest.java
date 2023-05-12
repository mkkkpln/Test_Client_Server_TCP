package common.request;

import common.new_things.CommandDTO;

public interface ICommandRequest extends IRequest {
    CommandDTO getCommand();
}

