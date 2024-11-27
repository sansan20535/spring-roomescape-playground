package roomescape.api.reservations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.api.exception.NotFoundSystemException;
import roomescape.api.reservations.dto.response.ReservationRegisterResponse;
import roomescape.db.entity.ReservationsEntity;
import roomescape.enums.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationsService {

    private final JdbcTemplate jdbcTemplate;

    private static final int DATABASE_ERROR = 0;

    @Transactional(readOnly = true)
    public List<ReservationsEntity> getReservations() {
        final String sql = "SELECT r.id as reservation_id, r.name, r.date, t.id as time_id, t.time as time_value FROM reservation as r inner join time as t on r.time_id = t.id";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) -> ReservationsEntity.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .date(resultSet.getString("date"))
                        .time(new Time(1L))
                        .build());
    }

    @Transactional
    public ReservationRegisterResponse createReservations(final String name, final String date, final Long timeId) {
        final String sql = "INSERT INTO reservation(name, date, time_id) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setLong(3, timeId);
            return ps;
        }, keyHolder);

        final String findSql = "SELECT id, name, date, time FROM reservation WHERE id = (?)";

        return jdbcTemplate.queryForObject(
                findSql,
                (rs, rowNum) -> ReservationRegisterResponse.of(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("time")
                ),
                keyHolder.getKey().longValue()
        );
    }

    @Transactional
    public void deleteReservations(final long reservationId) {
        final String sql = "DELETE FROM reservation WHERE id = ?";

        if (jdbcTemplate.update(sql, reservationId) == DATABASE_ERROR) {
            throw new NotFoundSystemException(ErrorMessage.NOT_FOUND_RESERVATION);
        }
    }
}
