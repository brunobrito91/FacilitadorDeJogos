package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

/**
 * Created by BrunoBrito91 on 29/11/2016.
 */
public class CaraCoroa implements Sorteio {

    List<Number> faces = new ArrayList<>();

    public CaraCoroa() {
        faces.add(R.drawable.cara);
        faces.add(R.drawable.coroa);
    }

    @Override
    public int getImagemResource() {
        Collections.shuffle(faces);
        return (int) faces.get(0);
    }
}
