package ventasR.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double valorUnitario;
    private int cantExistencia;
}
