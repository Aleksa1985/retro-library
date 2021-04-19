package org.lexsoft.library.core.exception;

import static org.lexsoft.library.utils.LogicUtils.functionsCase;
import static org.lexsoft.library.utils.LogicUtils.switchTypeWithFunctions;

import java.util.Arrays;
import java.util.Date;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.lexsoft.library.core.exception.model.ErrorMessage;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;

@Slf4j
@Provider
public class GlobalExceptionHandler  implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(RuntimeException exception) {
     Response r = (Response) switchTypeWithFunctions(
         exception,
         functionsCase(Exception.class, ex -> handleInternalServerException(ex)),
         functionsCase(BusinessException.class, be -> handleBusinessException(be)),
         functionsCase(NullPointerException.class, ne -> handleNullPointerException(ne))
        );
      return r;
  }

  private Response handleBusinessException(BusinessException be) {
    log.error(be.getMessage(), be);;
    ErrorMessage errorMessage = new ErrorMessage(new Date().getTime(),be.getErrorMessages());
    return Response.status(be.getStatus()).entity(errorMessage).build();
  }

  private Response handleInternalServerException(Exception ex) {
    log.error(ex.getMessage(), ex);;
    ErrorMessage errorMessage = new ErrorMessage(new Date().getTime(), Arrays.asList(Error.builder().message(ex.getMessage()).build()));
    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
  }

  private Response handleNullPointerException(NullPointerException ex) {
    log.error(ex.getMessage(), ex);;
    ErrorMessage errorMessage = new ErrorMessage(new Date().getTime(), Arrays.asList(Error.builder().message(ex.getMessage()).build()));
    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
  }

}
