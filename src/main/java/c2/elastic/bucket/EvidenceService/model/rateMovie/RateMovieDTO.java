package c2.elastic.bucket.EvidenceService.model.rateMovie;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
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
public class RateMovieDTO extends EventDTO {
    private long contentId;
    private float rating;

    public RateMovieDTO(String userId, String timestamp, String eventType, long contentId, float rating) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
        this.rating = rating;
    }
}
