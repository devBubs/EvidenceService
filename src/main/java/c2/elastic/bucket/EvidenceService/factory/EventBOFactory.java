package c2.elastic.bucket.EvidenceService.factory;

import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketBO;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDO;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsBO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewBO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieBO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;

import java.util.List;
import java.util.stream.Collectors;

import static c2.elastic.bucket.EvidenceService.constants.EventConstants.ADD_TO_BUCKET_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.DETAILS_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.GENRE_VIEW_EVENT;
import static c2.elastic.bucket.EvidenceService.constants.EventConstants.RATE_MOVIE_EVENT;

public class EventBOFactory {
    public static EventBO<?, ?> createBO(EventDTO eventDTO) {
        String incomingEvent = eventDTO.getEventType();
        EventBO<?, ?> eventBO = null;
        switch (incomingEvent) {
            case DETAILS_EVENT:
                eventBO = createDetailsBO((DetailsDTO) eventDTO);
                break;
            case ADD_TO_BUCKET_EVENT:
                eventBO = createAddToBucketBO((AddToBucketDTO) eventDTO);
                break;
            case GENRE_VIEW_EVENT:
                eventBO = createGenreViewBO((GenreViewDTO) eventDTO);
                break;
            case RATE_MOVIE_EVENT:
                eventBO = createRateMovieBO((RateMovieDTO) eventDTO);
                break;
        }
        return eventBO;
    }

    public static List<? extends EventBO<?, ?>> createBO(List<? extends EventDTO> eventDTOList) {
        return eventDTOList.stream().map(EventBOFactory::createBO).collect(Collectors.toList());
    }

    public static EventBO<?, ?> createBO(EventDO eventDO) {
        return null;
    }

    private static DetailsBO createDetailsBO(DetailsDTO detailsDTO) {
        return DetailsBO.builder()
                .userId(detailsDTO.getUserId())
                .timestamp(detailsDTO.getTimestamp())
                .eventType(detailsDTO.getEventType())
                .contentId(detailsDTO.getContentId())
                .build();
    }

    private static DetailsBO createDetailsBO(DetailsDO detailsDO) {
        return null;
    }

    private static AddToBucketBO createAddToBucketBO(AddToBucketDTO addToBucketDTO) {
        return AddToBucketBO.builder()
                .userId(addToBucketDTO.getUserId())
                .timestamp(addToBucketDTO.getTimestamp())
                .eventType(addToBucketDTO.getEventType())
                .contentId(addToBucketDTO.getContentId())
                .build();
    }

    private static AddToBucketBO createAddToBucketBO(AddToBucketDO addToBucketDO) {
        return null;
    }

    private static GenreViewBO createGenreViewBO(GenreViewDTO genreViewDTO) {
        return GenreViewBO.builder()
                .userId(genreViewDTO.getUserId())
                .timestamp(genreViewDTO.getTimestamp())
                .eventType(genreViewDTO.getEventType())
                .contentId(genreViewDTO.getContentId())
                .build();
    }

    private static GenreViewBO createGenreViewBO(GenreViewDO genreViewDO) {
        return null;
    }

    private static RateMovieBO createRateMovieBO(RateMovieDTO rateMovieDTO) {
        return RateMovieBO.builder()
                .userId(rateMovieDTO.getUserId())
                .timestamp(rateMovieDTO.getTimestamp())
                .eventType(rateMovieDTO.getEventType())
                .contentId(rateMovieDTO.getContentId())
                .rating(rateMovieDTO.getRating())
                .build();
    }

    private static RateMovieBO createRateMovieBO(RateMovieDO rateMovieDO) {
        return null;
    }
}

