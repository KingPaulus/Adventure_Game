public class Level {


    private int playerXpose = GameMode.player.getX();
    private int playerYpose = GameMode.player.getY();
    private int anzahlVonGegner = 4;
    private int Levelgegner = 20;
    private int[] [] gegnerPose = new int[this.anzahlVonGegner] [2];
    //[] Anzahl von gegner , [2] poseX/Y von gegner
    private int [] [] gegnerWerte = new int[anzahlVonGegner] [5];


    public void setgegnerWerte(int gegnerNR,int Wert, int NeuerWert) {
        gegnerWerte[gegnerNR][Wert] = NeuerWert;
    }
    public int getgegnerWerte(int gegnerNR,int Wert) {
        return gegnerWerte[gegnerNR][Wert];
    }
    public void setGegnerAnzahl(int anzahl) {
        this.anzahlVonGegner = anzahl;
    }
    public void setGegnerLevel(int anzahl) {
        this.Levelgegner = anzahl;
    }
    public int getGegnerPose(int gegnerNr,int xy) {
        return gegnerPose[gegnerNr][xy];
    }
    public void setGegnerPose(int gegnerNr,int xy, int neu) {
        gegnerPose[gegnerNr][xy] = neu;
    }
    public void genGegnerPose(int losche1, int losche2, int gegnerNR) {
        losche1 = 0;
        losche2 = 0;

        int rndm1 = (int)(Math.random()*23);
        if(rndm1 == 0) {
            rndm1 = 1;
        }

        int rndm2 = (int)(Math.random()*14);
        //Sicherung
        if(rndm2 == 0) {
            rndm2 = 1;
        }
//		System.out.println("spawngegner(" + rndm1 + ", " + rndm2 + ", " + gegnerNR + ");");
        spawngegner(rndm1, rndm2,gegnerNR);
    }

    public void spawngegner(int r1, int r2,int gegnerNR) {
//		 Nicht im Spieler
        System.out.println("Berechne gegner " + "( " + r1 + ", " + r2 + ", " + gegnerNR + " )");
        if(r1 == playerXpose/20 && r2 == playerYpose/20) {
            System.out.println("Gegner im Spieler / Berechne Neu");
            genGegnerPose(r1,r2,gegnerNR);
        }
//		System.out.println("Nicht Im spieler " + gegnerNR);
        //Nicht Im Shop
        if(r1 == GameMode.shop.getShopPose(0)/20 && r2 == GameMode.shop.getShopPose(1)/20) {
            System.out.println("Gegner im Shop / Berechne Neu");
            genGegnerPose(r1,r2,gegnerNR);
        }
//		System.out.println("Nicht Im shop " + gegnerNR);
        // Y+1										// X +1											// Y - 1										// X - 1
        if(r1 == playerXpose/20 && r2 == (playerYpose/20+1) || r1 == (playerXpose/20+1) && r2 == playerYpose/20 || r1 == playerXpose/20 && r2 == (playerYpose/20-1) || r1 == (playerXpose/20-1) && r2 == playerYpose/20) {
            System.out.println("Gegner Neben Spieler / Berechne Neu");
            genGegnerPose(r1,r2,gegnerNR);
        }
//		System.out.println("Nicht NebenSpieler " + gegnerNR);

        for(int zzu = 1;zzu<gegnerPose.length;zzu++) {
            if(r1 == gegnerPose[zzu-1][0] && r2 == gegnerPose[zzu-1][1]) {
                genGegnerPose(r1,r2,gegnerNR);
            }
            if(r1 == (gegnerPose[zzu-1][0]/20)+1 && r2 == (gegnerPose[zzu-1][1]/20) || r1 == (gegnerPose[zzu-1][0]/GameMode.PlayerGrosse) && r2 == (gegnerPose[zzu-1][1]/GameMode.PlayerGrosse)+1 || r1 == (gegnerPose[zzu-1][0]/GameMode.PlayerGrosse)-1 && r2 == (gegnerPose[zzu-1][1]/GameMode.PlayerGrosse) || r1 == (gegnerPose[zzu-1][0]/GameMode.PlayerGrosse) && r2 == (gegnerPose[zzu-1][1]/GameMode.PlayerGrosse)-1) {
                System.out.println("Gegner neben Gegner / Berechne Neu");
                genGegnerPose(r1,r2,gegnerNR);
            }
        }
//		System.out.println("r1 = " + r1 + " r2 = " + r2);
        gegnerPose[gegnerNR] [0] = r1*20;
        gegnerPose[gegnerNR] [1] = r2*20;
//		gegnerWerte[] []
//		for(int ztz = 0;ztz<gegnerWerte[gegnerNR].length;ztz++) {
//		for(int ztz = 0;ztz<GameMode.L1.getGegnerLevel().lenght;ztz++) {
        for(int ztz = 0;ztz<5;ztz++) {
//			MaximumWeaponLevel
            int iiio = (int)(Math.random()*20+Levelgegner);
            // TODO
            if(Levelgegner >= 100-20) {
                GameMode.shop.setMaximumWeaponLevel(200);
                GameMode.shop.setShopLevel(2);
            }
            if(Levelgegner >= 200-20) {
                GameMode.shop.setMaximumWeaponLevel(300);
                GameMode.shop.setShopLevel(3);
            }
            if(Levelgegner >= 300-20) {
                GameMode.shop.setMaximumWeaponLevel(400);
                GameMode.shop.setShopLevel(4);
            }
            gegnerWerte[gegnerNR] [ztz] = iiio;
        }
        gegnerWerte[gegnerNR] [3] = (int)(Math.random()*20+Levelgegner);
    }

    public int getGegnerAnzahl() {
        return this.anzahlVonGegner;
    }
    public int getGegnerLevel() {
        return this.Levelgegner;
    }
}
