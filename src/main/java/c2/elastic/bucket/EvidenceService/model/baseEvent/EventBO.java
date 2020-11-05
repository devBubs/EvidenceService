package c2.elastic.bucket.EvidenceService.model.baseEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class EventBO<DTO extends EventDTO, DO extends EventDO> extends Event {
    public EventBO(String userId, String timestamp, String eventType) {
        super(userId, timestamp, eventType);
    }
    public abstract DO convertToDO();
    public abstract DTO convertToDTO();
}
