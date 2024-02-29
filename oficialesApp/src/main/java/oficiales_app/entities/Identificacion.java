package oficiales_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Identificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_factura")
	private Long id;
	
	@Column
	private String numero;
	@Column
	private String nacionalidad;
	@Column(name="tipo_ident")
	@Enumerated(EnumType.STRING)
	private TipoIdent tipoIdent;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
	private Client client;
}
