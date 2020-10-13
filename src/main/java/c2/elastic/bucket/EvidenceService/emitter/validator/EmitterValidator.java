package c2.elastic.bucket.EvidenceService.emitter.validator;

import static c2.elastic.bucket.EvidenceService.constants.EventConstants.ADD_TO_BUCKET_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.DETAILS_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.GENRE_VIEW_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.RATE_MOVIE_EVENT;

import c2.elastic.bucket.EvidenceService.emitter.constants.EmitterConstants;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;

import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class EmitterValidator {

    private static final Set<String> validEvents = new HashSet<>(
            Arrays.asList(ADD_TO_BUCKET_EVENT, DETAILS_EVENT, GENRE_VIEW_EVENT, RATE_MOVIE_EVENT));

    public void validateEmitEvent(EventDTO eventDTO){
        try{
            Preconditions.checkArgument(StringUtils.isNotBlank(eventDTO.getTimestamp()), EmitterConstants.EVENT_TIMESTAMP_NULL);
            Preconditions.checkArgument(StringUtils.isNotBlank(eventDTO.getEventType()), EmitterConstants.EVENT_TYPE_NULL);
            Preconditions.checkArgument(validEvents.contains(eventDTO.getEventType()), EmitterConstants.EVENT_TYPE_INVALID);
            String incomingEvent = eventDTO.getEventType();
            switch (incomingEvent){
                case ADD_TO_BUCKET_EVENT: validateAddToBucketEvent((AddToBucketDTO) eventDTO); break;
                case DETAILS_EVENT: validateDetailsEvent((DetailsDTO) eventDTO); break;
                case GENRE_VIEW_EVENT: validateGenreViewEvent((GenreViewDTO) eventDTO); break;
                case RATE_MOVIE_EVENT: validateRateMovieEvent((RateMovieDTO) eventDTO); break;
            }
        }catch(IllegalArgumentException e){
            throw new EvidenceServiceInvalidInputException(e.getMessage(), e);
        }
    }

    private void validateAddToBucketEvent(AddToBucketDTO addToBucketDTO){

    }

    private void validateDetailsEvent(DetailsDTO detailsDTO){

    }

    private void validateGenreViewEvent(GenreViewDTO genreViewDTO){

    }

    private void validateRateMovieEvent(RateMovieDTO rateMovieDTO){

    }
}
