package roomescape.controller.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.request.ReservationRegisterRequest;
import roomescape.dto.response.ReservationRegisterResponse;
import roomescape.entity.ReservationEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private List<ReservationEntity> reservationEntities = new ArrayList<>();
    private AtomicLong index = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationEntities);
    }

    @PostMapping
    public ResponseEntity<ReservationRegisterResponse> createReservation(
            @RequestBody final ReservationRegisterRequest reservationRegisterRequest
    ) {
        final ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(index.incrementAndGet())
                .name(reservationRegisterRequest.name())
                .date(reservationRegisterRequest.date())
                .time(reservationRegisterRequest.time())
                .build();

        reservationEntities.add(reservationEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", URI.create("/reservations/" + reservationEntity.getId()).toString())
                .body(ReservationRegisterResponse.of(
                        reservationEntity.getId(),
                        reservationEntity.getName(),
                        reservationEntity.getDate(),
                        reservationEntity.getTime()
                ));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "reservationId") final long reservationId
    ) {
        final ReservationEntity findReservationEntity = reservationEntities.stream()
                .filter(reservationEntity -> reservationEntity.getId() == reservationId)
                .findFirst()
                .orElseThrow(null);

        reservationEntities.remove(findReservationEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
