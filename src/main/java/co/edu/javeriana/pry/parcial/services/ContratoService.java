package co.edu.javeriana.pry.parcial.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import co.edu.javeriana.pry.parcial.dtos.ContratoDTO;
import co.edu.javeriana.pry.parcial.dtos.ContratoResponseDTO;
import co.edu.javeriana.pry.parcial.entities.Contrato;
import co.edu.javeriana.pry.parcial.entities.Estadocontrato;
import co.edu.javeriana.pry.parcial.exceptions.ResourceNotFoundException;
import co.edu.javeriana.pry.parcial.repositories.ContratoRepository;

@Service
public class ContratoService {
    private static final String CONTRATO_NOT_FOUND = "Contrato no encontrado con id ";

    private final ContratoRepository contratoRepository;

    private final ModelMapper modelMapper;

    public ContratoService(ContratoRepository contratoRepository, ModelMapper modelMapper) {
        this.contratoRepository = contratoRepository;
        this.modelMapper = modelMapper;
    }

    // Verificar si un contrato existe
    public boolean doesContratoExist(Long contratoId) {
        return contratoRepository.existsById(contratoId);
    }

    // Obtener un contrato por ID
    public ContratoResponseDTO getContratoById(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + id));
        return modelMapper.map(contrato, ContratoResponseDTO.class);
    }

    // Crear un nuevo contrato
    public ContratoResponseDTO createContrato(ContratoDTO contratoDTO) {
        Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
        contrato.setEstado(Estadocontrato.ACTIVO); // Por defecto, el estado es ACTIVO

        contrato = contratoRepository.save(contrato);
        return modelMapper.map(contrato, ContratoResponseDTO.class);
    }

    // Actualizar un contrato existente
    public ContratoResponseDTO updateContrato(Long id, ContratoDTO contratoDTO) {
        Contrato existingContrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + id));

        modelMapper.map(contratoDTO, existingContrato); // Actualiza los valores del contrato existente
        existingContrato.setId(id); // Asegurar que el ID no cambie

        Contrato updatedContrato = contratoRepository.save(existingContrato);
        return modelMapper.map(updatedContrato, ContratoResponseDTO.class);
    }

    // Eliminar un contrato por ID
    public void deleteContrato(Long id) {
        if (!doesContratoExist(id)) {
            throw new ResourceNotFoundException(CONTRATO_NOT_FOUND + id);
        }
        contratoRepository.deleteById(id);
    }

    // Obtener todos los contratos
    public List<ContratoResponseDTO> getAllContratos() {
        List<Contrato> contratos = contratoRepository.findAll();

        if (contratos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron contratos.");
        }

        return contratos.stream()
                .map(contrato -> modelMapper.map(contrato, ContratoResponseDTO.class))
                .toList();
    }

    // Cambiar el estado de un contrato a INACTIVO
    public ContratoResponseDTO deactivateContrato(Long contratoId) {
        Contrato contrato = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRATO_NOT_FOUND + contratoId));

        contrato.setEstado(Estadocontrato.INACTIVO);
        Contrato updatedContrato = contratoRepository.save(contrato);

        return modelMapper.map(updatedContrato, ContratoResponseDTO.class);
    }

    // Obtener contratos por estado
    public List<ContratoResponseDTO> getContratosByEstado(Estadocontrato estado) {
        List<Contrato> contratos = contratoRepository.findByEstado(estado);

        if (contratos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron contratos con estado: " + estado);
        }

        return contratos.stream()
                .map(contrato -> modelMapper.map(contrato, ContratoResponseDTO.class))
                .toList();
    }
}
