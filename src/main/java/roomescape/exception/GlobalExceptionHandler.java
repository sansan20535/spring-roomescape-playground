package roomescape.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roomescape.enums.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // custom
    @ExceptionHandler({NotFoundException.class, BadRequestException.class})
    public ResponseEntity<String> handleCustomException(final ReservationException reservationException) {
        return ResponseEntity.status(reservationException.getHttpStatus()).body(reservationException.getMessage());
    }

    // validation
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidateException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.EMPTY_VALUE_REQUEST.getMessage());
    }
}
