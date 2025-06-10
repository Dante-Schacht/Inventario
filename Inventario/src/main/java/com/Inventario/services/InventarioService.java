package com.Inventario.services;

import com.Inventario.dto.InventarioDTO;
import com.Inventario.models.Inventario;
import com.Inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioDTO> listar() {
        return inventarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO obtenerPorId(Integer id) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(id);
        return inventarioOpt.map(this::toDTO).orElse(null);
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto) {
        if (!inventarioRepository.existsById(id)) {
            return null;
        }
        dto.setId(id);
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public void eliminar(Integer id) {
        inventarioRepository.deleteById(id);
    }

    // Métodos auxiliares de conversión
    private InventarioDTO toDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setProductoId(inventario.getProductoId());
        dto.setCantidad(inventario.getCantidad());
        dto.setUbicacion(inventario.getUbicacion());
        return dto;
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setId(dto.getId());
        inventario.setProductoId(dto.getProductoId());
        inventario.setCantidad(dto.getCantidad());
        inventario.setUbicacion(dto.getUbicacion());
        return inventario;
    }
}
