package com.RODRIGO.RPX.entity;
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
@Table(name = "tb_marca")
public class Marca {
    @NotNull(message = "O nome da Marca é obrigatorio!")
    @Size(min = 3 , max = 50, message = "o nome da marca deve ter entre 3 e 50 caracteres")
    @Column(unique = true)
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
}
