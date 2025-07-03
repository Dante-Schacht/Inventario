package com.Inventario.assembler;

import com.Inventario.controllers.InventarioController;
import com.Inventario.dto.InventarioDTO;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioAssembler implements RepresentationModelAssembler<InventarioDTO, EntityModel<InventarioDTO>> {

    @Override
    public EntityModel<InventarioDTO> toModel(InventarioDTO dto) {
        return EntityModel.of(dto,
            linkTo(methodOn(InventarioController.class).obtener(dto.getId())).withSelfRel(),
            linkTo(methodOn(InventarioController.class).listar()).withRel("inventario")
        );
    }
}
