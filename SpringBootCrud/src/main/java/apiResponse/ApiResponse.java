package apiResponse;


import com.crud.SpringBootCrud.entities.UserEntity;

public class ApiResponse<T> {
    private int status;
    private boolean success;

    private String message;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, Object data, int status) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.data = data;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


