package com.bigv.firstspring;

public class HelloResponse {
    private String message;

    public HelloResponse(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
