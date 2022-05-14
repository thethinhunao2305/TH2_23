package product.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message="Code is required")
	private String code;
	@NotBlank(message="Description is required")
	private String description;
	@Min(value = 0L, message = "The price must be positive number")
	@NotNull(message="Price is required")
	private Double price;
	
}
