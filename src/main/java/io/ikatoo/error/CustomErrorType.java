package io.ikatoo.error;

public class CustomErrorType {
    private String message;

    public CustomErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
