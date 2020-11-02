package c2.elastic.bucket.EvidenceService.exception;

public class EvidenceServiceNonRetriableException extends RuntimeException{

    public EvidenceServiceNonRetriableException(String message) {
        super(message);
    }

    public EvidenceServiceNonRetriableException(String message, Throwable cause) {
        super(message, cause);
    }
}
