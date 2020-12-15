package br.ufes.p2.model;

public class Notificacao {

    private Long id;
    private String mensagem;
    private boolean lida;
    private Imagem imagemReferencia;
    private Usuario usuarioDestino;

    public Notificacao() {
    }

    public Notificacao(Long id, String mensagem, boolean lida, Imagem imagemReferencia, Usuario usuarioDestino) {
        this.id = id;
        this.mensagem = mensagem;
        this.lida = lida;
        this.imagemReferencia = imagemReferencia;
        this.usuarioDestino = usuarioDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Imagem getImagemReferencia() {
        return imagemReferencia;
    }

    public void setImagemReferencia(Imagem imagemReferencia) {
        this.imagemReferencia = imagemReferencia;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

}
