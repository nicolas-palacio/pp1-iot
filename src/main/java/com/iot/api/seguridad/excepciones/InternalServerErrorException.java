package com.iot.api.seguridad.excepciones;

public class InternalServerErrorException extends  RuntimeException{
    private static final String DESCRIPTION= "Internal Server Error (500)";

    public InternalServerErrorException(String detail){
        super(DESCRIPTION + "."+ detail);
    }
}
