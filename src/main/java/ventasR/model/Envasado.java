package ventasR.model;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envasado {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double valorUnitario;
    private int cantExistencia;
    private LocalDate fechaEnvasado;
    private double peso;
    private Pais pais;

}
