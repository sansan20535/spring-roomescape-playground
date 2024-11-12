package roomescape.api.reservations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.db.entity.ReservationsEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationsService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ReservationsEntity> getReservations() {
        final String sql = "select id, name, date, time from reservation";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) -> ReservationsEntity.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .date(resultSet.getString("date"))
                        .time(resultSet.getString("time"))
                        .build());
    }
}
