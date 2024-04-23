package com.example.myapplication;

public class Odpis {
    public Odpis(int rok, double odpis, double opravky, double ZC, double VC) {
        this.rok = rok;
        this.odpis = odpis;
        this.opravky = opravky;
        this.ZC = ZC;
        this.VC = VC;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public double getOdpis() {
        return odpis;
    }

    public void setOdpis(double odpis) {
        this.odpis = odpis;
    }

    public double getOpravky() {
        return opravky;
    }

    public void setOpravky(double opravky) {
        this.opravky = opravky;
    }

    public double getZC() {
        return ZC;
    }

    public void setZC(double ZC) {
        this.ZC = ZC;
    }

    public double getVC() {
        return VC;
    }

    public void setVC(double VC) {
        this.VC = VC;
    }

    private int rok;
    private double odpis;
    private double opravky;
    private double ZC;
    private double VC;
}
