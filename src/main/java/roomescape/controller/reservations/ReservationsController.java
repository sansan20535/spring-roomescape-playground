package roomescape.controller.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.entity.ReservationEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private List<ReservationEntity> reservationEntities = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        reservationEntities.add(
                ReservationEntity.builder()
                        .id(1)
                        .name("브라운")
                        .date("2023-01-01")
                        .time("10:00")
                        .build()
        );
        reservationEntities.add(
                ReservationEntity.builder()
                        .id(2)
                        .name("브라운")
                        .date("2023-01-02")
                        .time("11:00")
                        .build()
        );
        return ResponseEntity.status(HttpStatus.OK).body(reservationEntities);
    }
}
