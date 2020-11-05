package c2.elastic.bucket.EvidenceService.components.processor.config;

import c2.elastic.bucket.EvidenceService.components.processor.manager.ProcessorManager;
import c2.elastic.bucket.EvidenceService.components.processor.validator.ProcessorValidator;
import c2.elastic.bucket.EvidenceService.constants.EventConstants;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceNonRetriableException;
import c2.elastic.bucket.EvidenceService.factory.EventBOFactory;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;
import c2.elastic.bucket.GenBucket.msgqueue.kafka.CustomConsumerConfig;
import c2.elastic.bucket.GenBucket.msgqueue.kafka.KConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class EvidenceServiceProcessorConfiguration {

    @Bean
    @Qualifier("AddToBucketEventDeserializer")
    Deserializer<AddToBucketDTO> getAddToBucketEventDeserializer(ObjectMapper objectMapper) {
        return getKafkaDeserializer(objectMapper, AddToBucketDTO.class);
    }

    @Bean
    @Qualifier("DetailsEventDeserializer")
    Deserializer<DetailsDTO> getDetailsEventDeserializer(ObjectMapper objectMapper) {
        return getKafkaDeserializer(objectMapper, DetailsDTO.class);
    }

    @Bean
    @Qualifier("GenreViewEventDeserializer")
    Deserializer<GenreViewDTO> getGenreViewEventDeserializer(ObjectMapper objectMapper) {
        return getKafkaDeserializer(objectMapper, GenreViewDTO.class);
    }

    @Bean
    @Qualifier("RateMovieEventDeserializer")
    Deserializer<RateMovieDTO> getRateMovieEventDeserializer(ObjectMapper objectMapper) {
        return getKafkaDeserializer(objectMapper, RateMovieDTO.class);
    }

    @Bean
    @Qualifier("StringDeserializer")
    Deserializer<String> getStringDeserializer() {
        return new StringDeserializer();
    }

    @Bean
    @Qualifier("AddToBucketEventConsumerConfig")
    public CustomConsumerConfig addToBucketConsumerConfig() {
        // Todo: read config details from yaml
        return CustomConsumerConfig.builder()
                .groupId("AddToBucketConsumerGroup")
                .pollTimeOutDuration(30000L)
                .numWorkers(3)
                .maxRetries(1)
                .topics(Collections.singletonList(EventConstants.ADD_TO_BUCKET_EVENT_TOPIC))
                .bootstrapServers("localhost:9092")
                .fetchMinBytes(50 * 1024)
                .fetchMaxWait(7000)
                .enableAutoCommit(false)
                .autoOffsetReset("earliest")
                .build();
    }

    @Bean
    @Qualifier("DetailsEventConsumerConfig")
    public CustomConsumerConfig DetailsConsumerConfig() {
        // Todo: read config details from yaml
        return CustomConsumerConfig.builder()
                .groupId("DetailsConsumerGroup")
                .pollTimeOutDuration(30000L)
                .numWorkers(3)
                .maxRetries(1)
                .topics(Collections.singletonList(EventConstants.DETAILS_EVENT_TOPIC))
                .bootstrapServers("localhost:9092")
                .fetchMinBytes(50 * 1024)
                .fetchMaxWait(7000)
                .enableAutoCommit(false)
                .autoOffsetReset("earliest")
                .build();
    }

    @Bean
    @Qualifier("GenreViewEventConsumerConfig")
    public CustomConsumerConfig genreViewConsumerConfig() {
        // Todo: read config details from yaml
        return CustomConsumerConfig.builder()
                .groupId("GenreViewConsumerGroup")
                .pollTimeOutDuration(30000L)
                .numWorkers(3)
                .maxRetries(1)
                .topics(Collections.singletonList(EventConstants.GENRE_VIEW_EVENT_TOPIC))
                .bootstrapServers("localhost:9092")
                .fetchMinBytes(50 * 1024)
                .fetchMaxWait(7000)
                .enableAutoCommit(false)
                .autoOffsetReset("earliest")
                .build();
    }

    @Bean
    @Qualifier("RateMovieConsumerConfig")
    public CustomConsumerConfig rateMovieConsumerConfig() {
        // Todo: read config details from yaml
        // Todo: change all primitives in config to classes and use defaults if null
        return CustomConsumerConfig.builder()
                .groupId("RateMovieConsumerGroup")
                .pollTimeOutDuration(30000L)
                .numWorkers(3)
                .maxRetries(1)
                .topics(Collections.singletonList(EventConstants.RATE_MOVIE_EVENT_TOPIC))
                .bootstrapServers("localhost:9092")
                .fetchMinBytes(50 * 1024)
                .fetchMaxWait(7000)
                .enableAutoCommit(false)
                .autoOffsetReset("earliest")
                .build();
    }

    @Bean
    public KConsumer<String, AddToBucketDTO> startAddToBucketEventConsumer(
            @Qualifier("AddToBucketEventConsumerConfig") CustomConsumerConfig addToBucketConsumerConfig,
            ProcessorManager processorManager,
            ProcessorValidator processorValidator,
            @Qualifier("StringDeserializer") Deserializer<String> keyDeserializer,
            @Qualifier("AddToBucketEventDeserializer") Deserializer<AddToBucketDTO> valueDeserializer) {
        KConsumer<String, AddToBucketDTO> consumer = getKConsumer(addToBucketConsumerConfig, processorManager,
                processorValidator, keyDeserializer, valueDeserializer);
        consumer.bind();
        return consumer;
    }

    @Bean
    public KConsumer<String, DetailsDTO> startDetailsEventConsumers(
            @Qualifier("DetailsEventConsumerConfig") CustomConsumerConfig detailsConsumerConfig,
            ProcessorManager processorManager,
            ProcessorValidator processorValidator,
            @Qualifier("StringDeserializer") Deserializer<String> keyDeserializer,
            @Qualifier("DetailsEventDeserializer") Deserializer<DetailsDTO> valueDeserializer) {
        KConsumer<String, DetailsDTO> consumer = getKConsumer(detailsConsumerConfig, processorManager,
                processorValidator, keyDeserializer, valueDeserializer);
        consumer.bind();
        return consumer;
    }

    @Bean
    public KConsumer<String, GenreViewDTO> startGenreViewEventConsumers(
            @Qualifier("GenreViewEventConsumerConfig") CustomConsumerConfig genreViewConsumerConfig,
            ProcessorManager processorManager,
            ProcessorValidator processorValidator,
            @Qualifier("StringDeserializer") Deserializer<String> keyDeserializer,
            @Qualifier("GenreViewEventDeserializer") Deserializer<GenreViewDTO> valueDeserializer) {
        KConsumer<String, GenreViewDTO> consumer = getKConsumer(genreViewConsumerConfig, processorManager,
                processorValidator, keyDeserializer, valueDeserializer);
        consumer.bind();
        return consumer;
    }

    @Bean
    public KConsumer<String, RateMovieDTO> startRateMovieEventConsumers(
            @Qualifier("RateMovieConsumerConfig") CustomConsumerConfig rateMovieConsumerConfig,
            ProcessorManager processorManager,
            ProcessorValidator processorValidator,
            @Qualifier("StringDeserializer") Deserializer<String> keyDeserializer,
            @Qualifier("RateMovieEventDeserializer") Deserializer<RateMovieDTO> valueDeserializer) {
        KConsumer<String, RateMovieDTO> consumer = getKConsumer(rateMovieConsumerConfig, processorManager,
                processorValidator, keyDeserializer, valueDeserializer);
        consumer.bind();
        return consumer;
    }

    private <T> Deserializer<T> getKafkaDeserializer(ObjectMapper objectMapper, Class<T> className) {
        return new Deserializer<T>() {
            @Override
            public T deserialize(String topic, byte[] data) {
                try {
                    return objectMapper.readValue(data, className);
                } catch (IOException e) {
                    String msg = String.format("Failed to deserialize data from topic %s", topic);
                    throw new EvidenceServiceNonRetriableException(msg, e);
                }
            }
        };
    }

    private <T extends EventDTO> KConsumer<String, T> getKConsumer(CustomConsumerConfig customConsumerConfig,
                                                                   ProcessorManager processorManager,
                                                                   ProcessorValidator processorValidator,
                                                                   Deserializer<String> keyDeserializer,
                                                                   Deserializer<T> valueDeserializer) {
        return new KConsumer<String, T>(customConsumerConfig, keyDeserializer, valueDeserializer) {
            @Override
            protected void onFailedCommit(Exception exception) {

            }

            @Override
            public void consume(List<T> eventDTOList) {
                try {
                    eventDTOList.forEach(processorValidator::validateEvent);
                    processorManager.processEvent(EventBOFactory.createBO(eventDTOList));
                } catch (EvidenceServiceInvalidInputException e) {
                    log.warn("Failed to consume from topic {} due to invalid input", customConsumerConfig.getTopics(), e);
                } catch (EvidenceServiceNonRetriableException e) {
                    log.error("Failed to consume from topic {}", customConsumerConfig.getTopics(), e);
                } catch (Exception e) {
                    log.error("Unexpected error when consuming from topic {}", customConsumerConfig.getTopics(), e);
                }
            }
        };
    }
}
