package oficiales_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Persona {

	@Column
	@NotNull
	private String Nombre;
	@Column
	@NotNull
	private String apellido1;
	@Column
	private String apellido2;
	
}
