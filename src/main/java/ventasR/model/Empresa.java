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


    private ArrayList<Ventas> listaVentas;
    private ArrayList<ClienteJuridico> clientesJuridicos;
    private ArrayList<ClienteNatural> clientesNaturales;

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

    }

