package c2.elastic.bucket.EvidenceService.model.baseEvent;

import c2.elastic.bucket.EvidenceService.constants.EventConstants;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static c2.elastic.bucket.EvidenceService.constants.EventConstants.ADD_TO_BUCKET_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.DETAILS_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.EVENT_TYPE_ATTRIBUTE;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.GENRE_VIEW_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.RATE_MOVIE_EVENT;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = EVENT_TYPE_ATTRIBUTE, visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = DetailsDTO.class, name = DETAILS_EVENT),
        @JsonSubTypes.Type(value = GenreViewDTO.class, name = GENRE_VIEW_EVENT),
        @JsonSubTypes.Type(value = AddToBucketDTO.class, name = ADD_TO_BUCKET_EVENT),
        @JsonSubTypes.Type(value = RateMovieDTO.class, name = RATE_MOVIE_EVENT)
})
public abstract class EventDTO extends Event {
    public EventDTO(String userId, String timestamp, String eventType) {
        super(userId, timestamp, eventType);
    }
}
