package com.ceiba.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.controllers.dto.PrestamoMapper;
import com.ceiba.biblioteca.controllers.dto.PrestamoRequestPost;
import com.ceiba.biblioteca.controllers.dto.PrestamoResponseGet;
import com.ceiba.biblioteca.controllers.dto.PrestamoResponsePost;
import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.services.PrestamoService;

//definimos la ruta
@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {
    // instanciamos la clase prestamo servicio para poder acceder a sus funciones
    @Autowired
    private PrestamoService prestamoService;

    // declaramos el controlador del endpoint /prestamo [POST] el cual recibe un
    // objeto de la clase PrestamoRequestPost y retorna un objeto de tipo y retorna
    // un objeto de tipo ResponseEntity el cual tiene en su cuerpo la informacion
    // del objeto PrestamoResponsePost
    @PostMapping
    public ResponseEntity<PrestamoResponsePost> prestarLibro(@Validated @RequestBody PrestamoRequestPost prestamo) {
        // registramos el prestamo por medio de la funcion .crearPrestamo() de la clase
        // PrestamoService la cual recibe un objeto de tipo Prestamo y retorna un objeto
        // de tipo Prestamo
        Prestamo respuesta = prestamoService.crearPrestamo(PrestamoMapper.toEntity(prestamo));

        // convertimos el objeto Prestamo a un objeto PrestamoResponsePost con la
        // funcion .toRSP() de la clase PrestamoMapper y lo enviamos en una respuesta al
        // usuario en el cuerpo de un objeto ResponseEntity con status OK o 200
        return new ResponseEntity<>(
                PrestamoMapper.toRSP(respuesta), HttpStatus.OK);
    }

    // declaramos el controlador del endpoint /prestamo/{id} [GET] el cual recibe el
    // id del prestamo que queremos obtener por medio de una variable en la ruta y
    // retorna un objeto de tipo ResponseEntity que tiene en el cuerpo un objeto de
    // tipo PrestamoResponseGet
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponseGet> consultarPrestamo(@PathVariable Integer id) {
        // obtenemos el prestamo por medio de la funcion .consultarPrestamo() de la
        // clase PrestamoService el cual recibe el id y retorna un objeto de tipo
        // Prestamo
        Prestamo respuesta = prestamoService.consultarPrestamo(id);

        // convertimos el objeto Prestamo a un objeto PrestamoResponseGet con la funcion
        // .toRSG() de la clase PrestamoMapper y lo enviamos en una respuesta al usuario
        // en el cuerpo de un objeto ResponseEntity con status OK o 200
        return new ResponseEntity<>(PrestamoMapper.toRSG(respuesta), HttpStatus.OK);

    }
}
