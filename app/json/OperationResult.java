package json;

/**
 * Created by Summer on 3/30/16.
 */
public class OperationResult {
    private Integer error;
    private Integer status_code;

    public OperationResult() {}

    public OperationResult(Integer status_code, Integer error) {
        this.status_code = status_code;
        this.error = error;
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
}
