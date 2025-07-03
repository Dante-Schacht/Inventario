package com.Inventario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.Inventario.dto.InventarioDTO;
import com.Inventario.services.InventarioService;
import com.Inventario.assembler.InventarioAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<InventarioDTO>> listar() {
        List<EntityModel<InventarioDTO>> inventarios = inventarioService.listar()
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
            linkTo(methodOn(InventarioController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioDTO>> obtener(@PathVariable Integer id) {
        InventarioDTO dto = inventarioService.obtenerPorId(id);
        return dto != null ?
            ResponseEntity.ok(assembler.toModel(dto)) :
            ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntityModel<InventarioDTO>> crear(@RequestBody InventarioDTO dto) {
        InventarioDTO creado = inventarioService.crear(dto);
        return ResponseEntity
            .created(linkTo(methodOn(InventarioController.class).obtener(creado.getId())).toUri())
            .body(assembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioDTO>> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
        InventarioDTO actualizado = inventarioService.actualizar(id, dto);
        return actualizado != null ?
            ResponseEntity.ok(assembler.toModel(actualizado)) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
