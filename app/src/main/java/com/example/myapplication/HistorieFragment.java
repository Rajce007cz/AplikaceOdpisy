package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.odpisy_aplikace.R;


public class HistorieFragment extends Fragment {
    private TextView txtHistorie;
    private Historie seznamHistorie;
    private SeznamOdpisu seznamOdpisu;
    private Odpis odpis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historie, container, false);
        try{
        txtHistorie = view.findViewById(R.id.txtHistorie);
        txtHistorie.setText("");
        Odpis odpis = seznamHistorie.seznamHistorie.get(seznamHistorie.seznamHistorie.size() - 1);
        for(Odpis odpis1: seznamHistorie.seznamHistorie){
            txtHistorie.append("Rok: " + odpis1.getRok() + " Odpis: " + odpis1.getOdpis() + " Opravky: " + odpis1.getOpravky() + " ZC: " + odpis1.getZC() + " VC: " + odpis1.getVC() + "\n");
        }}
        catch (Exception e){
            txtHistorie.setText("Nemáš žádný odpis");
        }
        return view;
    }
}