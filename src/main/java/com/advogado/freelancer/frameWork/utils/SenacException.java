package com.advogado.freelancer.frameWork.utils;
import java.util.ArrayList;
import java.util.List;

public class SenacException extends Exception {
    private List<String> messages = new ArrayList<>();

    public SenacException(String message) {
        super(message);
    }

    public SenacException(List<String> msg){
        this.messages = msg;
    }

    public List<String> getMessages() {
        if(messages.isEmpty()){
            messages.add(super.getMessage());
        }

        return messages;
    }

    public String getMessage(){
        if(messages.isEmpty()){
            return super.getMessage();
        }

        return messages.toString();
    }
}
