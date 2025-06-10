package com.Inventario.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class InventarioDTO {

    private Integer id;
    private Integer productoId;
    private Integer cantidad;
    private String ubicacion;
    private LocalDateTime fechaActualizacion;

}
