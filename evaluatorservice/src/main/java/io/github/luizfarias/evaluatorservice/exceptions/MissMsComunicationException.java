package io.github.luizfarias.evaluatorservice.exceptions;

import lombok.Getter;

public class MissMsComunicationException extends Exception{

    @Getter
    private Integer status;

    public MissMsComunicationException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
