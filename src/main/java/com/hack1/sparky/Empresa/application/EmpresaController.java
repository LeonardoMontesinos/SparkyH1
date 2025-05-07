package com.hack1.sparky.Empresa.application;

import com.hack1.sparky.Empresa.domain.EmpresaService;
import com.hack1.sparky.Empresa.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> crear(@RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(empresaService.crearEmpresa(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listar() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.obtenerEmpresa(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> actualizar(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestBody EmpresaStatusUpdateDTO dto) {
        empresaService.cambiarEstado(id, dto.isActiva());
        return ResponseEntity.noContent().build();
    }

    // Falta implementar: GET /{id}/consumption
}