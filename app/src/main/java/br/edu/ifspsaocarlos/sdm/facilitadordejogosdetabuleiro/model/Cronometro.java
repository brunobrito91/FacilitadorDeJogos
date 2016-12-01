package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * Created by BrunoBrito91 on 26/11/2016.
 */

public class Cronometro implements Tempo {

    private long tempo;
    private Chronometer chronometer;
    private long milliseconds = 0;

    public Cronometro(Chronometer chronometer) {
        this.chronometer = chronometer;
        inicializa(SystemClock.elapsedRealtime());
    }

    @Override
    public long getTempoInicial() {
        return tempo;
    }

    @Override
    public void setTempoInicial(long tempo) {
        this.tempo = tempo;
    }

    @Override
    public void start() {
        //seta o cronometro com o tempo em que foi pausado, ou com zero quando o cronometro ainda não foi iniciado
        chronometer.setBase(SystemClock.elapsedRealtime() - milliseconds);
        //inicia a contagem
        chronometer.start();
    }

    @Override
    public void pause() {
        //guarda o tempo que passou entre o start e o pause
        milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        //pausa a contagem
        chronometer.stop();
    }

    @Override
    public void stop() {
        //para a contagem
        milliseconds = 0;
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
    }

    @Override
    public void inicializa(long tempoInicial) {
        setTempoInicial(tempoInicial);
    }

}
