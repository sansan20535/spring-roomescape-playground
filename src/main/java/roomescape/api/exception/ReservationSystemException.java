package roomescape.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import roomescape.enums.ErrorMessage;

@Getter
public class ReservationSystemException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    protected ReservationSystemException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.message = errorMessage.getMessage();
        this.httpStatus = errorMessage.getHttpStatus();
    }
}
