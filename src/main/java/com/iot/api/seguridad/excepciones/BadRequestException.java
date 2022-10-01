package com.iot.api.seguridad.excepciones;

public class BadRequestException  extends  RuntimeException{
    private static final String DESCRIPTION= "Bad Request Exception (400)";

    public BadRequestException(String detail){
        super(DESCRIPTION + "."+ detail);
    }
}
