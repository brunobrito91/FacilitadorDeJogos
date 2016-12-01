package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.R;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

/**
 * Created by BrunoBrito91 on 29/11/2016.
 */
public class CaraCoroa implements Sorteio {

    List<Number> faces = new ArrayList<>();

    public CaraCoroa() {
        //adiciona a lista de imagens as opções[cara, coroa]
        faces.add(R.drawable.cara);
        faces.add(R.drawable.coroa);
    }

    @Override
    public int getImagemResource() {
        //reordena a lista de imagens
        Collections.shuffle(faces);
        //recupera uma imagem aleatoria
        return (int) faces.get(0);
    }
}
