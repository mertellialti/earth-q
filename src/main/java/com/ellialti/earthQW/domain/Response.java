package com.ellialti.earthQW.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private boolean status;
    private T data;

    public Response(boolean status, T data) {
        this.status = status;
        this.data = data;
    }
}