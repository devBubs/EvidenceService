package c2.elastic.bucket.EvidenceService.model.details;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true )
@SuperBuilder(toBuilder = true )
@NoArgsConstructor
public class DetailsBO extends EventBO<DetailsDTO, DetailsDO> {

    private long contentId;

    public DetailsBO(long userId, String timestamp, String eventType, long contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }

    @Override
    public DetailsDO convertToDO() {
        DetailsDO detailsDO = DetailsDO.builder().userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return detailsDO;
    }

    @Override
    public DetailsDTO convertToDTO() {
        DetailsDTO detailsDTO = DetailsDTO.builder().userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return detailsDTO;
    }
}
