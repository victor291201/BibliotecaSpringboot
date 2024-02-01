package com.ceiba.biblioteca.shared.enums;

// la enumeracion EnumTipoUsuario nos provee una forma sencilla de acceder a la informacion del tipo de 
// usuario desde cualquier parte de la aplicacion esta es equivalente al numero del tipo de usuario, asi
// por tanto internamente el valor del enum, el valor en las entidades y repositorios, seguira siengo 
// entero y este documento simplemente proporciona una forma organizada de acceder al calor entero de 
// cada tipo de usuario
public enum EnumTipoUsuario {
    // asigmanos un valor a cada tipo de usuario, por medio del constructor
    USUARIO_AFILIADO(1), USUARIO_EMPLEADO(2), USUARIO_INVITADO(3);

    // asignamos un atributo para poder recuperar el valor del tipo de usuario una
    // vez lo hallamos
    // creado
    public final int valor;

    // declaramos el constructor con el cual le podremos asignar a cada enum su
    // numero de tipo de usuario
    // correspondiente
    EnumTipoUsuario(int valor) {
        this.valor = valor;
    }
}
