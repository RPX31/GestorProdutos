
package com.RODRIGO.RPX.entity;

import com.RODRIGO.RPX.dto.request.CategoriaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
            min = 3,
            max = 50,
            message = "O nome da Categoria deve ter entre 3 e 50 caracteres"
    )
    @Column(unique = true)
    private String nome;

    // NOVO: construtor que recebe o DTO.
    public Categoria(CategoriaRequestDTO request) {
        this.nome = request.nome();
    }
}
