package c2.elastic.bucket.EvidenceService.components.processor.manager;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;

import java.util.List;

public interface ProcessorManager {
    void processEvent(List<? extends EventBO<?,?>> eventBOList);
}
