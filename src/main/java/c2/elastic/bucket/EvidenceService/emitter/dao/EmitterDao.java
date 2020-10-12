package c2.elastic.bucket.EvidenceService.emitter.dao;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;

public interface EmitterDao {
    EventBO emitEventBO(EventBO eventBO);
}
