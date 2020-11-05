package c2.elastic.bucket.EvidenceService.components.emitter.manager;

import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceNonRetriableException;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.GenBucket.msgqueue.Producer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DefaultEmitterManager implements EmitterManager {

    @Resource(name = "AllProducersMap")
    private Map<String, Producer<String, EventDTO>> producers;

    @Override
    public void processEvent(EventDTO eventDTO) {
        String eventType = eventDTO.getEventType();
        Producer<String, EventDTO> producer = Optional.ofNullable(producers.get(eventType))
                .orElseThrow(() -> new EvidenceServiceNonRetriableException("Not producer found for eventType:" + eventType));
        producer.produce(eventDTO);
    }

    @Override
    public void processEvent(List<EventDTO> eventDTOList) {
        String eventType = eventDTOList.stream().findFirst()
                .orElseThrow(() -> new EvidenceServiceNonRetriableException("No valid events found"))
                .getEventType();
        Producer<String, EventDTO> producer = Optional.ofNullable(producers.get(eventType))
                .orElseThrow(() -> new EvidenceServiceNonRetriableException("Not producer found for eventType:" + eventType));
        producer.produce(eventDTOList);
    }
}
