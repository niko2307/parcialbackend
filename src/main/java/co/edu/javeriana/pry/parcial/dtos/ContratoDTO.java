package co.edu.javeriana.pry.parcial.dtos;

import co.edu.javeriana.pry.parcial.entities.Estadocontrato;
import lombok.Data;

@Data
public class ContratoDTO {
    private double valor;
    private String nombreContratante;
    private String documentoContratante;
    private String nombreContratantista;
    private String documentoContratantista;
    private String fechaInicial;
    private String fechaFinal;
    private Estadocontrato estado;
}
