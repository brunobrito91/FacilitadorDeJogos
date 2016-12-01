package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.StringPrepParseException;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * Created by BrunoBrito91 on 27/11/2016.
 */

//Classe personalizada de CountDownTimer
public class ContadorRegressivo extends CountDownTimer {

    private Chronometer chronometer;

    //recebe Chronometer para que o text seja alterado, o tempo inicial e o intervalo do on tick
    public ContadorRegressivo(Chronometer chronometer, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.chronometer = chronometer;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //seta o texto do Chronometer com o tempo restante até zerar a cada ontick
        chronometer.setText(android.text.format.DateFormat.format("mm:ss", millisUntilFinished));
    }

    @Override
    public void onFinish() {
        //seta o texto do Chronometer com o tempo 0 ao término da contagem
        chronometer.setText(android.text.format.DateFormat.format("mm:ss", 0));
    }
}
