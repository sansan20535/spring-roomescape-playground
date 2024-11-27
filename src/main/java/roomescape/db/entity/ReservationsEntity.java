package roomescape.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
@Builder
@AllArgsConstructor
public class ReservationsEntity {

    private final long id;

    private final String name;

    private final String date;

    private final Time time;

}
