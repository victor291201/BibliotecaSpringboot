package com.ceiba.biblioteca.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.entities.Prestamo;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    Optional<Prestamo> findFirstByIdentificacionUsuarioAndFechaEntregaIsAfter(
            String identificacionUsuario, LocalDate fechaEntrega);
}