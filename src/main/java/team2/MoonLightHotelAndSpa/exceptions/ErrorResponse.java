package team2.MoonLightHotelAndSpa.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private String developMessage;

    @ExceptionHandler(value = DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistsException(DuplicateRecordException ex) {

        return new ErrorResponse(ex.getMessage(), ex.getLocalizedMessage());
    }

}
