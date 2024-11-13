package roomescape.api.reservations.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.api.reservations.dto.request.ReservationRegisterRequest;
import roomescape.api.reservations.service.ReservationsService;
import roomescape.db.entity.ReservationsEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationsController {

    private final ReservationsService reservationsService;

    private List<ReservationsEntity> reservationEntities = new ArrayList<>();
    private AtomicLong index = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<ReservationsEntity>> getReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.getReservations());
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(
            @RequestBody @Valid final ReservationRegisterRequest reservationRegisterRequest
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", URI.create("/reservations/" +
                        reservationsService.createReservations(reservationRegisterRequest.name(), reservationRegisterRequest.date(), reservationRegisterRequest.time())).toString()
                )
                .body(null);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "reservationId") final long reservationId
    ) {

        reservationsService.deleteReservations(reservationId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
