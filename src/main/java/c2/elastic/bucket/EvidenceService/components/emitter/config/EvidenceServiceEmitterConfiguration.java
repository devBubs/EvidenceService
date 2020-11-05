package c2.elastic.bucket.EvidenceService.components.emitter.config;

import c2.elastic.bucket.EvidenceService.constants.EventConstants;
import c2.elastic.bucket.EvidenceService.model.addToBucket.AddToBucketDTO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.EvidenceService.model.details.DetailsDTO;
import c2.elastic.bucket.EvidenceService.model.genreView.GenreViewDTO;
import c2.elastic.bucket.EvidenceService.model.rateMovie.RateMovieDTO;
import c2.elastic.bucket.GenBucket.msgqueue.Message;
import c2.elastic.bucket.GenBucket.msgqueue.Producer;
import c2.elastic.bucket.GenBucket.msgqueue.kafka.CustomProducerConfig;
import c2.elastic.bucket.GenBucket.msgqueue.kafka.KProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class EvidenceServiceEmitterConfiguration {

    @Bean(name = "AddToBucketProducerConfig")
    CustomProducerConfig getAddToBucketProducerConfig() {
        return CustomProducerConfig.builder()
                .bootstrapServers("localhost:9092")
                .numWorkers(2)
                .topic(EventConstants.ADD_TO_BUCKET_EVENT_TOPIC)
                .producerPoolMaxWait(20000)
                .build();
    }

    @Bean(name = "DetailsProducerConfig")
    CustomProducerConfig getDetailsProducerConfig() {
        return CustomProducerConfig.builder()
                .bootstrapServers("localhost:9092")
                .numWorkers(2)
                .topic(EventConstants.DETAILS_EVENT_TOPIC)
                .producerPoolMaxWait(20000)
                .build();
    }

    @Bean(name = "GenreViewProducerConfig")
    CustomProducerConfig getGenreViewProducerConfig() {
        return CustomProducerConfig.builder()
                .bootstrapServers("localhost:9092")
                .numWorkers(2)
                .topic(EventConstants.GENRE_VIEW_EVENT_TOPIC)
                .producerPoolMaxWait(20000)
                .build();
    }

    @Bean(name = "RateMovieProducerConfig")
    CustomProducerConfig getRateMovieProducerConfig() {
        return CustomProducerConfig.builder()
                .bootstrapServers("localhost:9092")
                .numWorkers(2)
                .topic(EventConstants.RATE_MOVIE_EVENT_TOPIC)
                .producerPoolMaxWait(20000)
                .build();
    }

    @Bean(name = "AllProducersMap")
    Map<String, ? extends Producer<String, ? extends EventDTO>> getAllProducerMap(
            @Qualifier("AddToBucketEventProducer") KProducer<String, AddToBucketDTO> addToBucketEventProducer,
            @Qualifier("DetailsEventProducer") KProducer<String, DetailsDTO> detailsEventProducer,
            @Qualifier("GenreViewEventProducer") KProducer<String, GenreViewDTO> genreViewEventProducer,
            @Qualifier("RateMovieEventProducer") KProducer<String, RateMovieDTO> rateMovieEventProducer) {
        Map<String, KProducer<String, ? extends EventDTO>> producerMap = Maps.newHashMap();
        producerMap.put(EventConstants.ADD_TO_BUCKET_EVENT, addToBucketEventProducer);
        producerMap.put(EventConstants.DETAILS_EVENT, detailsEventProducer);
        producerMap.put(EventConstants.GENRE_VIEW_EVENT, genreViewEventProducer);
        producerMap.put(EventConstants.RATE_MOVIE_EVENT, rateMovieEventProducer);
        producerMap.forEach((key, value) -> value.bind());
        return producerMap;
    }

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Qualifier("KafkaStringSerializer")
    Serializer<String> getStringSerializer() {
        return new StringSerializer();
    }

    @Bean
    @Qualifier("AddToBucketEventSerializer")
    Serializer<AddToBucketDTO> getAddToBucketEventSerializer(ObjectMapper objectMapper) {
        return new Serializer<AddToBucketDTO>() {

            @Autowired
            ObjectMapper objectMapper;

            @Override
            public byte[] serialize(String topic, AddToBucketDTO data) {
                byte[] bytes = null;
                try {
                    bytes = objectMapper.writeValueAsBytes(data);
                } catch (JsonProcessingException e) {
                    System.out.println("Failed to serialize event: " + data.getEventType());
                }
                return bytes;
            }
        };
    }

    @Bean
    @Qualifier("AddToBucketEventProducer")
    KProducer<String, AddToBucketDTO> getAddToBucketEventProducer(@Qualifier("AddToBucketProducerConfig") CustomProducerConfig configuration,
                                                                  @Qualifier("AddToBucketEventSerializer") Serializer<AddToBucketDTO> valueSerializer,
                                                                  @Qualifier("KafkaStringSerializer") Serializer<String> keySerializer) {
        return new KProducer<String, AddToBucketDTO>(configuration, keySerializer, valueSerializer) {
            @Override
            protected Message<String, AddToBucketDTO> convert(AddToBucketDTO addToBucketDTO) {
                return Message.<String, AddToBucketDTO>builder().routingKey(addToBucketDTO.getUserId())
                        .data(addToBucketDTO)
                        .build();
            }
        };
    }

    @Bean
    @Qualifier("DetailsEventSerializer")
    Serializer<DetailsDTO> getDetailsEventSerializer(ObjectMapper objectMapper) {
        return new Serializer<DetailsDTO>() {

            @Autowired
            ObjectMapper objectMapper;

            @Override
            public byte[] serialize(String topic, DetailsDTO data) {
                byte[] bytes = null;
                try {
                    bytes = objectMapper.writeValueAsBytes(data);
                } catch (JsonProcessingException e) {
                    System.out.println("Failed to serialize event: " + data.getEventType());
                }
                return bytes;
            }
        };
    }

    @Bean
    @Qualifier("DetailsEventProducer")
    KProducer<String, DetailsDTO> getDetailsEventProducer(@Qualifier("DetailsProducerConfig") CustomProducerConfig configuration,
                                                          @Qualifier("DetailsEventSerializer") Serializer<DetailsDTO> valueSerializer,
                                                          @Qualifier("KafkaStringSerializer") Serializer<String> keySerializer) {
        return new KProducer<String, DetailsDTO>(configuration, keySerializer, valueSerializer) {
            @Override
            protected Message<String, DetailsDTO> convert(DetailsDTO detailsDTO) {
                return Message.<String, DetailsDTO>builder().routingKey(detailsDTO.getUserId())
                        .data(detailsDTO)
                        .build();
            }
        };
    }


    @Bean
    @Qualifier("GenreViewEventSerializer")
    Serializer<GenreViewDTO> getGenreViewEventSerializer(ObjectMapper objectMapper) {
        return new Serializer<GenreViewDTO>() {

            @Autowired
            ObjectMapper objectMapper;

            @Override
            public byte[] serialize(String topic, GenreViewDTO data) {
                byte[] bytes = null;
                try {
                    bytes = objectMapper.writeValueAsBytes(data);
                } catch (JsonProcessingException e) {
                    System.out.println("Failed to serialize event: " + data.getEventType());
                }
                return bytes;
            }
        };
    }

    @Bean
    @Qualifier("GenreViewEventProducer")
    KProducer<String, GenreViewDTO> getGenreViewEventProducer(@Qualifier("GenreViewProducerConfig") CustomProducerConfig configuration,
                                                              @Qualifier("GenreViewEventSerializer") Serializer<GenreViewDTO> valueSerializer,
                                                              @Qualifier("KafkaStringSerializer") Serializer<String> keySerializer) {
        return new KProducer<String, GenreViewDTO>(configuration, keySerializer, valueSerializer) {
            @Override
            protected Message<String, GenreViewDTO> convert(GenreViewDTO genreViewDTO) {
                return Message.<String, GenreViewDTO>builder().routingKey(genreViewDTO.getUserId())
                        .data(genreViewDTO)
                        .build();
            }
        };
    }


    @Bean
    @Qualifier("RateMovieEventSerializer")
    Serializer<RateMovieDTO> getRateMovieEventSerializer(ObjectMapper objectMapper) {
        return new Serializer<RateMovieDTO>() {

            @Autowired
            ObjectMapper objectMapper;

            @Override
            public byte[] serialize(String topic, RateMovieDTO data) {
                byte[] bytes = null;
                try {
                    bytes = objectMapper.writeValueAsBytes(data);
                } catch (JsonProcessingException e) {
                    System.out.println("Failed to serialize event: " + data.getEventType());
                }
                return bytes;
            }
        };
    }

    @Bean
    @Qualifier("RateMovieEventProducer")
    KProducer<String, RateMovieDTO> getRateMovieEventProducer(@Qualifier("RateMovieProducerConfig") CustomProducerConfig configuration,
                                                              @Qualifier("RateMovieEventSerializer") Serializer<RateMovieDTO> valueSerializer,
                                                              @Qualifier("KafkaStringSerializer") Serializer<String> keySerializer) {
        return new KProducer<String, RateMovieDTO>(configuration, keySerializer, valueSerializer) {
            @Override
            protected Message<String, RateMovieDTO> convert(RateMovieDTO rateMovieDTO) {
                return Message.<String, RateMovieDTO>builder().routingKey(rateMovieDTO.getUserId())
                        .data(rateMovieDTO)
                        .build();
            }
        };
    }


}
