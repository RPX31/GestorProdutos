package com.RODRIGO.RPX.entity;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "tb_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50 , message = "O nome da Categoria deve ter entre 3 e 50 caracteres")
    @Column(unique = true)
    private String nome;
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos; 
    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;}
    public String getNome(){return nome;}
    public void setNome( String nome){this.nome = nome;} 
    public List<Produto> getProdutos(){return produtos;} 
    public void setProdutos(List<Produto> produtos){this.produtos = produtos;}
}
