package c2.elastic.bucket.EvidenceService.exception;

public class EvidenceServiceInvalidInputException extends RuntimeException{
    public EvidenceServiceInvalidInputException(String message) {
        super(message);
    }

    public EvidenceServiceInvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
