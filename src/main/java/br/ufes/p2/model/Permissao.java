package br.ufes.p2.model;

public class Permissao {

    private Imagem imagem;
    private Usuario usuario;
    private String descricao;

    public Permissao() {
    }

    public Permissao(Imagem imagem, Usuario usuario, String descricao) {
        this.imagem = imagem;
        this.usuario = usuario;
        this.descricao = descricao;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
