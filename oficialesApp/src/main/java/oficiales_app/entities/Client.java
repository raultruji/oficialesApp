package oficiales_app.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="clients")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	private Long id;	
	@Embedded
	private Persona persona;
	@Column
	@Email
	private String email;
	@Column
	private LocalDate nacimiento;
	@Column(columnDefinition = "varchar(100)")
	private String observaciones;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sex", columnDefinition = "ENUM('X','V','M') default 'X'")
	private Sex sex;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Identificacion identificacion;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Adress adress;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="certificado_id")
	private List<Certificado> certificados;
	
	
	public String getFechaNacimientoFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nacimiento.format(formatter);
    }
}
