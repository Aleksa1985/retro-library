package org.lexsoft.library.core.exception;

import java.util.List;
import javax.ws.rs.core.Response.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;

@AllArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException{

    Status status;
    List<Error> errorMessages;

}
