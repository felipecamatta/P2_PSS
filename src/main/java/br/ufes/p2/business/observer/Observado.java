package br.ufes.p2.business.observer;

import java.util.ArrayList;
import java.util.List;

public class Observado {

    private List<Observador> observadores;

    public Observado() {
        this.observadores = new ArrayList<>();
    }

    public void addObservador(Observador o) {
        this.observadores.add(o);
    }

    public void removerObservador(Observador o) {
        this.observadores.remove(o);
    }

    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.update();
        }
    }

}
