package com.RODRIGO.RPX.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_marca")
public class Marca {
    
    @NotNull(message = "O nome da Marca é obrigatorio!")
    @Size(min = 3 , max = 50, message = "o nome da marca deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @OneToMany(mappedBy = "marca") 
    private List<Produto> produtos;

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public List<Produto> getProdutos(){return produtos;} 

    public void setProdutos(List<Produto> produtos){this.produtos = produtos;}

}
