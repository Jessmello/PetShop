package model;

import java.io.Serializable;

public class Produto implements Serializable {
    
    private Long id;
    private String nome;
    private String descricao;
    private Animal idAnimal = new Animal();
    private String marca;
    private int quantidade;
    private double valor;
    private Integer cdTipoProduto;

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getCdTipoProduto() {
        return cdTipoProduto;
    }

    public void setCdTipoProduto(Integer cdTipoProduto) {
        this.cdTipoProduto = cdTipoProduto;
    }
    
}
