package c2.elastic.bucket.EvidenceService.model.details;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class DetailsDO extends EventDO {
    private String contentId;

    public DetailsDO(String userId, String timestamp, String eventType, String contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }
}
