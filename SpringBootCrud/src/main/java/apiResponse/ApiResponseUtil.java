package apiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> create(boolean success, String message, T data, int status) {
        ApiResponse<T> response = new ApiResponse<>(success, message, data, status);
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }
}