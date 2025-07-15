package com.RODRIGO.RPX.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_produto")
public class Produto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50 , message = "O nome da Categoria deve ter entre 3 e 50 caracteres")
    private String nome;
    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private Double preco;
    @NotNull(message = "A descricão é obrigatória")
    @Size(min = 3 ,max = 200, message = "A descrição deve ter no máximo 200 caracteres")
    private String descricao;
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "não aceita quantidades negativas")
    private Integer quantidade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
   
}
