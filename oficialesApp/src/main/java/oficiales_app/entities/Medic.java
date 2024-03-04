package oficiales_app.entities;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="medics")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Medic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medic_id")
	private Long id;
	@Embedded
	@NotNull
	private Persona persona;
	@Column(name="num_col")
	@NotNull
	private Integer numCol;
	@Column
	private String especialidad;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="certificado_id")
	private List<Certificado> certificados;
}
