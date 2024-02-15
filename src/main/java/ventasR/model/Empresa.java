package ventasR.model;

import lombok.Getter;
import lombok.Setter;
import ventasR.exception.AtributoVacioException;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
public class Empresa {

    private ArrayList<Clientes> listaclientes;
    private ArrayList<Ventas> listaVentas;
    private ArrayList<Clientes> clientes;

    private static Empresa empresa;

    public static Empresa getInstance() {
        if(empresa == null){
            empresa = new Empresa();
        }

        return empresa;
    }


    public Clientes registrarCliente(String nombre, String apellido, String identificacion, String direccion, String telefono, String email, String nit, LocalDate fechaNacimiento) throws AtributoVacioException {

        if(identificacion == null || identificacion.isBlank()){
            throw new AtributoVacioException("La identificacion es obligatoria");
        }
        if(nombre == null || nombre.isBlank()){
            throw new AtributoVacioException("El nombre es obligatorio");
        }

        if(telefono == null || telefono.isBlank()){
            throw new AtributoVacioException("El telefono es obligatorio");
        }

        if(email == null || email.isBlank()){
            throw new AtributoVacioException("El email es obligatorio");
        }
        if(nit == null || nit.isBlank()){
            throw new AtributoVacioException("El nit es obligatorio");
        }

        if( obtenerCliente(identificacion) != null ){
            throw new AtributoVacioException("La cédula "+identificacion+" ya está registrada");
        }

        //Demás validaciones

        Clientes cliente = Clientes.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .apellido(apellido)
                .email(email)
                .direccion(direccion)
                .telefono(telefono)
                .fechaNacimiento(fechaNacimiento)
                .build();

        clientes.add(cliente);

        return cliente;
    }

    public Clientes obtenerCliente(String identificacion){
        return listaclientes.stream().filter(c -> c.getIdentificacion().equals(identificacion)).findFirst().orElse(null);
    }






}
