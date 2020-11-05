package c2.elastic.bucket.EvidenceService.components.processor.validator;

import c2.elastic.bucket.EvidenceService.components.emitter.constants.EmitterConstants;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static c2.elastic.bucket.EvidenceService.constants.EventConstants.ADD_TO_BUCKET_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.DETAILS_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.GENRE_VIEW_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.RATE_MOVIE_EVENT;

@Component
public class ProcessorValidator {

    public void validateEvent(EventDTO eventDTO){
        try{
            Preconditions.checkArgument(StringUtils.isNotBlank(eventDTO.getEventType()), EmitterConstants.EVENT_TYPE_NULL);
        }catch (IllegalArgumentException e){
            throw new EvidenceServiceInvalidInputException(e.getMessage(), e);
        }
        switch (eventDTO.getEventType()){
            case ADD_TO_BUCKET_EVENT: validateEvent((AddToBucketDTO) eventDTO); break;
            case DETAILS_EVENT: validateEvent((DetailsDTO) eventDTO); break;
            case GENRE_VIEW_EVENT: validateEvent((GenreViewDTO) eventDTO); break;
            case RATE_MOVIE_EVENT: validateEvent((RateMovieDTO) eventDTO); break;
            default: throw new EvidenceServiceInvalidInputException("Received EventDTO is not of a valid event type: " + eventDTO.getEventType());
        }
    }

    private void validateEvent(AddToBucketDTO addToBucketDTO){
        //Todo: add appropriate validation
    }

    private void validateEvent(DetailsDTO detailsDTO){
        //Todo: add appropriate validation
    }

    private void validateEvent(GenreViewDTO genreViewDTO){
        //Todo: add appropriate validation
    }

    private void validateEvent(RateMovieDTO rateMovieDTO){
        //Todo: add appropriate validation
    }
}
