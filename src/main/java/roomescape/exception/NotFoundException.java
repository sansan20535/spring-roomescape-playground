package roomescape.exception;

import roomescape.enums.ErrorMessage;

public class NotFoundException extends ReservationException {

    public NotFoundException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
