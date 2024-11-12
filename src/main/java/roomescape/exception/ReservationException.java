package roomescape.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import roomescape.enums.ErrorMessage;

@Getter
public class ReservationException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    protected ReservationException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.message = errorMessage.getMessage();
        this.httpStatus = errorMessage.getHttpStatus();
    }
}
