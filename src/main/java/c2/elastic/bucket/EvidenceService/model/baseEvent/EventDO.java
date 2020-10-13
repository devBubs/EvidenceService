package c2.elastic.bucket.EvidenceService.model.baseEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class EventDO extends Event {
    public EventDO(long userId, String timestamp, String eventType) {
        super(userId, timestamp, eventType);
    }
}
