package com.ceiba.biblioteca.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.entities.Prestamo;
import java.time.LocalDate;
import java.util.Optional;

//prestamoRepository es una clase que encapsula el objeto prestamo en una clase JpaRepository
//y lo relaciona con la tabla, para poder acceder directamente a los datos de esta, por medio de
//un paquete de funciones

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    // debido a que necesitamos una funcion mas especifica de las que nos
    // proporciona Jpa por defecto
    // la declaramos por su nomenclatura, nomenclatura que tomara Jpa junto con los
    // parametros y objeto de
    // retorno, para construir la funcionalidad de dicha funcion

    Optional<Prestamo> findFirstByIdentificacionUsuarioAndFechaEntregaIsAfter(
            String identificacionUsuario, LocalDate fechaEntrega);
}