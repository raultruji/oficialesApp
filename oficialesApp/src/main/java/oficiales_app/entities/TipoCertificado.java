package oficiales_app.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tipos_certificado")
public class TipoCertificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tipo_certificado_id")
	private Long id;
	@Column
	private String tittle;
	@Column
	private String text;
	@Column
	@Enumerated(EnumType.STRING)
	private Idioma idioma;
	
	@OneToMany(mappedBy="tipoCertificado")//	@OneToMany(mappedBy="tipo_certificado")
	private List<Certificado> certificados;
}
