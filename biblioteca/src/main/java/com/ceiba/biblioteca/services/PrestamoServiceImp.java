package com.ceiba.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;
import com.ceiba.biblioteca.shared.enums.EnumTipoUsuario;

import java.time.DayOfWeek;
import java.time.LocalDate;

//PrestamoServiceImp es una clase que implementa los metodos definidos en la clase PrestamoService
//utilizando las clases Prestamo para manejar el objeto y PrestamoRepository para interactuar con la base de datos

@Service
public class PrestamoServiceImp implements PrestamoService {
    // instanciamos un objeto de la clase PrestamoRepository para acceder a las
    // funciones de la clase
    // atravez de este manipularemos la base de datos
    @Autowired
    private PrestamoRepository prestamoRepository;

    // implementamos la funcion de crear prestamo
    @Override
    public Prestamo crearPrestamo(Prestamo prestamo) {
        // verificamos que el codigo pertenece a un tipo de usuario valido
        if (prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_AFILIADO.valor
                || prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_EMPLEADO.valor
                || prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_INVITADO.valor) {
            // creamos un objeto tipo Prestamo y continuamos con la logica de la funcion
            Prestamo prestamoSend = new Prestamo();

            // llenamos el objeto Prestamo, con los datos que nos proporciono el parametro
            // prestamo
            prestamoSend.setIsbn(prestamo.getIsbn());
            prestamoSend.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
            prestamoSend.setTipoUsuario(prestamo.getTipoUsuario());

            // asignamos una cantidad de dias de prestamo al usuario dependiendo de su tipo
            // de usuario
            Integer dias = 0;
            if (prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_AFILIADO.valor) {
                dias = 10;
            } else {
                if (prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_EMPLEADO.valor) {
                    dias = 8;
                } else {
                    if (prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_INVITADO.valor) {
                        dias = 7;
                    }
                }
            }

            // por medio de prestamoRepository y utilizando la funcion que definimos por
            // nomenclatura accedemos a la base de datos y buscamos un prestamo registrado a
            // la identificacion del usuario (en caso de no existir un prestamo registrado
            // con esa identificacion, retornaremos null)
            Prestamo prestamoInvitado = prestamoRepository
                    .findFirstByIdentificacionUsuarioAndFechaEntregaIsAfter(
                            prestamoSend.getIdentificacionUsuario(), LocalDate.now())
                    .orElse(null);

            // validamos el tipo de usuario nuevamente, y si el usuario pertenece a un tipo
            // usuario de tipo invitado verificamos que no tenga ningun prestamo registrado
            if ((prestamo.getTipoUsuario() == EnumTipoUsuario.USUARIO_INVITADO.valor && prestamoInvitado == null)
                    || prestamo.getTipoUsuario() != EnumTipoUsuario.USUARIO_INVITADO.valor) {
                // creamos un objeto de tipo LocalDate y entero, para poder asignarle al usuario
                // la fecha en la que caducara el prestamo
                LocalDate fecha = LocalDate.now();
                int diasAgregados = 0;

                // agregamos los dias a la fecha actual
                // utilizamos la variable dias agregados y la variable dias que definimos
                // previamente, para controlar el ciclo
                while (diasAgregados < dias) {
                    // agregamos un dia a la variable fecha (inicializada en la fecha actual)
                    fecha = fecha.plusDays(1);

                    // si el dia de la semana en la que esta la variable fecha no es un sabado o un
                    // domingo aumentamos el contador de dias agregados, hasta que la cantidad e
                    // dias diferentes a sabado y domingo agregados sea igual a la cantidad de dias
                    // que le corresponde al usuario
                    if (!(fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                            || fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                        diasAgregados++;
                    }
                }

                // le asignamos al usuario la fecha en la que debera entregar el libro (la
                // cantidad de dias en la que entregara el libro no tiene en cuenta sabados ni
                // domingos)
                prestamoSend.setFechaEntrega(fecha);

                // guardamos el prestamo creado en la base de datos con la funcion .save()
                // proporcionada por PrestamoRepository (este prestamo incluye nadamas isbn,
                // identificacionUsuario, tipoDeUsuario y fechaDeEntrega, debido a que la
                // variable Id la proporciona la funcion .save() y la clase Prestamo de forma
                // automatica)
                Prestamo prestamoResponse = prestamoRepository.save(prestamoSend);

                // enviamos el dato PrestamoResponse como respuesta de la solicitud
                return prestamoResponse;

            } else {
                // si el usuario pertenece a un tipo usuario de tipo invitado y tiene un
                // prestamo registrado enviaremos una excepcion previamente configurara en la
                // clase ErrorResponse y la clase GlobalResponse400 con el siguiente mensaje "El
                // usuario con identificación xxxxxx ya tiene un libro prestado por lo cual no
                // se le puede realizar otro préstamo"
                String mensaje = "El usuario con identificación " + prestamo.getIdentificacionUsuario()
                        + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        mensaje);
            }

        } else {
            // si el codigo de prestamo Pertenece a un tipo de usuario no valido, enviaremos
            // una excepcion previamente configurada en la clase ErrorResponse y la clase
            // GlobalResponse400 con el mensaje "Tipo de usuario no permitido en la
            // biblioteca"
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tipo de usuario no permitido en la biblioteca");

        }
    }

    // impelementamos la funcion de consultar un prestamo por id
    @Override
    public Prestamo consultarPrestamo(Integer id) {
        // buscamos en la base de datos un prestamo utilizando la funcion .finById() de
        // la clase PrestamoRepository, lo cual nos retornara un prestamo (o un valor
        // nulo en caso de no existirr dicho prestamo)
        Prestamo dataOpt = prestamoRepository.findById(id).orElse(null);

        // realizamos la validacion para enviarle una excepcion al usuario en caso de no
        // encontrar un usuario con el id proporcionado
        if (dataOpt == null) {
            // enviamos al usuario una excepcion previamente configurada en la clase
            // ErrorResponse y la clase y GlobalResponse404 con el mensaje "No existe un
            // prestamo asociado a ese identificador"
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No existe un prestamo asociado al identificador " + id);
        }

        // retornamos el valor obtenido de la peticion
        return dataOpt;
    }
}
