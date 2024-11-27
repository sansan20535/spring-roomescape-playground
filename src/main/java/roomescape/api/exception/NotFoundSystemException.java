package roomescape.api.exception;

import roomescape.enums.ErrorMessage;

public class NotFoundSystemException extends ReservationSystemException {

    public NotFoundSystemException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
