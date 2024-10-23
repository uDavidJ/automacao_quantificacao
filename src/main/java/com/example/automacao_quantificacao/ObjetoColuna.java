package com.example.automacao_quantificacao;

public class ObjetoColuna {


    private String nome_classe;
    private String descricao_classe;
    private String quantidade_classe_string;
    private String totalQuantidade_classe_string;
    private Integer quantidade_classe;
    private Integer totalQuantidade_classe;

    public ObjetoColuna(String nome_classe, String descricao_classe, Integer quantidade_classe, Integer totalQuantidade_classe) {
        this.nome_classe = nome_classe;
        this.descricao_classe = descricao_classe;
        this.quantidade_classe = quantidade_classe;
        this.totalQuantidade_classe = totalQuantidade_classe;
    }

    public ObjetoColuna(String nome_classe, String descricao_classe, String quantidade_classe, String totalQuantidade_classe) {
        this.nome_classe = nome_classe;
        this.descricao_classe = descricao_classe;
        this.quantidade_classe_string = quantidade_classe;
        this.totalQuantidade_classe_string = totalQuantidade_classe;
    }

    public String getNome_classe() {
        return nome_classe;
    }

    public void setNome_classe(String nome_classe) {
        this.nome_classe = nome_classe;
    }

    public String getDescricao_classe() {
        return descricao_classe;
    }

    public void setDescricao_classe(String descricao_classe) {
        this.descricao_classe = descricao_classe;
    }

    public Integer getQuantidade_classe() {
        return quantidade_classe;
    }

    public void setQuantidade_classe(Integer quantidade_classe) {
        this.quantidade_classe = quantidade_classe;
    }

    public Integer getTotalQuantidade_classe() {
        return totalQuantidade_classe;
    }

    public String getQuantidade_classe_string() {
        return quantidade_classe_string;
    }

    public void setQuantidade_classe_string(String quantidade_classe_string) {
        this.quantidade_classe_string = quantidade_classe_string;
    }

    public String getTotalQuantidade_classe_string() {
        return totalQuantidade_classe_string;
    }

    public void setTotalQuantidade_classe_string(String totalQuantidade_classe_string) {
        this.totalQuantidade_classe_string = totalQuantidade_classe_string;
    }

    public void setTotalQuantidade_classe(Integer totalQuantidade_classe) {
        this.totalQuantidade_classe = totalQuantidade_classe;
    }
}
