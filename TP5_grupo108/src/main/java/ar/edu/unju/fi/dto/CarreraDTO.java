package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
	@NotNull(message = "Código no puede estar vacío")
	private long codigo;

	@NotBlank(message = "Nombre no puede estar vacío")
	private String nombre;

	@Min(value = 1, message = "Cantidad de años debe ser al menos 1")
	private int cantAnios;

	private boolean estado;
}