package org.lexsoft.library.web.validator;

import java.util.List;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.StringUtils;
import org.lexsoft.library.core.exception.BusinessException;
import org.lexsoft.library.core.exception.ErrorMessages;
import org.lexsoft.library.utils.ExceptionUtils;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;

public abstract class AbstractValidator {

  protected void validateMandatory(String paramName, String param, List<Error> errors){
    if(Boolean.TRUE.equals(StringUtils.isEmpty(param))){
      ExceptionUtils.addError(ErrorMessages.PARAM_MANDATORY, errors, paramName);
    }
  }

  protected void validateMandatory(String paramName, Object param, List<Error> errors){
    if(param == null){
      ExceptionUtils.addError(ErrorMessages.PARAM_MANDATORY, errors, paramName);
    }
  }

  public void cancelProcessingAndThrowException(List<Error> errors){
    if(!errors.isEmpty()) {
      throw new BusinessException(Status.BAD_REQUEST,errors);
    }
  }


}
