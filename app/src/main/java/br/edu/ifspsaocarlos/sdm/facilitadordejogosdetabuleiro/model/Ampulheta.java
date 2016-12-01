package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import android.text.format.DateFormat;
import android.widget.Chronometer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.ContadorRegressivo;

/**
 * Created by BrunoBrito91 on 27/11/2016.
 */
public class Ampulheta implements Tempo {

    private static final long INTERVAL = 100;
    private Chronometer chronometer;
    private ContadorRegressivo contadorRegressivo;
    private long tempo;

    public Ampulheta(Chronometer chronometer) {
        this.chronometer = chronometer;
        inicializa(0);
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
        contadorRegressivo.start();
    }

    @Override
    public void pause() {
        //cancela a contagem
        contadorRegressivo.cancel();
        //recupera o tempo em que foi pausado e seta o contadorRegressivo com esse tempo
        long t = 1;
        SimpleDateFormat sf = new SimpleDateFormat("mm:ss");
        try {
            t = sf.parse(chronometer.getText().toString()).getTime() - 10800000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contadorRegressivo = new ContadorRegressivo(chronometer, t, INTERVAL);
    }

    @Override
    public void stop() {
        contadorRegressivo.cancel();
        inicializa(tempo);
    }

    @Override
    public void inicializa(long tempoInicial) {
        //seta o tempo inicial com o tempo passado
        setTempoInicial(tempoInicial);
        //seta o contadorRegressivo com o tempo passado
        contadorRegressivo = new ContadorRegressivo(chronometer, tempoInicial, INTERVAL);
        //altera o texto para o tempo passado
        chronometer.setText(DateFormat.format("mm:ss", tempoInicial));
    }
}
