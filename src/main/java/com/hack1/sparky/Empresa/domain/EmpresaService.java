package com.hack1.sparky.Empresa.domain;

import com.hack1.sparky.Empresa.dto.EmpresaDTO;
import com.hack1.sparky.Empresa.dto.EmpresaResponseDTO;
import com.hack1.sparky.Empresa.infrastructure.EmpresaRepository;
import com.hack1.sparky.IA.domain.ModeloIA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaResponseDTO crearEmpresa(EmpresaDTO dto) {
        // Convertir el String modeloIA a un enum ModeloIA
        ModeloIA modeloIA = ModeloIA.valueOf(dto.getModeloIA());

        Empresa empresa = new Empresa(dto.getNombre(), dto.getRuc(), dto.getFechaAfiliacion(), dto.isActiva(), modeloIA);
        empresaRepository.save(empresa);
        return toResponse(empresa);
    }

    public List<EmpresaResponseDTO> listarEmpresas() {
        return empresaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EmpresaResponseDTO obtenerEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return toResponse(empresa);
    }

    public EmpresaResponseDTO actualizarEmpresa(Long id, EmpresaDTO dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresa.setNombre(dto.getNombre());
        empresa.setRuc(dto.getRuc());
        empresa.setFechaAfiliacion(dto.getFechaAfiliacion());
        empresa.setActiva(dto.isActiva());

        // Convertir el String modeloIA a un enum ModeloIA de forma segura
        if (dto.getModeloIA() != null) {
            try {
                ModeloIA modeloIA = ModeloIA.valueOf(dto.getModeloIA());
                empresa.setModeloIA(modeloIA);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Modelo de IA no válido: " + dto.getModeloIA());
            }
        }

        empresaRepository.save(empresa);
        return toResponse(empresa);
    }

    public void cambiarEstado(Long id, boolean activa) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresa.setActiva(activa);
        empresaRepository.save(empresa);
    }

    private EmpresaResponseDTO toResponse(Empresa empresa) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setId(empresa.getId());
        dto.setNombre(empresa.getNombre());
        dto.setRuc(empresa.getRuc());
        dto.setModeloIA(empresa.getModeloIA().getNombreEnGithub()); // Usar name() para obtener el nombre del enum
        dto.setActiva(empresa.isActiva());
        return dto;
    }
}

