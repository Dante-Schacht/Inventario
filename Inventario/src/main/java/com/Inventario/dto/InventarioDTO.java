package com.Inventario.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
@Data
public class InventarioDTO extends RepresentationModel<InventarioDTO>{

    private Integer id;
    private Integer productoId;
    private Integer cantidad;
    private String ubicacion;
    private LocalDateTime fechaActualizacion;

}
