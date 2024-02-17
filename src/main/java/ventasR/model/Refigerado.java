package ventasR.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refigerado  {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double valorUnitario;
    private int cantExistencia;
    private String codigoAprovacion;
    private int temperatura;
}
