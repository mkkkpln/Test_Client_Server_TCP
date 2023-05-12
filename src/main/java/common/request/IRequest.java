package common.request;

import java.io.Serializable;

public interface IRequest extends Serializable {
    RequestBody getRequestBody();


}
