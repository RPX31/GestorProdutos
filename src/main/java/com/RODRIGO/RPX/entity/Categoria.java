package com.RODRIGO.RPX.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50 , message = "O nome da Categoria deve ter entre 3 e 50 caracteres")
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos; 

    public String getNome(){return nome;}
    public void setNome( String nome){this.nome = nome;} 
}
