package oficiales_app.entities;

import java.time.LocalDateTime;
import java.util.Locale;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name="certificados")
@RequiredArgsConstructor
public class Certificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_certificado")
	private Long id;
	@Column (columnDefinition = "VARCHAR(7) default 'ES_es'")
	private Locale locale;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_certificado")
	private TipoCertificado tipoCertificado;
	@Column
	private boolean oficial;
	@Column
	private double tarifa;
	@Column(name = "centro", columnDefinition = "VARCHAR(25) default 'Barcelona'")
	private String centro;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	
	@ManyToOne
	private Client client;
	@ManyToOne
	private Medic medic;
	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        
    }
}
