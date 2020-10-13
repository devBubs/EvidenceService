package c2.elastic.bucket.EvidenceService.emitter.manager;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;

public interface EmitterManager {
    EventBO processEvent(EventBO eventBO);
}
