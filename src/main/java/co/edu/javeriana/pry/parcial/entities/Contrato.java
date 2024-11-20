package co.edu.javeriana.pry.parcial.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "contrato")
@Data
public class Contrato {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "nombre_contratante", nullable = false, length = 255)
    private String nombreContratante;

    @Column(name = "documento_contratante", nullable = false, length = 255)
    private String documentoContratante;

    @Column(name = "nombre_contratantista", nullable = false, length = 255)
    private String nombreContratantista;

    @Column(name = "documento_contratantista", nullable = false, length = 255)
    private String documentoContratantista;

    @Column(name = "fecha_inicial", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "fecha_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Enumerated(EnumType.STRING) // Usa el nombre del enum como valor en la base de datos
    @Column(name = "estado", nullable = false)
    private Estadocontrato estado;
}
