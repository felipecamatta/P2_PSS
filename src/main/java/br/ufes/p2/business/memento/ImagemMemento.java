package br.ufes.p2.business.memento;

public class ImagemMemento {

    private Long id;
    private String nome;
    private String diretorio;
    private boolean excluida;

    public ImagemMemento(Long id, String nome, String diretorio, boolean excluida) {
        this.id = id;
        this.nome = nome;
        this.diretorio = diretorio;
        this.excluida = excluida;
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

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public boolean isExcluida() {
        return excluida;
    }

    public void setExcluida(boolean excluida) {
        this.excluida = excluida;
    }

}
