package oficiales_app.entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name="tipo_certificado")
public class TipoCertificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_certificado")
	private Long id;
	@Column
	private String tittle;
	@Column
	private String text;
	@Column
	@Enumerated(EnumType.STRING)
	private Idioma idioma;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCertificado", cascade = CascadeType.ALL)
	private List<Certificado> certificados;
}
