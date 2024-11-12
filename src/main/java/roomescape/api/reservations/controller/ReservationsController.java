package roomescape.api.reservations.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.api.reservations.dto.request.ReservationRegisterRequest;
import roomescape.api.reservations.dto.response.ReservationRegisterResponse;
import roomescape.db.entity.ReservationsEntity;
import roomescape.enums.ErrorMessage;
import roomescape.api.exception.NotFoundException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private List<ReservationsEntity> reservationEntities = new ArrayList<>();
    private AtomicLong index = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<ReservationsEntity>> getReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationEntities);
    }

    @PostMapping
    public ResponseEntity<ReservationRegisterResponse> createReservation(
            @RequestBody @Valid final ReservationRegisterRequest reservationRegisterRequest
    ) {
        final ReservationsEntity reservationsEntity = ReservationsEntity.builder()
                .id(index.incrementAndGet())
                .name(reservationRegisterRequest.name())
                .date(reservationRegisterRequest.date())
                .time(reservationRegisterRequest.time())
                .build();

        reservationEntities.add(reservationsEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", URI.create("/reservations/" + reservationsEntity.getId()).toString())
                .body(ReservationRegisterResponse.of(
                        reservationsEntity.getId(),
                        reservationsEntity.getName(),
                        reservationsEntity.getDate(),
                        reservationsEntity.getTime()
                ));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "reservationId") final long reservationId
    ) {
        final ReservationsEntity findReservationsEntity = reservationEntities.stream()
                .filter(reservationsEntity -> reservationsEntity.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (findReservationsEntity == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_RESERVATION);
        }

        reservationEntities.remove(findReservationsEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
