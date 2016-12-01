package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

/**
 * Created by BrunoBrito91 on 26/11/2016.
 */
public interface Tempo {

    public long getTempoInicial();

    public void setTempoInicial(long tempo);

    void start();

    void pause();

    void stop();

    void inicializa(long i);
}
