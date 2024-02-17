package ventasR.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ventasR.exception.AtributoVacioException;
import ventasR.exception.ElementoNoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Empresa {



    private ArrayList<ClienteJuridico> clientesJuridicos;
    private ArrayList<ClienteNatural> clientesNaturales;
    private ArrayList<Perecedero> listaPerecederos;
    private ArrayList<Refigerado> listaRefigerados;
    private ArrayList<Envasado> listaEnvasados;


    private static Empresa empresa;

    public Empresa() {
        this.clientesNaturales = new ArrayList<>();
        this.clientesJuridicos = new ArrayList<>();


    }

    public static Empresa getInstance() {
        if (empresa == null) {
            empresa = new Empresa();
        }

        return empresa;
    }


    public ClienteJuridico registrarClienteJuridico(String nombre, String apellido, String identificacion, String direccion, String telefono, String nit) throws AtributoVacioException {

        if (identificacion == null || identificacion.isBlank()) {
            throw new AtributoVacioException("La identificacion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new AtributoVacioException("El telefono es obligatorio");
        }

        if (nit == null || nit.isBlank()) {
            throw new AtributoVacioException("El nit es obligatorio");
        }

        if (obtenerClienteJuridico(identificacion) != null) {
            throw new AtributoVacioException("La cédula " + identificacion + " ya está registrada");
        }

        //Demás validaciones

        ClienteJuridico cliente = ClienteJuridico.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .apellido(apellido)
                .direccion(direccion)
                .telefono(telefono)
                .build();

        clientesJuridicos.add(cliente);

        return cliente;
    }

    public ClienteJuridico obtenerClienteJuridico(String identificacion) {
        return clientesJuridicos.stream().filter(c -> c.getIdentificacion().equals(identificacion)).findFirst().orElse(null);
    }

    public void eliminarClienteJuridico(String identificacion) throws ElementoNoEncontradoException {
        ClienteJuridico clienteAEliminar = obtenerClienteJuridico(identificacion);
        if (clienteAEliminar != null) {
            clientesJuridicos.remove(clienteAEliminar);
        } else {
            throw new ElementoNoEncontradoException("No se encontró un guía con la identificación proporcionada.");
        }
    }

    public void actualizarClienteJuridico(String nombre, String apellido, String identificacion, String direccion, String telefono, String nit) throws AtributoVacioException {
        if (identificacion == null || identificacion.isBlank()) {
            throw new AtributoVacioException("La identificacion es obligatoria");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new AtributoVacioException("El apellido es obligatorio");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new AtributoVacioException("la direccion es obligatorio");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new AtributoVacioException("El telefono es obligatorio");
        }

        if (nit == null || nit.isBlank()) {
            throw new AtributoVacioException("El nit es obligatorio");
        }
        ClienteJuridico clienteJuridico = obtenerClienteJuridico(identificacion);
        if (clienteJuridico != null) {
            clienteJuridico.setNombre(nombre);
            clienteJuridico.setApellido(apellido);
            clienteJuridico.setDireccion(direccion);
            clienteJuridico.setTelefono(telefono);
            clienteJuridico.setNit(nit);
        } else {
            throw new AtributoVacioException("No existe el usuario");
        }
    }


        public ClienteNatural registrarClienteNatural (String nombre, String apellido, String identificacion, String
        direccion, String telefono, String email, LocalDate fechaNacimiento) throws AtributoVacioException {

            if (identificacion == null || identificacion.isBlank()) {
                throw new AtributoVacioException("La identificacion es obligatoria");
            }
            if (nombre == null || nombre.isBlank()) {
                throw new AtributoVacioException("El nombre es obligatorio");
            }

            if (telefono == null || telefono.isBlank()) {
                throw new AtributoVacioException("El telefono es obligatorio");
            }

            if (email == null || email.isBlank()) {
                throw new AtributoVacioException("El email es obligatorio");
            }

            if (obtenerClienteNatural(identificacion) != null) {
                throw new AtributoVacioException("La cédula " + identificacion + " ya está registrada");
            }

            //Demás validaciones

            ClienteNatural cliente = ClienteNatural.builder()

                    .nombre(nombre)
                    .apellido(apellido)
                    .identificacion(identificacion)
                    .direccion(direccion)
                    .telefono(telefono)
                    .email(email)
                    .fechaNacimiento(fechaNacimiento)

                    .build();

            clientesNaturales.add(cliente);

            return cliente;
        }

        public ClienteNatural obtenerClienteNatural (String identificacion){
            return clientesNaturales.stream().filter(c -> c.getIdentificacion().equals(identificacion)).findFirst().orElse(null);
        }

        public void eliminarClienteNatural (String identificacion) throws ElementoNoEncontradoException {
            ClienteNatural clienteAEliminar = obtenerClienteNatural(identificacion);
            if (clienteAEliminar != null) {
                clientesNaturales.remove(clienteAEliminar);
            } else {
                throw new ElementoNoEncontradoException("No se encontró un guía con la identificación proporcionada.");
            }
        }

        public void actualizarClienteNatural (String nombre, String apellido, String identificacion, String
        direccion, String telefono, String email, LocalDate fechaNacimiento) throws AtributoVacioException
        {
            if (identificacion == null || identificacion.isBlank()) {
                throw new AtributoVacioException("La identificacion es obligatoria");
            }

            if (nombre == null || nombre.isBlank()) {
                throw new AtributoVacioException("El nombre es obligatorio");
            }
            if (apellido == null || apellido.isBlank()) {
                throw new AtributoVacioException("El apellido es obligatorio");
            }

            if (telefono == null || telefono.isBlank()) {
                throw new AtributoVacioException("El telefono es obligatorio");
            }

            if (email == null || email.isBlank()) {
                throw new AtributoVacioException("El email es obligatorio");
            }
            ClienteNatural clienteNatural = obtenerClienteNatural(identificacion);
            if (clienteNatural != null) {
                clienteNatural.setNombre(nombre);
                clienteNatural.setApellido(apellido);
                clienteNatural.setDireccion(direccion);
                clienteNatural.setTelefono(telefono);
                clienteNatural.setEmail(email);
                clienteNatural.setFechaNacimiento(fechaNacimiento);
            } else {
                throw new AtributoVacioException("No existe el usuario");
            }

        }
    public Perecedero registrarProductoPerecedero (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,LocalDate fechaVencimiento) throws AtributoVacioException {

        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }

        if (fechaVencimiento == null ) {
            throw new AtributoVacioException("ingrese fecha de vencimineto es obligatorio");
        }


        //Demás validaciones

        Perecedero perecedero = Perecedero.builder()

                .codigo(codigo)
                .nombre(nombre)
                .descripcion(descripcion)
                .valorUnitario(valorUnitario)
                .cantExistencia(cantidadExistente)
                .fechaVencimiento(fechaVencimiento)

                .build();

        listaPerecederos.add(perecedero);

        return perecedero;
    }
    public Perecedero obtenerProductoPerecedero (String codigo){
        return listaPerecederos.stream().filter(c -> c.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
    public void eliminarPerecedero (String codigo) throws ElementoNoEncontradoException {
        Perecedero perecederoAEliminar = obtenerProductoPerecedero(codigo);
        if (perecederoAEliminar != null) {
            listaPerecederos.remove(perecederoAEliminar);
        } else {
            throw new ElementoNoEncontradoException("No se encontró un producto perecedero con el codigo proporcionado.");
        }
    }
    public void actualizarProductoPerecedero (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,LocalDate fechaVencimiento) throws AtributoVacioException
    {
        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }

        if (fechaVencimiento == null ) {
            throw new AtributoVacioException("ingrese fecha de vencimineto es obligatorio");
        }

        Perecedero perecedero = obtenerProductoPerecedero(codigo);
        if (perecedero != null) {
            perecedero.setNombre(nombre);
            perecedero.setDescripcion(descripcion);
            perecedero.setCodigo(codigo);
            perecedero.setCantExistencia(cantidadExistente);
            perecedero.setFechaVencimiento(fechaVencimiento);
            perecedero.setValorUnitario(valorUnitario);
        } else {
            throw new AtributoVacioException("No existe el producto");
        }

    }
    //Refigererado
    public Refigerado registrarProductoRefigerado (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,String codigoAprovacion, int temperatura) throws AtributoVacioException {
        if (codigo == null || codigo.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }
        if (codigoAprovacion == null|| codigoAprovacion.isBlank() ) {
            throw new AtributoVacioException("ingrese el codigo de aprovacion es obligatorio");
        }
        if (temperatura == 0) {
            throw new AtributoVacioException("la temperatura es obligatorio");
        }

        //Demás validaciones

        Refigerado refigerado = Refigerado.builder()

                .codigo(codigo)
                .nombre(nombre)
                .descripcion(descripcion)
                .valorUnitario(valorUnitario)
                .cantExistencia(cantidadExistente)
                .codigoAprovacion(codigoAprovacion)
                .temperatura(temperatura)

                .build();

        listaRefigerados.add(refigerado);

        return refigerado;
    }
    public Refigerado obtenerProductoRefigerado (String codigo){
        return listaRefigerados.stream().filter(c -> c.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
    public void eliminarRefigerado (String codigo) throws ElementoNoEncontradoException {
        Refigerado refigeradoAEliminar = obtenerProductoRefigerado(codigo);
        if (refigeradoAEliminar != null) {
            listaPerecederos.remove(refigeradoAEliminar);
        } else {
            throw new ElementoNoEncontradoException("No se encontró un producto perecedero con el codigo proporcionado.");
        }
    }
    public void actualizarProductoRefigerado (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,String codigoAprovacion, int temperatura) throws AtributoVacioException
    {
        if (codigo == null || codigo.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }
        if (codigoAprovacion == null|| codigoAprovacion.isBlank() ) {
            throw new AtributoVacioException("ingrese el codigo de aprovacion es obligatorio");
        }
        if (temperatura == 0) {
            throw new AtributoVacioException("la temperatura es obligatorio");
        }

        Refigerado refigerado = obtenerProductoRefigerado(codigo);
        if (refigerado != null) {
            refigerado.setNombre(nombre);
            refigerado.setDescripcion(descripcion);
            refigerado.setCodigo(codigo);
            refigerado.setCantExistencia(cantidadExistente);
            refigerado.setCodigoAprovacion(codigoAprovacion);
            refigerado.setValorUnitario(valorUnitario);
            refigerado.setTemperatura(temperatura);
        } else {
            throw new AtributoVacioException("No existe el producto");
        }

    }
    //Envasado

    public Envasado registrarProductoEnvasado (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,LocalDate fechaEnvasado, double peso, Pais pais) throws AtributoVacioException {
        if (codigo == null || codigo.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }

        if (peso == 0) {
            throw new AtributoVacioException("el peso es obligatorio");
        }

        if (pais == null) {
            throw new AtributoVacioException("el pais es obligatorio");
        }


        //Demás validaciones

        Envasado envasado = Envasado.builder()

                .codigo(codigo)
                .nombre(nombre)
                .descripcion(descripcion)
                .valorUnitario(valorUnitario)
                .cantExistencia(cantidadExistente)
                .fechaEnvasado(fechaEnvasado)
                .peso(peso)
                .pais(pais)

                .build();

        listaEnvasados.add(envasado);

        return envasado;
    }
    public Envasado obtenerProductoEnvasado (String codigo){
        return listaEnvasados.stream().filter(c -> c.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
    public void eliminarEnvasado (String codigo) throws ElementoNoEncontradoException {
        Envasado envasadoAEliminar = obtenerProductoEnvasado(codigo);
        if (envasadoAEliminar != null) {
            listaEnvasados.remove(envasadoAEliminar);
        } else {
            throw new ElementoNoEncontradoException("No se encontró un producto perecedero con el codigo proporcionado.");
        }
    }
    public void actualizarProductoEnvasado (String codigo,String nombre, String descripcion, double  valorUnitario, int cantidadExistente
            ,LocalDate fechaEnvasado, double peso, Pais pais) throws AtributoVacioException
    {
        if (codigo == null || codigo.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new AtributoVacioException("La descripcion es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }
        if (valorUnitario == 0) {
            throw new AtributoVacioException("El valor es obligatorio");
        }
        if (peso == 0) {
            throw new AtributoVacioException("el peso es obligatorio");
        }

        if (pais == null) {
            throw new AtributoVacioException("el pais es obligatorio");
        }

        Envasado envasado = obtenerProductoEnvasado(codigo);
        if (envasado != null) {
            envasado.setNombre(nombre);
            envasado.setDescripcion(descripcion);
            envasado.setCodigo(codigo);
            envasado.setCantExistencia(cantidadExistente);
            envasado.setFechaEnvasado(fechaEnvasado);
            envasado.setValorUnitario(valorUnitario);
            envasado.setPais(pais);
            envasado.setPeso(peso);

        } else {
            throw new AtributoVacioException("No existe el producto");
        }

    }

    }

