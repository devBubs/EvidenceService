package c2.elastic.bucket.EvidenceService.emitter.producer.serializer;

import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventDTOSerializer implements Serializer<EventDTO> {

    private final ObjectMapper objectMapper;

    @Autowired
    public EventDTOSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, EventDTO data) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, EventDTO data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
