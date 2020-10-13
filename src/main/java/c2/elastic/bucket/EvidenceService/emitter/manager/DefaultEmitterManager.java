package c2.elastic.bucket.EvidenceService.emitter.manager;

import c2.elastic.bucket.EvidenceService.emitter.dao.EmitterDao;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmitterManager implements c2.elastic.bucket.EvidenceService.emitter.manager.EmitterManager {

    private final EmitterDao emitterDao;

    @Autowired
    public DefaultEmitterManager(EmitterDao emitterDao) {
        this.emitterDao = emitterDao;
    }

    @Override
    public EventBO processEvent(EventBO eventBO) {
        //TODO: add processing calls to enricher component
        eventBO = emitterDao.emitEventBO(eventBO);
        return eventBO;
    }
}
