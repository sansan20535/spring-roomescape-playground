package roomescape.db.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import roomescape.db.entity.TimeEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<TimeEntity> getTimes() {

        final String sql = "SELECT id, time FROM time";

        return jdbcTemplate.query(
                sql, (resultSet, rowNum) -> TimeEntity.builder()
                        .id(resultSet.getLong("id"))
                        .time(resultSet.getString("time"))
                        .build()
        );
    }

    public long createTime(final String time) {
        final String sql = "INSERT INTO time(time) VALUES (?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, time);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteTime(final long timeId) {
        final String sql = "DELETE FROM time WHERE id = ?";

        jdbcTemplate.update(sql, timeId);
    }
}
