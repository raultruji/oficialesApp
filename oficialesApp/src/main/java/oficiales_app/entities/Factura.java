package oficiales_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_factura")
	private Long id;
	
	@Column(columnDefinition = "varchar(50)")
	private String concepto;
	@Column
	private double importe;
}
