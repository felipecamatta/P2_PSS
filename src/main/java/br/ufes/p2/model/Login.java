package br.ufes.p2.model;

public class Login {

    private Long id;
    private String usuario;
    private String senha;
    private Usuario usuarioObj;

    public Login() {
    }

    public Login(Long id, String usuario, String senha, Usuario usuarioObj) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.usuarioObj = usuarioObj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioObj() {
        return usuarioObj;
    }

    public void setUsuarioObj(Usuario usuarioObj) {
        this.usuarioObj = usuarioObj;
    }

}
