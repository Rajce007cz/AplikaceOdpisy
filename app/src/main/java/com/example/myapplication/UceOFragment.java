package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.odpisy_aplikace.R;


public class UceOFragment extends Fragment {
    private Integer normalRok2 = 0 ;
    private Integer ucetRok2 = 0 ;
    private Button btn_Vypocet2;
    private Button btnHistorie2;
    private Button btnReset2;
    private TextView vc2;
    private TextView celmes;
    private TextView mes;
    private TextView ntext;
    private SeznamOdpisu seznamOdpisu;
    private Historie seznamHistorie;
    private double mesCena = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_uce_o, container, false);
            seznamOdpisu = new SeznamOdpisu();
            seznamHistorie = new Historie();
            btn_Vypocet2 = view.findViewById(R.id.btn_vypocet);
            btnReset2 = view.findViewById(R.id.btnReset2);
            btnHistorie2 = view.findViewById(R.id.btnHistorie2);
            ntext = view.findViewById(R.id.ntext);
            vc2 = view.findViewById(R.id.vc2);
            mes = view.findViewById(R.id.mes);
            celmes = view.findViewById(R.id.celmes);
            btn_Vypocet2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{String VCString = vc2.getText().toString();
                    double VC = Double.parseDouble(VCString);
                    if(VC < 80000.0){
                        ntext.setText("Malá vstupní cena!!!");
                    }else{
                    normalRok2 =0;
                    String mesString = mes.getText().toString();
                    double mes = Double.parseDouble(mesString);

                    String Stcelmes = celmes.getText().toString();
                    double celemes= Double.parseDouble(Stcelmes);
                    double ZC = VC;
                    double opravky = 0.0;
                    double odpis = VC/celemes * mes;
                    mesCena = VC/celemes;
                    opravky += odpis;
                    ZC -= odpis;
                    seznamOdpisu.pridatOdpis(new Odpis(normalRok2, odpis, opravky, ZC, VC));
                    seznamHistorie.pridatOdpisy(new Odpis(normalRok2, odpis, opravky, ZC, VC));
                    normalRok2++;
                    zobrazOdpis();
                    }}catch (Exception e){
                        ntext.setText("Chybné hodnoty!!!");
                }
                }
            });
            btnReset2.setOnClickListener(e -> {
                vc2.setText("");
                mes.setText("");
                celmes.setText("");
                normalRok2 = 0;
                ntext.setText("");
            seznamOdpisu.seznamOdpisu.clear();
        });
        btnHistorie2.setOnClickListener(e -> zobrazHistorii2());
        return view;
    }
    public void zobrazOdpis () {
        Odpis odpis = seznamOdpisu.seznamOdpisu.get(seznamOdpisu.seznamOdpisu.size() - 1);
        ntext.append("Odpis/Měsíční: " + odpis.getOdpis() +"/" + mesCena + " ZC: " + odpis.getZC() + " VC: " + odpis.getVC() +" MD:551 D:081-089"+ "\n");

    }
    public void zobrazHistorii2(){
        try {
            ntext.setText("");
            Odpis odpis = seznamHistorie.seznamHistorie.get(seznamHistorie.seznamHistorie.size() - 1);
            ntext.append("Odpis/Měsíční: " + odpis.getOdpis() + "/" + mesCena + " ZC: " + odpis.getZC() + " VC: " + odpis.getVC() + " MD:551 D:081-089" + "\n");
        }catch (Exception e){
            ntext.setText("Žádné odpisy!!!");
        }
    }
}