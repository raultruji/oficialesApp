package oficiales_app.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
