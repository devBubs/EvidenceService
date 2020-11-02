package c2.elastic.bucket.EvidenceService.model.addToBucket;

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
public class AddToBucketBO extends EventBO<AddToBucketDTO, AddToBucketDO> {

    private long contentId;

    public AddToBucketBO(String userId, String timestamp, String eventType, long contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }

    @Override
    public AddToBucketDO convertToDO() {
        AddToBucketDO addToBucketDO = AddToBucketDO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return addToBucketDO;
    }

    @Override
    public AddToBucketDTO convertToDTO() {
        AddToBucketDTO addToBucketDTO = AddToBucketDTO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return addToBucketDTO;
    }
}
