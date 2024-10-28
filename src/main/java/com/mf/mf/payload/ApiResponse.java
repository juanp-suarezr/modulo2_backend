package com.mf.mf.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private Date tiempo  = new Date();
    private String mensaje;
    private T data;

    public ApiResponse(String mensaje,T data) {
        this.mensaje = mensaje;
        this.data = data;
    }
}

