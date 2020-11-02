package c2.elastic.bucket.EvidenceService.model.baseEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Event {
    private String userId;
    private String timestamp;
    private String eventType;
}
