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
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Entity
@RequiredArgsConstructor
public class Adress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_adress")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoVia tipoVia;
	@Column(name = "calle", columnDefinition = "varchar(40)")
	private String nombreVia;
	@Column(name = "numero", columnDefinition = "varchar(7)")
	private String numero;
	@Column(name = "bloque", columnDefinition = "varchar(10)")
	private String Bloque;
	@Column(name = "portal", columnDefinition = "varchar(10)")
	private String portal;
	@Column(name = "escalera", columnDefinition = "varchar(10)")
	private String escalera;
	@Column(name = "planta", columnDefinition = "varchar(10)")
	private String planta;
	@Column(name = "provincia", columnDefinition = "varchar(40)")
	private String provincia;
	@Column(name = "municipio", columnDefinition = "varchar(40)")
	private String municipio;
	@Column(name = "codigo_postal",  columnDefinition = "INT default 08000")
	private String cPostal;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
	private Client client;
}
