package co.edu.javeriana.pry.parcial.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import co.edu.javeriana.pry.parcial.dtos.ContratoDTO;
import co.edu.javeriana.pry.parcial.dtos.ContratoResponseDTO;
import co.edu.javeriana.pry.parcial.services.ContratoService;
import co.edu.javeriana.pry.parcial.entities.Estadocontrato;

@CrossOrigin
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {
    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    // Obtener un contrato por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> getContratoById(@PathVariable Long id) {
        ContratoResponseDTO contrato = contratoService.getContratoById(id);
        return ResponseEntity.ok(contrato);
    }

    // Crear un nuevo contrato
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContratoResponseDTO> createContrato(@RequestBody ContratoDTO contratoDTO) {
        ContratoResponseDTO createdContrato = contratoService.createContrato(contratoDTO);
        return ResponseEntity.ok(createdContrato);
    }

    // Actualizar un contrato existente
    @PutMapping("/update/{id}")
    public ResponseEntity<ContratoResponseDTO> updateContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        ContratoResponseDTO updatedContrato = contratoService.updateContrato(id, contratoDTO);
        return ResponseEntity.ok(updatedContrato);
    }

    // Eliminar un contrato por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id) {
        contratoService.deleteContrato(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los contratos
    @GetMapping("/all")
    public ResponseEntity<List<ContratoResponseDTO>> getAllContratos() {
        List<ContratoResponseDTO> contratos = contratoService.getAllContratos();
        return ResponseEntity.ok(contratos);
    }

    // Cambiar el estado de un contrato a INACTIVO
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ContratoResponseDTO> deactivateContrato(@PathVariable Long id) {
        ContratoResponseDTO updatedContrato = contratoService.deactivateContrato(id);
        return ResponseEntity.ok(updatedContrato);
    }

    // Obtener contratos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ContratoResponseDTO>> getContratosByEstado(@PathVariable String estado) {
        List<ContratoResponseDTO> contratos = contratoService.getContratosByEstado(Enum.valueOf(Estadocontrato.class, estado.toUpperCase()));
        return ResponseEntity.ok(contratos);
    }
    
}
