package c2.elastic.bucket.EvidenceService.components.emitter.validator;

import static c2.elastic.bucket.EvidenceService.constants.EventConstants.ADD_TO_BUCKET_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.DETAILS_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.GENRE_VIEW_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.RATE_MOVIE_EVENT;

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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    public void validateEmitEvent(List<EventDTO> eventDTOList){
        try{
            eventDTOList.forEach(event->{
                Preconditions.checkArgument(StringUtils.isNotBlank(event.getTimestamp()), EmitterConstants.EVENT_TYPE_NULL);
                Preconditions.checkArgument(StringUtils.isNotBlank(event.getEventType()), EmitterConstants.EVENT_TYPE_INVALID);
            });
            String incomingEventType = eventDTOList.get(0).getEventType();
            Preconditions.checkArgument(validEvents.contains(incomingEventType), EmitterConstants.EVENT_TYPE_INVALID);
            Preconditions.checkArgument(eventDTOList.stream().allMatch(event-> event.getEventType().equals(incomingEventType)), EmitterConstants.EVENT_TYPE_NOT_HOMOGENOUS);
            switch (incomingEventType){
                case ADD_TO_BUCKET_EVENT: eventDTOList.forEach(event->validateAddToBucketEvent((AddToBucketDTO)event)); break;
                case DETAILS_EVENT: eventDTOList.forEach(event->validateDetailsEvent((DetailsDTO)event)); break;
                case GENRE_VIEW_EVENT: eventDTOList.forEach(event->validateGenreViewEvent((GenreViewDTO) event)); break;
                case RATE_MOVIE_EVENT: eventDTOList.forEach(event->validateRateMovieEvent((RateMovieDTO) event)); break;
            }
        }catch (IllegalArgumentException e){
            throw new EvidenceServiceInvalidInputException(e.getMessage(), e);
        }

    }

    private void validateAddToBucketEvent(AddToBucketDTO addToBucketDTO){
        //Todo: add appropriate validation
    }

    private void validateDetailsEvent(DetailsDTO detailsDTO){
        //Todo: add appropriate validation
    }

    private void validateGenreViewEvent(GenreViewDTO genreViewDTO){
        //Todo: add appropriate validation
    }

    private void validateRateMovieEvent(RateMovieDTO rateMovieDTO){
        //Todo: add appropriate validation
    }
}
