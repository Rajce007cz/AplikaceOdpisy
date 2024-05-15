package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Historie {
    List<Odpis> seznamHistorie = new ArrayList<>();
    public void pridatOdpisy(Odpis odpis){
        seznamHistorie.add(odpis);
    }
}
