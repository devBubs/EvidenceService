package c2.elastic.bucket.EvidenceService.components.processor.manager;

import c2.elastic.bucket.EvidenceService.components.processor.dao.ProcessorDao;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DefaultProcessorManager implements ProcessorManager {

    @Autowired
    private ProcessorDao processorDao;

    @Override
    public void processEvent(List<? extends EventBO<?, ?>> eventBOList) {
        //Todo: Any enrichment logic
        if (!eventBOList.isEmpty()) {
            log.info("Processing {} events", eventBOList.size());
            List<EventDO> eventDOList = eventBOList.stream().map(EventBO::convertToDO).collect(Collectors.toList());
            processorDao.persistEvent(eventDOList);
        } else {
            log.info("No events to process");
        }
    }
}
