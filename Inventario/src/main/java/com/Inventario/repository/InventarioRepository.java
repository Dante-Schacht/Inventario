package com.Inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inventario.models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {}
