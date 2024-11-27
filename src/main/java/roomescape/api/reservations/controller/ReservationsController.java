package roomescape.api.reservations.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.api.reservations.dto.request.ReservationRegisterRequest;
import roomescape.api.reservations.dto.response.ReservationRegisterResponse;
import roomescape.api.reservations.service.ReservationsService;
import roomescape.db.entity.ReservationsEntity;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationsController {

    private final ReservationsService reservationsService;

    @GetMapping
    public ResponseEntity<List<ReservationsEntity>> getReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.getReservations());
    }

    @PostMapping
    public ResponseEntity<ReservationRegisterResponse> createReservation(
            @RequestBody @Valid final ReservationRegisterRequest reservationRegisterRequest
    ) {

        final ReservationRegisterResponse reservationRegisterResponse = reservationsService.createReservations(
                reservationRegisterRequest.name(),
                reservationRegisterRequest.date(),
                reservationRegisterRequest.timeId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/reservations/" + reservationRegisterResponse.id()).body(reservationRegisterResponse);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "reservationId") final long reservationId
    ) {

        reservationsService.deleteReservations(reservationId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
