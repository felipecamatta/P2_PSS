package br.ufes.p2.model;

public class Privilegio {

    private Long idUsuario;
    private boolean visualizarTodas;
    private boolean excluirTodas;
    private boolean compartilharTodas;

    public Privilegio() {
    }

    public Privilegio(Long idUsuario, boolean visualizarTodas, boolean excluirTodas, boolean compartilharTodas) {
        this.idUsuario = idUsuario;
        this.visualizarTodas = visualizarTodas;
        this.excluirTodas = excluirTodas;
        this.compartilharTodas = compartilharTodas;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isVisualizarTodas() {
        return visualizarTodas;
    }

    public void setVisualizarTodas(boolean visualizarTodas) {
        this.visualizarTodas = visualizarTodas;
    }

    public boolean isExcluirTodas() {
        return excluirTodas;
    }

    public void setExcluirTodas(boolean excluirTodas) {
        this.excluirTodas = excluirTodas;
    }

    public boolean isCompartilharTodas() {
        return compartilharTodas;
    }

    public void setCompartilharTodas(boolean compartilharTodas) {
        this.compartilharTodas = compartilharTodas;
    }

}
