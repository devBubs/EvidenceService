package c2.elastic.bucket.EvidenceService.components.processor.dao;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;

import java.util.List;

public interface ProcessorDao {
    void persistEvent(EventDO eventDO);
    void persistEvent(List<EventDO> eventDOList);
}
