package ventasR.model;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clientes {

    private String nombre;
    private String apellido;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String email;
    private String nit;
    private LocalDate fechaNacimiento;


}
