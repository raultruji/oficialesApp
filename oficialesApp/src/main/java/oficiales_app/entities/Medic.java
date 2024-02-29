package oficiales_app.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Medic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_medic")
	private Long id;
	@Embedded
	@NotNull
	private Persona persona;
	@Column(name="num_col")
	@NotNull
	private Integer numCol;
	@Column
	private String especialidad;
}
