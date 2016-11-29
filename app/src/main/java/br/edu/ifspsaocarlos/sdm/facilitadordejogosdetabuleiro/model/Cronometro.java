package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import android.os.SystemClock;

/**
 * Created by BrunoBrito91 on 26/11/2016.
 */

public class Cronometro implements Tempo {

    private long tempo;

    public Cronometro() {
        setTempoInicial(SystemClock.elapsedRealtime());
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
