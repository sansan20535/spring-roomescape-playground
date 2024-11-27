package roomescape.api.exception;

import roomescape.enums.ErrorMessage;

public class BadRequestException extends ReservationException {

    public BadRequestException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
