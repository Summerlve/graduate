package json;

/**
 * Created by Summer on 3/30/16.
 */
public class OperationResult {
    private Integer error;
    private Integer status_code;
    private String message;

    public OperationResult() {}

    public OperationResult(Integer status_code, Integer error, String message) {
        this.status_code = status_code;
        this.error = error;
        this.message = message;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
