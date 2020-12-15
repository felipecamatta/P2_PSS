package br.ufes.p2.model;

public class Solicitacao {

    private Long id;
    private Imagem imagem;
    private Usuario usuario;
    private String descricao;
    private boolean aceita;

    public Solicitacao() {
    }

    public Solicitacao(Imagem imagem, Usuario usuario, String descricao) {
        this.imagem = imagem;
        this.usuario = usuario;
        this.descricao = descricao;
    }

    public Solicitacao(Imagem imagem, Usuario usuario, String descricao, boolean aceita) {
        this.imagem = imagem;
        this.usuario = usuario;
        this.descricao = descricao;
        this.aceita = aceita;
    }

    public Solicitacao(Long id, Imagem imagem, Usuario usuario, String descricao, boolean aceita) {
        this.id = id;
        this.imagem = imagem;
        this.usuario = usuario;
        this.descricao = descricao;
        this.aceita = aceita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAceita() {
        return aceita;
    }

    public void setAceita(boolean aceita) {
        this.aceita = aceita;
    }

}
