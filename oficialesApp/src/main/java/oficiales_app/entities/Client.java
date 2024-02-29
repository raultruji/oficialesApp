package oficiales_app.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_client")
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
	
	public String getFechaNacimientoFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nacimiento.format(formatter);
    }
}
