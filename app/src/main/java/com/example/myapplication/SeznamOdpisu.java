package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class SeznamOdpisu {
    List<Odpis> seznamOdpisu = new ArrayList<>();
    public void pridatOdpis(Odpis odpis){
        seznamOdpisu.add(odpis);
    }
}
