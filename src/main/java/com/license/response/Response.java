package com.license.response;

import java.util.Date;

public class Response {
    private int status = 200;
    private String currentTime;
    private String message;

    public Response() {
        currentTime = new Date().toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getMessage() {
        return message;
    }

    public void setSucessMessage(String message) {
        this.status = 200;
        this.message = message;
    }

    public void setErrorMessage(String message) {
        this.status = 500;
        this.message = message;
    }
}
