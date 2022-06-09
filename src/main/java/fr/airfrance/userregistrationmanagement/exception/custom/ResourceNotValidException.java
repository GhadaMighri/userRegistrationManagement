package fr.airfrance.userregistrationmanagement.exception.custom;

public class ResourceNotValidException extends RuntimeException {

  public ResourceNotValidException() {
  }

  public ResourceNotValidException(String message) {
    super(message);
  }

  public ResourceNotValidException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotValidException(Throwable cause) {
    super(cause);
  }

  public ResourceNotValidException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
