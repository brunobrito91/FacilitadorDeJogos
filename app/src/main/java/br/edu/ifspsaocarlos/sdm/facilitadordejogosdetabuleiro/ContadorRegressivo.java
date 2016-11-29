package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Chronometer;

/**
 * Created by BrunoBrito91 on 27/11/2016.
 */

public class ContadorRegressivo extends CountDownTimer {

    private Chronometer chronometer;
    private Context context;

    public ContadorRegressivo(Context context, Chronometer chronometer, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.chronometer = chronometer;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        chronometer.setText(android.text.format.DateFormat.format("mm:ss", millisUntilFinished));
    }

    @Override
    public void onFinish() {
        chronometer.setText(android.text.format.DateFormat.format("mm:ss", 0));
    }
}
