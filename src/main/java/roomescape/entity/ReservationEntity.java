package roomescape.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationEntity {

    private final long id;

    private final String name;

    private final String date;

    private final String time;

    @Builder
    public ReservationEntity(long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }
}
