package c2.elastic.bucket.EvidenceService.emitter.dao;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultEmitterDao implements EmitterDao{

    @Override
    public EventBO<?, ?> emitEventBO(EventBO<?, ?> eventBO) {
        log.info("Emitting event to processor: {}", eventBO);
        return eventBO;
    }
}
