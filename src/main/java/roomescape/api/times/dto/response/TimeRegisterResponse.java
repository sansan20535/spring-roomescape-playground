package roomescape.api.times.dto.response;

public record TimeRegisterResponse(
        long id,
        String time
) {

    public static TimeRegisterResponse of(final long id, final String time) {
        return new TimeRegisterResponse(id, time);
    }
}
