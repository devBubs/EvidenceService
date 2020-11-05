package c2.elastic.bucket.EvidenceService.model.rateMovie;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class RateMovieBO extends EventBO<RateMovieDTO, RateMovieDO> {

    private String contentId;
    private float rating;

    public RateMovieBO(String userId, String timestamp, String eventType, String contentId, float rating) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
        this.rating = rating;
    }

    @Override
    public RateMovieDO convertToDO() {
        RateMovieDO rateMovieDO = RateMovieDO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .rating(rating)
                .build();

        return rateMovieDO;
    }

    @Override
    public RateMovieDTO convertToDTO() {
        RateMovieDTO rateMovieDTO = RateMovieDTO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .rating(rating)
                .build();

        return rateMovieDTO;
    }
}
