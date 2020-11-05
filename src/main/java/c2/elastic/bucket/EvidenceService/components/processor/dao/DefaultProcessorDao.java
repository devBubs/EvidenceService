package c2.elastic.bucket.EvidenceService.components.processor.dao;

import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceNonRetriableException;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDO;
import c2.elastic.bucket.EvidenceService.components.processor.constants.ProcessorConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Component
public class DefaultProcessorDao implements ProcessorDao {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void persistEvent(EventDO eventDO) {

    }

    @Override
    public void persistEvent(List<EventDO> eventDOList) {
        String eventType = eventDOList.stream().findFirst()
                .orElseThrow(() -> new EvidenceServiceInvalidInputException("No valid events found"))
                .getEventType();
        String location = assignPersistenceLocation(eventType);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(location))) {
            bw.write(objectMapper.writeValueAsString(eventDOList));
        } catch (IOException e) {
            String msg = String.format("Failed to persist data %s at location %s", eventDOList, location);
            throw new EvidenceServiceNonRetriableException(msg, e);
        }
    }

    private String assignPersistenceLocation(String eventType) {
        StringBuilder location = new StringBuilder();
        location.append(ProcessorConstants.EVIDENCE_LOGS_ROOT_DIRECTORY);
        location.append(eventType).append("/");
        Date currentTimeStamp = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        location.append(formatter.format(currentTimeStamp)).append("/");
        formatter = new SimpleDateFormat("MMMM");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        location.append(formatter.format(currentTimeStamp).toLowerCase()).append("/");
        formatter = new SimpleDateFormat("dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        location.append(formatter.format(currentTimeStamp)).append("/");
        formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        File dir = new File(location.toString());
        if (!dir.exists()) {
            synchronized (this) {
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }
        }
        String filename = formatter.format(currentTimeStamp) +
                (int) (Math.random() * ((ProcessorConstants.RANDOM_UPPER_BOUND - ProcessorConstants.RANDOM_LOWER_BOUND) + 1));
        location.append(filename);
        return location.toString();
    }
}
