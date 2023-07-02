package com.ellialti.earthQW.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    private String locale;
    private String conversationId;

    public Request() {
    }
}