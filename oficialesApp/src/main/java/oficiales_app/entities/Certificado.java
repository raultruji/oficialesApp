package oficiales_app.entities;

import java.time.LocalDateTime;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="certificados")
@RequiredArgsConstructor
public class Certificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "certificado_id")
	private Long id;
	@Column (columnDefinition = "VARCHAR(7) default 'ES_es'")
	private Locale locale;

	@Column
	private boolean oficial;
	@Column
	private double tarifa;
	@Column(name = "centro", columnDefinition = "VARCHAR(25) default 'Barcelona'")
	private String centro;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne	
	@JoinColumn(name="tipo_certificado_id")
	private TipoCertificado tipoCertificado;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name="medic_id")
	private Medic medic;
	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        
    }
}
