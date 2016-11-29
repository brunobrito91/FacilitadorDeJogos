package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import android.os.SystemClock;

import java.io.Serializable;

/**
 * Created by BrunoBrito91 on 27/11/2016.
 */
public class Ampulheta implements Tempo, Serializable {

    private long tempo;

    public Ampulheta() {
        setTempoInicial(0);
    }

    @Override
    public long getTempoInicial() {
        return tempo;
    }

    @Override
    public void setTempoInicial(long tempo) {
        this.tempo = tempo;
    }
}
