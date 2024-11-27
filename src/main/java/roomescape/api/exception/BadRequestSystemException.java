package roomescape.api.exception;

import roomescape.enums.ErrorMessage;

public class BadRequestSystemException extends ReservationSystemException {

    public BadRequestSystemException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
