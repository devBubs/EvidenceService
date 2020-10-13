package c2.elastic.bucket.EvidenceService.model.addToBucket;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class AddToBucketDO extends EventDO {
    private long contentId;

    public AddToBucketDO(long userId, String timestamp, String eventType, long contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }
}
