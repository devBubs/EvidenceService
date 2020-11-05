package c2.elastic.bucket.EvidenceService.model.genreView;

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
public class GenreViewDTO extends EventDTO {
    private String contentId;

    public GenreViewDTO(String userId, String timestamp, String eventType, String contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }
}
