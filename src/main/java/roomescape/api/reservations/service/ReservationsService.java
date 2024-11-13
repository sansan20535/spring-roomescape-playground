package roomescape.api.reservations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.db.entity.ReservationsEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationsService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ReservationsEntity> getReservations() {
        final String sql = "SELECT id, name, date, time FROM reservation";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) -> ReservationsEntity.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .date(resultSet.getString("date"))
                        .time(resultSet.getString("time"))
                        .build());
    }

    @Transactional
    public long createReservations(final String name, final String date, final String time) {
        final String sql = "INSERT INTO reservation(name, date, time) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, time);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Transactional
    public void deleteReservations(final long reservationId) {
        final String sql = "delete from reservation where id = ?";

        jdbcTemplate.update(sql, reservationId);
    }
}
