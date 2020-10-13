package c2.elastic.bucket.EvidenceService.model.genreView;

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
public class GenreViewBO extends EventBO<GenreViewDTO, GenreViewDO>{

    private long contentId;

    public GenreViewBO(long userId, String timestamp, String eventType, long contentId) {
        super(userId, timestamp, eventType);
        this.contentId = contentId;
    }

    @Override
    public GenreViewDO convertToDO() {
        GenreViewDO genreViewDO = GenreViewDO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return genreViewDO;
    }

    @Override
    public GenreViewDTO convertToDTO() {
        GenreViewDTO genreViewDTO = GenreViewDTO.builder()
                .userId(getUserId())
                .timestamp(getTimestamp())
                .eventType(getEventType())
                .contentId(contentId)
                .build();
        return genreViewDTO;
    }
}
