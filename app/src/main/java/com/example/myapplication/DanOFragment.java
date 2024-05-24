package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.odpisy_aplikace.R;


public class DanOFragment extends Fragment {
    private Integer normalRok = 0 ;
    private Integer ucetRok = 0 ;
    private Button btnVypocet;
    private Button btnReset;
    private TextView vc;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rbRovno, rbZrych;
    private RadioGroup radioGroup, radioGroup2;
    private SeznamOdpisu seznamOdpisu;
    private Historie seznamHistorie;
    private Button btnHistorie;
    private TextView nviewText;
    private Double sazba = 0.0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dan_o, container, false);
        seznamOdpisu = new SeznamOdpisu();
        seznamHistorie = new Historie();
        btnVypocet = view.findViewById(R.id.btnVypocet);
        btnReset = view.findViewById(R.id.btnReset);
        btnHistorie = view.findViewById(R.id.btnHistorie);
        nviewText = view.findViewById(R.id.nview_text);
        vc = view.findViewById(R.id.vc);
        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        rb4 = view.findViewById(R.id.rb4);
        rb5 = view.findViewById(R.id.rb5);
        rb6 = view.findViewById(R.id.rb6);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup2 = view.findViewById(R.id.radioGroup2);
        rbRovno = view.findViewById(R.id.rbRovno);
        rbZrych = view.findViewById(R.id.rbZrych);
        btnVypocet.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vypocet();
            }
        });
        btnReset.setOnClickListener(e -> {
            vc.setText("");
            normalRok = 0;
            ucetRok = 0;
            radioGroup.clearCheck();
            radioGroup2.clearCheck();
            nviewText.setText("");
            seznamOdpisu.seznamOdpisu.clear();
        });
        btnHistorie.setOnClickListener(e -> zobrazHistorii());

        return view;
    }
    public void initSazba () {
        if (rbRovno.isChecked()) {
            if (normalRok == 0) {
                if (rb1.isChecked()) {
                    sazba = 20.0;
                }
                if (rb2.isChecked()) {
                    sazba = 11.0;
                }
                if (rb3.isChecked()) {
                    sazba = 5.5;
                }
                if (rb4.isChecked()) {
                    sazba = 2.15;
                }
                if (rb5.isChecked()) {
                    sazba = 1.4;
                }
                if (rb6.isChecked()) {
                    sazba = 1.02;
                }
            } else {
                if (rb1.isChecked()) {
                    sazba = 40.0;
                }
                if (rb2.isChecked()) {
                    sazba = 22.25;
                }
                if (rb3.isChecked()) {
                    sazba = 10.5;
                }
                if (rb4.isChecked()) {
                    sazba = 5.15;
                }
                if (rb5.isChecked()) {
                    sazba = 3.4;
                }
                if (rb6.isChecked()) {
                    sazba = 2.02;
                }
            }
        }
        if (rbZrych.isChecked()) {
            if (normalRok == 0) {
                if (rb1.isChecked()) {
                    sazba = 3.0;
                }
                if (rb2.isChecked()) {
                    sazba = 5.0;
                }
                if (rb3.isChecked()) {
                    sazba = 10.0;
                }
                if (rb4.isChecked()) {
                    sazba = 20.0;
                }
                if (rb5.isChecked()) {
                    sazba = 30.0;
                }
                if (rb6.isChecked()) {
                    sazba = 50.0;
                }
            } else {
                if (rb1.isChecked()) {
                    sazba = 4.0;
                }
                if (rb2.isChecked()) {
                    sazba = 6.0;
                }
                if (rb3.isChecked()) {
                    sazba = 11.0;
                }
                if (rb4.isChecked()) {
                    sazba = 21.0;
                }
                if (rb5.isChecked()) {
                    sazba = 31.0;
                }
                if (rb6.isChecked()) {
                    sazba = 51.0;
                }
            }
        }
    }
    public void vypocet() {
        normalRok = 0;
        ucetRok = 0;
        nviewText.setText("");
        try{String VCString = vc.getText().toString();
        double VC = Double.parseDouble(VCString);
        if(VC < 80000.0){
            nviewText.setText("Malá vstupní cena!!!");
        }else{
            double ZC = VC;
            double opravky = 0.0;
            if (rbRovno.isChecked()) {
                while (ZC > 0) {
                    initSazba();
                    double odpis = VC * (sazba / 100);
                    opravky += odpis;
                    ZC -= odpis;
                    normalRok++;
                    ucetRok++;
                    seznamOdpisu.pridatOdpis(new Odpis(normalRok, odpis, opravky, ZC, VC));
                    seznamHistorie.pridatOdpisy(new Odpis(normalRok, odpis, opravky, ZC, VC));
                    zobrazOdpis();
                }
            }
            if (rbZrych.isChecked()) {
                while (ZC > 0) {
                    initSazba();
                    double odpis = 0;
                    if (normalRok == 0) {
                        odpis = VC / sazba;
                        ucetRok++;
                    }
                    if (normalRok > 0) {
                        odpis = 2 * ZC / (sazba - ucetRok);
                        ucetRok++;
                    }
                    opravky += odpis;
                    ZC -= odpis;
                    seznamOdpisu.pridatOdpis(new Odpis(normalRok, odpis, opravky, ZC, VC));
                    seznamHistorie.pridatOdpisy(new Odpis(normalRok, odpis, opravky, ZC, VC));
                    normalRok++;
                    zobrazOdpis();
                }
            }
        }

    }catch (Exception e){
            nviewText.setText("Chybné hodnoty!!!");}}
    public void zobrazOdpis () {
        Odpis odpis = seznamOdpisu.seznamOdpisu.get(seznamOdpisu.seznamOdpisu.size() - 1);
        nviewText.append("Rok: " + odpis.getRok() + " Odpis: " + odpis.getOdpis() + " Opravky: " + odpis.getOpravky() + " ZC: " + odpis.getZC() + " VC: " + odpis.getVC() + "\n");

    }
    public void zobrazHistorii(){
        try{nviewText.setText("");
        Odpis odpis = seznamHistorie.seznamHistorie.get(seznamHistorie.seznamHistorie.size() - 1);
        for(Odpis odpis1: seznamHistorie.seznamHistorie){
            nviewText.append("Rok: " + odpis1.getRok() + " Odpis: " + odpis1.getOdpis() + " Opravky: " + odpis1.getOpravky() + " ZC: " + odpis1.getZC() + " VC: " + odpis1.getVC() + "\n");
        }
    }catch (Exception e){
        nviewText.setText("Žádné odpisy!!!");
    }
    }
}