package c2.elastic.bucket.EvidenceService.emitter.validator;

import c2.elastic.bucket.EvidenceService.exception.EvidenceServiceInvalidInputException;
import c2.elastic.bucket.EvidenceService.model.baseEvent.EventDTO;


public class EmitterValidator {
    public void validateEmitEvent(EventDTO eventDTO){
        try{

        }catch(IllegalArgumentException e){
            throw new EvidenceServiceInvalidInputException(e.getMessage(), e);
        }
    }
}
