package roomescape.api.times.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.api.times.dto.response.TimeRegisterResponse;
import roomescape.db.dao.TimeDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeDao timeDao;

    @Transactional(readOnly = true)
    public List<TimeRegisterResponse> getTimes() {
        return timeDao.getTimes().stream()
                .map(timeEntity -> TimeRegisterResponse.of(timeEntity.getId(), timeEntity.getTime()))
                .toList();
    }

    @Transactional
    public long createTime(final String time) {
        return timeDao.createTime(time);
    }

    @Transactional
    public void deleteTime(final long timeId) {
        timeDao.deleteTime(timeId);
    }
}
