package c2.elastic.bucket.EvidenceService.emitter.controller;

import c2.elastic.bucket.EvidenceService.emitter.manager.EmitterManager;
import c2.elastic.bucket.EvidenceService.emitter.util.ErrorUtil;
import c2.elastic.bucket.EvidenceService.emitter.validator.EmitterValidator;
import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;
import c2.elastic.bucket.EvidenceService.factory.EventBOFactory;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventBO;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;
import c2.elastic.bucket.GenBucket.model.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/emitter")
@RestController
public class EmitterController {
    private final EmitterManager emitterManager;
    private final EmitterValidator emitterValidator;
    private final ErrorUtil errorUtil;

    @Autowired
    public EmitterController(EmitterManager emitterManager, EmitterValidator emitterValidator, ErrorUtil errorUtil) {
        this.emitterManager = emitterManager;
        this.emitterValidator = emitterValidator;
        this.errorUtil = errorUtil;
    }

    @PostMapping("/event")
    ResponseEntity<GenericResponse<EventDTO>> emitEvent(@RequestBody EventDTO eventDTO){
        try{
            emitterValidator.validateEmitEvent(eventDTO);
            EventBO eventBO = EventBOFactory.createBO(eventDTO);
            eventBO = emitterManager.processEvent(eventBO);
            eventDTO = eventBO.convertToDTO();
            GenericResponse<EventDTO> response = GenericResponse.<EventDTO>builder()
                    .data(eventDTO)
                    .build();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (EvidenceServiceInvalidInputException e){
            log.warn("Failed to emit event due to invalid input {}", eventDTO, e);
            return errorUtil.getErrorResponse(e);
        } catch (Exception e){
            String message = "Failed to emit event due to unexpected error";
            log.warn(message);
            return errorUtil.getFaultResponse(message);
        }
    }
}
