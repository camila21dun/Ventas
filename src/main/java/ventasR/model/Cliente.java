package ventasR.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private String nombre;
    private String apellido;
    private String identificacion;
    private String direccion;
    private String telefono;
}
