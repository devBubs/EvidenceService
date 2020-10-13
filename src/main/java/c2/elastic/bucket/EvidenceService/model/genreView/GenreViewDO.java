package c2.elastic.bucket.EvidenceService.model.genreView;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
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
public class GenreViewDO extends EventDO {
    private long contentId;

    public GenreViewDO(long userId, String timestamp, String eventType, long contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }
}
