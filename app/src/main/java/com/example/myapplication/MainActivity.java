package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Integer normalRok = 0 ;
    private Integer ucetRok = 0 ;
    private Button btnVypocet; // Assuming these are buttons
    private Button btnReset;
    private TextView vc; // Assuming these are TextViews
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rbRovno, rbZrych; // Assuming these are RadioButtons
    private RadioGroup radioGroup, radioGroup2;
    private SeznamOdpisu seznamOdpisu;
    private TextView nviewText;
    private Double sazba = 0.0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

            // Initialize your variables here
            btnVypocet = findViewById(R.id.btnSpocitat); // Replace with actual id
            btnReset = findViewById(R.id.btnReset); // Replace with actual id
            nviewText = findViewById(R.id.nview_text);
            vc = findViewById(R.id.vc); // Replace with actual id
            rb1 = findViewById(R.id.rb1); // Replace with actual id
            rb2 = findViewById(R.id.rb2); // Replace with actual id
            rb3 = findViewById(R.id.rb3); // Replace with actual id
            rb4 = findViewById(R.id.rb4); // Replace with actual id
            rb5 = findViewById(R.id.rb5); // Replace with actual id
            rb6 = findViewById(R.id.rb6); // Replace with actual id
            radioGroup = findViewById(R.id.radioGroup);
            radioGroup2 = findViewById(R.id.radioGroup2);
            rbRovno = findViewById(R.id.rbRovno); // Replace with actual id
            rbZrych = findViewById(R.id.rbZrych); // Replace with actual id
            SeznamOdpisu seznamOdpisu = new SeznamOdpisu();
            btnVypocet.setOnClickListener(e -> zobraz());
            btnReset.setOnClickListener(e -> {
                vc.setText("");
                normalRok = 0;
                ucetRok = 0;
                radioGroup.clearCheck();
                radioGroup2.clearCheck();
                nviewText.setText("");
                seznamOdpisu.seznamOdpisu.clear();
            });
    }
        public void initSazba () {
            if (rbRovno.isSelected()) {
                if (normalRok == 0) {
                    if (rb1.isSelected()) {
                        sazba = 20.0;
                    }
                    if (rb2.isSelected()) {
                        sazba = 11.0;
                    }
                    if (rb3.isSelected()) {
                        sazba = 5.5;
                    }
                    if (rb4.isSelected()) {
                        sazba = 2.15;
                    }
                    if (rb5.isSelected()) {
                        sazba = 1.4;
                    }
                    if (rb6.isSelected()) {
                        sazba = 1.02;
                    }
                } else {
                    if (rb1.isSelected()) {
                        sazba = 40.0;
                    }
                    if (rb2.isSelected()) {
                        sazba = 22.25;
                    }
                    if (rb3.isSelected()) {
                        sazba = 10.5;
                    }
                    if (rb4.isSelected()) {
                        sazba = 5.15;
                    }
                    if (rb5.isSelected()) {
                        sazba = 3.4;
                    }
                    if (rb6.isSelected()) {
                        sazba = 2.02;
                    }
                }
            }
            if (rbZrych.isSelected()) {
                if (normalRok == 0) {
                    if (rb1.isSelected()) {
                        sazba = 3.0;
                    }
                    if (rb2.isSelected()) {
                        sazba = 5.0;
                    }
                    if (rb3.isSelected()) {
                        sazba = 10.0;
                    }
                    if (rb4.isSelected()) {
                        sazba = 20.0;
                    }
                    if (rb5.isSelected()) {
                        sazba = 30.0;
                    }
                    if (rb6.isSelected()) {
                        sazba = 50.0;
                    }
                } else {
                    if (rb1.isSelected()) {
                        sazba = 4.0;
                    }
                    if (rb2.isSelected()) {
                        sazba = 6.0;
                    }
                    if (rb3.isSelected()) {
                        sazba = 11.0;
                    }
                    if (rb4.isSelected()) {
                        sazba = 21.0;
                    }
                    if (rb5.isSelected()) {
                        sazba = 31.0;
                    }
                    if (rb6.isSelected()) {
                        sazba = 51.0;
                    }
                }
            }
        }
        public void vypocet () {
            double VC = Double.parseDouble((String)vc.getText());
            double ZC = VC;
            double opravky = 0;
            if (rbRovno.isSelected()) {
                while (ZC > 0) {
                    initSazba();
                    double odpis = Double.parseDouble((String)vc.getText()) * (sazba / 100);
                    opravky += odpis;
                    ZC -= odpis;
                    seznamOdpisu.pridatOdpis(new Odpis(normalRok, odpis, opravky, ZC, Double.parseDouble((String) vc.getText())));
                    normalRok++;
                    ucetRok++;
                    zobrazOdpis();
                }
            }
            if (rbZrych.isSelected()) {
                while (ZC > 0) {
                    initSazba();
                    double odpis = 0;
                    if (normalRok == 0) {
                        odpis = Double.parseDouble((String) vc.getText()) / sazba;
                        ucetRok++;
                    }
                    if (normalRok > 0) {
                        odpis = 2 * ZC / (sazba - ucetRok);
                        ucetRok++;
                    }
                    opravky += odpis;
                    ZC -= odpis;
                    seznamOdpisu.pridatOdpis(new Odpis(normalRok, odpis, opravky, ZC, Double.parseDouble((String) vc.getText())));
                    normalRok++;
                    zobrazOdpis();
                }
            }
        }
    public void zobrazOdpis () {
        for (Odpis odpis : seznamOdpisu.seznamOdpisu) {
            nviewText.append("Rok: " + odpis.getRok() + " Odpis: " + odpis.getOdpis() + " Opravky: " + odpis.getOpravky() + " ZC: " + odpis.getZC() + " VC: " + odpis.getVC() + "\n");
        }
    }
    public void zobraz(){
        nviewText.append("Ro");
    }
    }
