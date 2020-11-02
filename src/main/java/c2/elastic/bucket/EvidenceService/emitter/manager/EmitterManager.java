package c2.elastic.bucket.EvidenceService.emitter.manager;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;

import java.util.List;

public interface EmitterManager {
    void processEvent(EventDTO eventDTO);

    void processEvent(List<EventDTO> eventDTOList);
}
