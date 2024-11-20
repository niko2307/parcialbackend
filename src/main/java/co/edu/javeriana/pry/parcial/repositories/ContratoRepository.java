package co.edu.javeriana.pry.parcial.repositories;


import co.edu.javeriana.pry.parcial.entities.*;
import co.edu.javeriana.pry.parcial.entities.Estadocontrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    // Buscar contratos por estado
    List<Contrato> findByEstado(Estadocontrato estado);
}

