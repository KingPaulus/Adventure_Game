
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMode extends JPanel implements MouseListener, KeyListener {

    public int BreiteX = Main.BreiteMain;
    public int HoheY = Main.HoheMain;
    public boolean StartBoolean = true;

    public static Spieler player = new Spieler();
    public static Level L1 = new Level();
    public static Shop shop = new Shop();


    //	static int anzahlVonGegner = 4;
//	static int Levelgegner = 20;
    boolean spawnGegner = false;
    boolean [] Move = new boolean[4];
    int Schaden1 = 0;
    int Schaden2 = 0;
    int SpielerIcon = 1;
    // 1Tod		2BogenSchuetze		3Zauberer

    String GameNameNormal = Main.GameName;
    boolean[] WindowStart = new boolean[4];
    // Start Screen Name
    // Charakter Start Screen
    // Game Screen
    // Game Over Screen
    boolean imShop = false;
    int anzahl_von_waffen = 5;
    boolean []imKampf = new boolean[L1.getGegnerAnzahl()];
    boolean Spieler_vs_Ki = false;
    int Restgegener = L1.getGegnerAnzahl();
    boolean GegnerZug = false;
    // Schaden = dmg
    // verteidigung = def
    // Leben = hp
    // genauigkeit = acc
    // ausweichen = eva dge
    int waffe;
    //Waffe 	1Schwert		2Axt		3Dreizack
    String[] WaffenName = new String[anzahl_von_waffen];

    boolean RestartWindow = false;
    static int PlayerGrosse = 20;
    //Player \/
//	static int xPose = 20, player.getY()= 20;
    int Herzen;
    String GeldString = String.valueOf(player.getGeld());
    boolean GameOver = false;
    int Kampfgegen;
    
    Image image1 = null;
    Image image2 = null;
    Image image2_2 = null;
    Image image3 = null;
    Image image3_2 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    Image image8 = null;
    Image image9 = null;
    Image image10 = null;
    Image image11 = null;
    Image image12 = null;
    Image image13 = null;
    Image image14 = null;
    Image image15 = null;
    Image image16 = null;
    Image image17 = null;
    Image image18 = null;
    Image image19 = null;
    Image image20 = null;
    Image image21 = null;
    Image image22 = null;
    Image image23 = null;
    Image image24 = null;
    Image image25 = null;
    Image image26 = null;
    ImageIcon imagePic27;
    Image image27 = null;

    // TODO

    // Schaden = dmg
    // verteidigung = def
    // Leben = hp
    // genauigkeit = acc
    // ausweichen = eva dge

    public JFrame frame;

    public GameMode() {
//		GameMode GM = new GameMode();
        frame = new JFrame("" + Main.GameName);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Main.BreiteMain,Main.HoheMain);
        frame.setVisible(true);
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        player.setX(20);
        player.setY(20);
        player.setHp(10);
        shop.spawnShop();
//		L1.setGegnerLevel(20);

        spawnGegner = true;
        StartBoolean= false;
        WindowStart[0] = true;
        WindowStart[1] = false;
        WindowStart[2] = false;
        WindowStart[3] = false;
        WaffenName[0] = "Dmg";
        WaffenName[1] = "Def";
        WaffenName[2] = "hp";
        WaffenName[3] = "acc";
        WaffenName[4] = "dge";
        this.setImage();
        repaint();
    }

    public void neuStart () {
        frame.setVisible(false);
        frame.dispose();
        player = new Spieler();
        L1 = new Level();
        shop = new Shop();
        System.out.println("Neu Start");
        GameMode gm = new GameMode();

    }


    public void shop() {
        repaint();
    }

    public void waffenUpdate (int waffenSlot ,int UpdateUm) {
        if(waffenSlot == 0) {
            player.setDmg(player.getDmg()+UpdateUm);
        } else if(waffenSlot == 1) {
            player.setDef(player.getDef()+UpdateUm);
        } else if(waffenSlot == 2) {
            player.setHp(player.getHp()+UpdateUm);
        } else if(waffenSlot == 3) {
            player.setAcc(player.getAcc()+UpdateUm);
        } else if(waffenSlot == 4) {
            player.setDge(player.getDge()+UpdateUm);
        }
//		Player[waffenSlot] = Player[waffenSlot] + UpdateUm;
    }

    public void kampf() {
        repaint();
    }

    public void angriff() {
        Schaden1 = 0;
        Schaden2 = 0;
//		System.out.println("angriff");
        // 0. Staerke
        // 1. Verteidigung
        // 2. Leben
        // 3. Genauigkeit
        // 4. ausweichen
//		gegnerWerte[gegenGegener1][];
//		Player[]


//		if(player.getAcc() > L1.getgegnerWerte(Kampfgegen,4)) {
        if(player.getAcc() > L1.getgegnerWerte(Kampfgegen,4)) {
            Schaden1 = player.getDmg() - L1.getgegnerWerte(Kampfgegen,1);
//			System.out.println("Schaden bei gegner = " + Schaden1);
            if(Schaden1 < 0) {
                Schaden1 = 0;
            }

            L1.setgegnerWerte(Kampfgegen, 2, L1.getgegnerWerte(Kampfgegen,2) - Schaden1);
        }
        if(L1.getgegnerWerte(Kampfgegen,2) <= 0) {
            // gegner TOD
            Schaden1 = 0;
            Schaden2 = 0;
            player.setGeld(player.getGeld()+10);
            L1.setGegnerPose(Kampfgegen,0, -20);
            L1.setGegnerPose(Kampfgegen,1, -20);
            Restgegener = Restgegener - 1;
//			System.out.println("Rest an gegner = " + Restgegener);
            flucht();
        }
        if(L1.getgegnerWerte(Kampfgegen,2) >= 0) {
//			GegnerZug = true;
            if(L1.getgegnerWerte(Kampfgegen,3) < player.getDge()) {
                GegnerZug = false;
            }
            GegnerSchlag();
        }
    }

    public void flucht() {
//		Kampfgegen
        int iuu = L1.getGegnerPose(Kampfgegen, 0);
        if(iuu != 0) {
//			System.out.println("flucht von " + Kampfgegen);
//			xPose = xPose - 20;
            player.setX(player.getX() + PlayerGrosse);
            player.TestCords();
            Spieler_vs_Ki = false;
            Schaden1 = 0;
            Schaden2 = 0;
            repaint();
//			System.out.println("Repaint();");
        } else if(iuu == 0) {
//			player.getX();
            player.setX(20);
            Spieler_vs_Ki = false;
            Schaden1 = 0;
            Schaden2 = 0;
            repaint();
        }
    }

    public void GegnerSchlag() {
        if(L1.getgegnerWerte(Kampfgegen,3) > player.getDge()) {
            Schaden2 = L1.getgegnerWerte(Kampfgegen,0) - player.getDef();
//			System.out.println("Schaden Bei dir = " + Schaden2);
            if(Schaden2 < 0) {
                Schaden2 = 0;
            }
            player.setHp(player.getHp() -Schaden2);
            GegnerZug = false;
        }
        repaint();
    }



    public void setImage() {
        System.out.println("Setting Images");
        try {
            image1 = ImageIO.read(new File("image/Karte.png"));
            image3 = ImageIO.read(new File("image/Gegner.png"));
            image5 = ImageIO.read(new File("image/hearts.png"));
            image8 = ImageIO.read(new File("image/Shop2.png"));
            image9 = ImageIO.read(new File("image/Shop3.png"));
            image10 = ImageIO.read(new File("image/ShopItem1.png"));
            image11 = ImageIO.read(new File("image/ShopItem2.png"));
            image12 = ImageIO.read(new File("image/ShopItem3.png"));
            image13 = ImageIO.read(new File("image/ShopItem4.png"));
            image14 = ImageIO.read(new File("image/ShopItem5.png"));
            image15 = ImageIO.read(new File("image/Karte2.png"));
            image16 = ImageIO.read(new File("image/Pfeil3.png"));
            image17 = ImageIO.read(new File("image/Pfeil4.png"));
            image18 = ImageIO.read(new File("image/Pfeil2.png"));
            image19 = ImageIO.read(new File("image/Pfeil1.png"));
            image20 = ImageIO.read(new File("image/Hintergrund1.png"));
            image21 = ImageIO.read(new File("image/SchriftRolle.png"));
            image23 = ImageIO.read(new File("image/KampfButton1.png"));
            image24 = ImageIO.read(new File("image/KampfButton2.png"));
            image25 = ImageIO.read(new File("image/Hintergrund3.png"));
            image26 = ImageIO.read(new File("image/half_hearts.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerImage(int player) {
        System.out.println("Setting Player Image: " + player);
        try {
            if(player == 1) {
                image2 = ImageIO.read(new File("image/Spieler1.png"));
                image2_2 = ImageIO.read(new File("image/Spieler1_2.png"));
                image22 = ImageIO.read(new File("image/Hintergrund2_1.png"));
            } else if(player == 2) {
                image2 = ImageIO.read(new File("image/Spieler2.png"));
                image2_2 = ImageIO.read(new File("image/Spieler2_2.png"));
                image22 = ImageIO.read(new File("image/Hintergrund2_2.png"));
            } else if(player == 3) {
                image2 = ImageIO.read(new File("image/Spieler3.png"));
                image2_2 = ImageIO.read(new File("image/Spieler3_2.png"));
                image22 = ImageIO.read(new File("image/Hintergrund2_3.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setShopImage() {
        System.out.println("Shop Level: " + shop.getShopLevel());
        try {
            if(shop.getShopLevel() == 1) {
                image3_2 = ImageIO.read(new File("image/Gegner1_1.png"));
                image4 = ImageIO.read(new File("image/ShopLevel1.png"));
            } else if(shop.getShopLevel() == 2) {
                image3_2 = ImageIO.read(new File("image/Gegner1_2.png"));
                image4 = ImageIO.read(new File("image/ShopLevel2.png"));
            } else if(shop.getShopLevel() == 3) {
                image3_2 = ImageIO.read(new File("image/Gegner1_3.png"));
                image4 = ImageIO.read(new File("image/ShopLevel3.png"));
            } else if(shop.getShopLevel() == 4) {
                image3_2 = ImageIO.read(new File("image/Gegner1_4.png"));
                image4 = ImageIO.read(new File("image/ShopLevel4.png"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFightBackgroundImage(String background) {
        System.out.println("Fight in: " + background);
        try {
            if (background == "Desert") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            } else if (background == "Ice") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            } else if (background == "Lake") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            } else if (background == "Rock") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            } else if (background == "Forest") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            } else if (background == "Gras") {
                image7 = ImageIO.read(new File("image/Kampf_Hintergrund_"+ background +".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWeaponImage(int weapon) {
        System.out.println("Choosen Weapon: " + weapon);
        try {
            if (weapon == 1) {
                image6 = ImageIO.read(new File("image/TotenStab.png"));
            } else if (weapon == 2) {
                image6 = ImageIO.read(new File("image/Zauberstab.png"));
            } else if (weapon == 3) {
                image6 = ImageIO.read(new File("image/Bogen.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Charakter_Select(int Charakter) {
        if(Charakter == 1) {
            waffe = 1;
            player.setSpieler(70,40,50,45,20);
        } else if(Charakter == 2) {
            waffe = 2;
            player.setSpieler(50,30,50,40,20);
        } else if(Charakter == 3) {
            waffe = 2;
            player.setSpieler(50,30,50,40,20);
        }
        WindowStart[1] = false;
        WindowStart[2] = true;
        repaint();
    }





    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Painting Game");

        if(WindowStart[0]) {
            System.out.println("Name Enter");
            g.drawImage(image20, 0, 0, BreiteX, HoheY-40, null);
            g.setColor(Color.WHITE);
//			g.fillRect(40, 80, 400, 50);
            g.drawImage(image21, 40, 80, 400, 100, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 50));
            // TODO
            g.drawString(GameNameNormal, 50, 60);
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
            g.drawString("Name:", 210, 90);
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 40));
            g.setColor(Color.black);

            if(player.getName().length() == 1) {
                g.drawString(player.getName(), 230, 140);
            } else if(player.getName().length() == 2) {
                g.drawString(player.getName(), 220, 140);
            } else if(player.getName().length() == 3) {
                g.drawString(player.getName(), 210, 140);
            } else if(player.getName().length() == 4) {
                g.drawString(player.getName(), 200, 140);
            } else if(player.getName().length() == 5) {
                g.drawString(player.getName(), 190, 140);
            } else if(player.getName().length() == 6) {
                g.drawString(player.getName(), 180, 140);
            } else if(player.getName().length() == 7) {
                g.drawString(player.getName(), 160, 140);
            } else if(player.getName().length() == 8) {
                g.drawString(player.getName(), 150, 140);
            } else if(player.getName().length() == 9) {
                g.drawString(player.getName(), 140, 140);
            } else if(player.getName().length() == 10) {
                g.drawString(player.getName(), 130, 140);
            } else if(player.getName().length() == 11) {
                g.drawString(player.getName(), 120, 140);
            } else if(player.getName().length() == 12) {
                g.drawString(player.getName(), 110, 140);
            } else if(player.getName().length() == 13) {
                g.drawString(player.getName(), 80, 140);
            } else if(player.getName().length() == 14) {
                g.drawString(player.getName(), 70, 140);
            } else if(player.getName().length() == 15) {
                g.drawString(player.getName(), 65, 140);
            }
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 30));
            g.setColor(Color.WHITE);
            //14 Maximum

            if( player.getName().length() == 14) {
                System.out.println("Name Entspricht 14 Zeichen");
                WindowStart[1] = true;
                WindowStart[0] = false;
                repaint();
            }

        } else if(WindowStart[1]) {
            System.out.println("Weapon Enter");
            this.setPlayerImage(SpielerIcon);

            g.drawImage(image22, 0, 0, BreiteX, HoheY, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 60));
            g.drawString("Charakter", 100, 60);

            g.drawImage(image2_2, 80, 80, 300, 300, null);

            imagePic27 = new ImageIcon("image/1Links.png");
            image27 = imagePic27.getImage();
            g.drawImage(image27, 380, 190, 80, 80, null);

            imagePic27 = new ImageIcon("image/1Rechts.png");
            image27 = imagePic27.getImage();
            g.drawImage(image27, 50, 190, 80, 80, null);

            imagePic27 = new ImageIcon("image/3Enter.png");
            image27 = imagePic27.getImage();
            g.drawImage(image27, 150, 390, 200, 50, null);
        } else if(WindowStart[2]) {
            this.setShopImage();

            if(spawnGegner) {
                for(int iiiu = 0; iiiu<L1.getGegnerAnzahl();iiiu++) {
                    //x460(23) y280(14)
                    int rndm1 = (int)(Math.random()*23);
                    if(rndm1 == 0) {
                        rndm1 = 1;
                    }
                    int rndm2 = (int)(Math.random()*14);
                    //Sicherung
                    if(rndm2 == 0) {
                        rndm2 = 1;
                    }
                    L1.spawngegner(rndm1, rndm2,iiiu);
                }
                spawnGegner = false;

            }

            if(	player.getX()/20 >= 0 && player.getY()/20 >= 0 && player.getX()/20 <= 10 & player.getY()/20 <= 5
                    || player.getX()/20 >= 11 && player.getY()/20 >= 0 && player.getX()/20 <= 13 && player.getY()/20 <= 3) {
                this.setFightBackgroundImage("Desert");
            } else if(player.getX()/20 >= 14 && player.getY()/20 >= 0 && player.getX()/20 <= 23 & player.getY()/20 <= 2) {
                this.setFightBackgroundImage("Ice");
            } else if(player.getX()/20 >= 19 && player.getY()/20 >= 3 && player.getX()/20 <= 23 & player.getY()/20 <= 9
                    || player.getX()/20 >= 20 && player.getY()/20 >= 10 && player.getX()/20 <= 23 & player.getY()/20 <= 12) {
                this.setFightBackgroundImage("Lake");
            } else if(player.getX()/20 >= 0 && player.getY()/20 >= 6 && player.getX()/20 <= 7 & player.getY()/20 <= 14) {
                this.setFightBackgroundImage("Rock");
            } else if(player.getX()/20 >= 8 && player.getY()/20 >= 10 && player.getX()/20 <= 19 & player.getY()/20 <= 14
                    || player.getX()/20 >= 20 && player.getY()/20 >= 13 && player.getX()/20 <= 23 & player.getY()/20 <= 14) {
                this.setFightBackgroundImage("Forest");
            } else if(player.getX()/20 >= 14 && player.getY()/20 >= 3 && player.getX()/20 <= 18 & player.getY()/20 <= 3
                    || player.getX()/20 >= 11 && player.getY()/20 >= 4 && player.getX()/20 <= 18 & player.getY()/20 <= 5
                    || player.getX()/20 >= 8 && player.getY()/20 >= 6 && player.getX()/20 <= 18 & player.getY()/20 <= 9) {
                this.setFightBackgroundImage("Gras");
            }

            if (Restgegener <= 0) {
                System.out.println("Naechte Welle");
                spawnGegner = true;
                L1.setGegnerLevel(L1.getGegnerLevel() + 10);
                System.out.println("Level = " + L1.getGegnerLevel());
                L1.setGegnerAnzahl(4);
                Restgegener = L1.getGegnerAnzahl();
                repaint();
            }

            if(player.getHp() <= 0) {
                GameOver = true;
                WindowStart[3] = true;
                // TOT
            }

//		g.setColor(Color.RED);
//		g.fillRect(0, 0, 11*20, 6*20);
//		g.fillRect(0, 0, 14*20, 5*20);
//		g.setColor(Color.BLUE);
//		g.fillRect(14*20, 0, 10*20, 3*20);
//		g.setColor(Color.CYAN);
//		g.fillRect(19*20, 3*20, 5*20, 7*20);
//		g.fillRect(20*20, 10*20, 4*20, 3*20);
//		g.setColor(Color.ORANGE);
//		g.fillRect(0*20, 6*20, 8*20, 9*20);
//		g.setColor(Color.GREEN);
//		g.fillRect(8*20, 10*20, 12*20, 10*20);
//		g.fillRect(20*20, 13*20, 4*20, 2*20);
//		g.setColor(Color.PINK);
//		g.fillRect(14*20, 3*20, 5*20, 2*20);
//		g.fillRect(11*20, 4*20, 8*20, 2*20);
//		g.fillRect(8*20, 6*20, 11*20, 4*20);
            g.drawImage(image1, 0, 0, BreiteX, HoheY-200, null);

            g.drawImage(image2, player.getX(), player.getY(), PlayerGrosse, PlayerGrosse, null);

            for(int uuz = 0;uuz<L1.getGegnerAnzahl();uuz++) {
//			g.drawImage(image3, gegnerPose[uuz] [0], gegnerPose[uuz] [1], PlayerGrosse, PlayerGrosse, null);
                g.drawImage(image3, L1.getGegnerPose(uuz, 0), L1.getGegnerPose(uuz, 1), PlayerGrosse, PlayerGrosse, null);
            }

            g.setColor(Color.RED);
            g.drawImage(image4, shop.getShopPose(0), shop.getShopPose(1), PlayerGrosse, PlayerGrosse, null);
            if(player.getX()==shop.getShopPose(0) && player.getY()==shop.getShopPose(1)) {
                imShop = true;
                shop();
            } else {
                imShop = false;
            }


            if(imShop == true) {
                g.drawImage(image8, 0, 0, BreiteX, HoheY-200, null);

                int LOL1 = 100;

                g.drawImage(image10, 100, 60, 50, 50, null);
                g.drawImage(image11, 160, 60, 50, 50, null);
                g.drawImage(image12, 220, 60, 50, 50, null);
                g.drawImage(image13, 280, 60, 50, 50, null);
                g.drawImage(image14, 340, 60, 50, 50, null);

                // 7 = 1Zeile
                int KeyForBuy = 1;
                g.setColor(Color.WHITE);
                g.setFont(new Font("Franklin Gothic Demi Italic", 4, 15));
                g.drawString("Or Use Key", LOL1-90, 150);
                if(5 <= 7) {
                    for(int izz = 0; izz <5;izz++) {
                        g.setColor(Color.BLACK);
                        g.fillRect(LOL1, 120, 50, 20);
                        g.setColor(Color.WHITE);
                        g.fillRect(LOL1+2, 122, 46, 16);
                        g.setFont(new Font("Franklin Gothic Demi Italic", 4, 15));
                        g.setColor(Color.BLACK);
                        g.drawString(shop.getPreise()+"$", LOL1+2, 135);
                        g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
                        g.setColor(Color.WHITE);
                        g.drawString(WaffenName[izz], LOL1+5, 40);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("Franklin Gothic Demi Italic", 4, 15));
                        g.drawString("" + KeyForBuy, LOL1+2, 150);
                        KeyForBuy = KeyForBuy + 1;
                        LOL1 = LOL1 + 60;
                    }
                    g.drawImage(image9, 0, 155, BreiteX, 10, null);
                }

            }

            for(int uuz = 0;uuz<L1.getGegnerAnzahl();uuz++) {
                if(player.getX()==L1.getGegnerPose(uuz, 0) && player.getY()==L1.getGegnerPose(uuz, 1)) {
                    imKampf[uuz] = true;
                    kampf();
//				System.out.println("Im Kampf mit " + uuz + ". Gegner");
                    Kampfgegen = uuz;
                } else {
                    imKampf[uuz] = false;
                }
            }


            // image2_2
            for(int ooi = 0; ooi<4;ooi++) {
                if(imKampf[ooi] == true) {
                    Spieler_vs_Ki = true;
                    //Karte - Spieler - Gegner
                    g.drawImage(image7, 0, 0, BreiteX, HoheY-200, null);
                    g.drawImage(image2_2, 10, 100, 200, 200, null);
                    g.drawImage(image3_2, 280, 100, 200, 200, null);
                    String[] gegnerwerteString = new String[anzahl_von_waffen];
                    g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
                    g.setColor(Color.WHITE);

                    for(int uztz = 0; uztz<gegnerwerteString.length;uztz++) {
//					gegnerwerteString[uztz] = String.valueOf(gegnerWerte[Kampfgegen][uztz]);
                        gegnerwerteString[uztz] = String.valueOf(L1.getgegnerWerte(Kampfgegen, uztz));
                    }
                    g.drawString("dmg: " + gegnerwerteString[0], 20, 30);
                    g.drawString("def: " + gegnerwerteString[1], 110, 30);
                    g.drawString("hp: " + gegnerwerteString[2], 200, 30);
                    g.drawString("acc: " + gegnerwerteString[3], 290, 30);
                    if(player.getAcc() > L1.getgegnerWerte(Kampfgegen,4)) {
                        g.setColor(Color.BLUE);
                    } else if(player.getAcc() < L1.getgegnerWerte(Kampfgegen,4)) {
                        g.setColor(Color.RED);
                    } else if(player.getAcc() == L1.getgegnerWerte(Kampfgegen,4)) {
                        g.setColor(Color.YELLOW);
                    }
                    g.drawString("dge: " + gegnerwerteString[4], 380, 30);

//				Schaden2

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Franklin Gothic Demi Italic", 4, 40));
                    g.drawString("- " + Schaden1, 380, 70);
                    g.drawString("- " + Schaden2, 30, 70);
                }
            }



            // HotBar
            g.drawImage(image15, 0, HoheY-200, BreiteX, 200, null);

            // Pfeil Tasten
            if(Spieler_vs_Ki == false) {

                // Oben
                g.drawImage(image16, 400, 330, 30, 30, null);

                // Unten
                g.drawImage(image17, 400, 390, 30, 30, null);

                // LINKS
                g.drawImage(image18, 370, 360, 30, 30, null);

//			Rechts
                g.drawImage(image19, 430, 360, 30, 30, null);
            } else {
                g.drawImage(image23, 340, 320, 120, 40, null);
                g.drawImage(image24, 340, 380, 120, 40, null);
                g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
                g.setColor(Color.BLACK);
                g.drawString("Q", 375, 365);
                g.drawString("E", 375, 425);

            }


            //Herzen
            int NeueHerzenZeile = 15;
            int groeseDerHerzen = 20;
            g.setColor(Color.RED);
            Herzen = player.getHp() / 10;
//		System.out.println("Live = " + Herzen);

            for(int zzzu = 0;zzzu<Herzen;zzzu++) {
                g.drawImage(image5, NeueHerzenZeile, HoheY-180, groeseDerHerzen, groeseDerHerzen, null);
                NeueHerzenZeile = NeueHerzenZeile + (groeseDerHerzen);
            }
            if (player.getHp() > 1 && player.getHp() < 10) {
                g.drawImage(image26, NeueHerzenZeile, HoheY-180, groeseDerHerzen, groeseDerHerzen, null);
            }

            //Geld
            g.setColor(Color.YELLOW);
            GeldString = String.valueOf(player.getGeld());
            g.setFont(new Font("Matura MT Script Capitals", 4, 40));
            g.drawString(GeldString + "$", 20, 380);

            //Name
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
            g.setColor(Color.WHITE);
            g.drawString(player.getName(), 20, 420);

            //Waffe
//		g.setColor(Color.BLACK);
            int groese = 80;
            if(waffe == 1) {
                this.setWeaponImage(1);
                g.drawImage(image6, 235, 325, groese, groese, null);
            } else if(waffe == 2) {
                this.setWeaponImage(2);
                g.drawImage(image6, 235, 325, groese, groese, null);
            } else if(waffe == 3) {
                this.setWeaponImage(3);
                g.drawImage(image6, 235, 325, groese, groese, null);
            }
            String[] waffenString = new String[5];
            waffenString[0] = String.valueOf(player.getDmg());
            waffenString[1] = String.valueOf(player.getDef());
            waffenString[2] = String.valueOf(player.getHp());
            waffenString[3] = String.valueOf(player.getAcc());
            waffenString[4] = String.valueOf(player.getDge());
            g.setFont(new Font("Franklin Gothic Demi Italic", 4, 20));
            g.setColor(Color.WHITE);
            g.drawString("dmg: " + waffenString[0], 20, 450);
            g.drawString("def: " + waffenString[1], 110, 450);
            g.drawString("hp: " + waffenString[2], 200, 450);
            g.drawString("acc: " + waffenString[3], 290, 450);
            g.drawString("dge: " + waffenString[4], 380, 450);
            // Schaden = dmg
            // verteidigung = def
            // Leben = hp
            // genauigkeit = acc
            // ausweichen = eva dge

            if(Spieler_vs_Ki == true) {

            }
        } else if(WindowStart[3] && GameOver) {
            g.drawImage(image25, 0, 0, BreiteX, HoheY, null);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("X = " +  x + " | y = " + y);
        if(GameOver == false) {
            if(Spieler_vs_Ki == false) {
                if(x > 405 && x < 435 && y > 360 && y < 390) {
                    // UP
//					player.setYup_down(PlayerGrosse,true);
                    player.setY(player.getY() - PlayerGrosse);
                    // UP
                    repaint();
                }

                if(x > 405 && x < 435 && y > 420 && y < 450) {
                    // Down
//					player.setYup_down(PlayerGrosse,false);
                    player.setY(player.getY() + PlayerGrosse);
                    player.TestCords();
                    repaint();
                }

                if(x > 375 && x < 405 && y > 390 && y < 420) {
                    // Linke
                    player.setY(player.getY() + PlayerGrosse);
                    player.TestCords();
                    repaint();
                }

                if(x > 435 && x < 465 && y > 390 && y < 420) {
                    //Rechts
                    player.setY(player.getY() - PlayerGrosse);
                    player.TestCords();
                    repaint();
                }

            } else {
                if(GegnerZug == false) {
//					Optionen Im Kampf
                    if(x > 350 && x < 470 && y > 345 && y < 385) {
                        angriff();
                    }
                    if(x > 350 && x < 470 && y > 405 && y < 445) {
                        flucht();
                    }
                }
            }
            if(WindowStart[1] == true) {
                if(x > 55 && x < 125 && y > 225 && y < 275) {
//					System.out.println("LEFT");
                    if(SpielerIcon == 1) {
                        SpielerIcon = 2;
                        repaint();
                    } else if(SpielerIcon == 2) {
                        SpielerIcon = 3;
                        repaint();
                    } else if(SpielerIcon == 3) {
                        SpielerIcon = 1;
                        repaint();
                    }
                }
                if(x > 385 && x < 455 && y > 225 && y < 275) {
                    if(SpielerIcon == 1) {
                        SpielerIcon = 3;
                        repaint();
                    } else if(SpielerIcon == 3) {
                        SpielerIcon = 2;
                        repaint();
                    } else if(SpielerIcon == 2) {
                        SpielerIcon = 1;
                        repaint();
                    }
                }

                if(x > 155 && x < 350 && y > 420 && y < 460) {
                    if(SpielerIcon == 1) {
                        Charakter_Select(1);
                        repaint();
                    } else if(SpielerIcon == 3) {
                        Charakter_Select(2);
                        repaint();
                    } else if(SpielerIcon == 2) {
                        Charakter_Select(3);
                        repaint();
                    }
                }
            }
        }

        if(GameOver == true) {
            if(x > 130 && x < 360 && y > 130 && y < 185) {
                System.out.println("Restart");
                RestartWindow = true;
                neuStart();
                // TODO
            }
        }

        if(imShop == true) {

            if(player.getGeld()>=shop.getPreise()) {
                // 50 Platz + 10 abstand
                if(x > 105 && x < 155 && y > 90 && y < 130 && player.getDmg() < shop.getMaximumWeaponLevel()) {
                    player.setDmg(player.getDmg() + 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(x > 165 && x < 215 && y > 90 && y < 130 && player.getDef() < shop.getMaximumWeaponLevel()) {
                    player.setDef(player.getDef() + 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(x > 225 && x < 275 && y > 90 && y < 130 && player.getHp() < 100) {
                    player.setHp(player.getHp() + 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(x > 285 && x < 335 && y > 90 && y < 130 && player.getAcc() < shop.getMaximumWeaponLevel()) {
                    player.setAcc(player.getAcc() + 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(x > 345 && x < 395 && y > 90 && y < 130 && player.getDge() < shop.getMaximumWeaponLevel()) {
                    player.setDge(player.getDge() + 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        if(GameOver == false) {
            if(Spieler_vs_Ki == false && WindowStart[0] == false && WindowStart[1] == false) {
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    player.setY(player.getY() - PlayerGrosse);
                    player.TestCords();

                }
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    player.setY(player.getY() + PlayerGrosse);
                    player.TestCords();

                }
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    player.setX(player.getX() + PlayerGrosse);
                    player.TestCords();

                }
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    player.setX(player.getX() - PlayerGrosse);
                    player.TestCords();

                }
            } else if(Spieler_vs_Ki == true && WindowStart[0] == false && WindowStart[1] == false){
                if(GegnerZug == false) {
//					Optionen Im Kampf
                    if(key == KeyEvent.VK_Q) {
                        angriff();
                    } else if(key == KeyEvent.VK_E) {
                        flucht();
                    }
                }
            }
        }
        if(imShop == true) {

            if(player.getGeld()>=shop.getPreise()) {
                // 50 Platz + 10 abstand
                if(key == KeyEvent.VK_1 && player.getDmg() < shop.getMaximumWeaponLevel()) {
                    waffenUpdate(0, 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(key == KeyEvent.VK_2 && player.getDef() < shop.getMaximumWeaponLevel()) {
                    waffenUpdate(1, 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(key == KeyEvent.VK_3 && player.getHp() < 100) {
                    waffenUpdate(2, 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(key == KeyEvent.VK_4 && player.getAcc() < shop.getMaximumWeaponLevel()) {
                    waffenUpdate(3, 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
                if(key == KeyEvent.VK_5 && player.getDge() < shop.getMaximumWeaponLevel()) {
                    waffenUpdate(4, 10);
                    player.setGeld(player.getGeld() - shop.getPreise());
                }
            }

        }

        if(WindowStart[1] == true) {
            if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
//				System.out.println("LEFT");
                if(SpielerIcon == 1) {
                    SpielerIcon = 2;

                } else if(SpielerIcon == 2) {
                    SpielerIcon = 3;

                } else if(SpielerIcon == 3) {
                    SpielerIcon = 1;

                }
            }
            if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
//				System.out.println("Right");
                if(SpielerIcon == 1) {
                    SpielerIcon = 3;

                } else if(SpielerIcon == 3) {
                    SpielerIcon = 2;

                } else if(SpielerIcon == 2) {
                    SpielerIcon = 1;

                }
            }
            if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
                if(SpielerIcon == 1) {
                    Charakter_Select(1);

                } else if(SpielerIcon == 3) {
                    Charakter_Select(2);

                } else if(SpielerIcon == 2) {
                    Charakter_Select(3);

                }
            }

        }

        if(WindowStart[0]) {

            if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
                System.out.println("Enter");
                for(int uuiuui = GameMode.player.getName().length();uuiuui<14;uuiuui++) {
                    GameMode.player.setName(GameMode.player.getName() + " ");
                }
//					System.out.println("L�nge = " + Name.length());

            }
            if(key == KeyEvent.VK_Q) {
                GameMode.player.setName(GameMode.player.getName() + "Q");

            }
            if(key == KeyEvent.VK_W) {
                GameMode.player.setName(GameMode.player.getName() + "W");

            }
            if(key == KeyEvent.VK_E) {
                GameMode.player.setName(GameMode.player.getName() + "E");

            }
            if(key == KeyEvent.VK_R) {
                GameMode.player.setName(GameMode.player.getName() + "R");

            }
            if(key == KeyEvent.VK_T) {
                GameMode.player.setName(GameMode.player.getName() + "T");

            }
            if(key == KeyEvent.VK_Z) {
                GameMode.player.setName(GameMode.player.getName() + "Z");

            }
            if(key == KeyEvent.VK_U) {
                GameMode.player.setName(GameMode.player.getName() + "U");

            }
            if(key == KeyEvent.VK_I) {
                GameMode.player.setName(GameMode.player.getName() + "I");

            }
            if(key == KeyEvent.VK_O) {
                GameMode.player.setName(GameMode.player.getName() + "O");

            }
            if(key == KeyEvent.VK_P) {
                GameMode.player.setName(GameMode.player.getName() + "P");

            }
            if(key == KeyEvent.VK_A) {
                GameMode.player.setName(GameMode.player.getName() + "A");

            }
            if(key == KeyEvent.VK_S) {
                GameMode.player.setName(GameMode.player.getName() + "S");

            }
            if(key == KeyEvent.VK_D) {
                GameMode.player.setName(GameMode.player.getName() + "D");

            }
            if(key == KeyEvent.VK_F) {
                GameMode.player.setName(GameMode.player.getName() + "F");

            }
            if(key == KeyEvent.VK_G) {
                GameMode.player.setName(GameMode.player.getName() + "G");

            }
            if(key == KeyEvent.VK_H) {
                GameMode.player.setName(GameMode.player.getName() + "H");

            }
            if(key == KeyEvent.VK_J) {
                GameMode.player.setName(GameMode.player.getName() + "J");

            }
            if(key == KeyEvent.VK_K) {
                GameMode.player.setName(GameMode.player.getName() + "K");

            }
            if(key == KeyEvent.VK_L) {
                GameMode.player.setName(GameMode.player.getName() + "L");

            }
            if(key == KeyEvent.VK_Y) {
                GameMode.player.setName(GameMode.player.getName() + "Y");

            }
            if(key == KeyEvent.VK_X) {
                GameMode.player.setName(GameMode.player.getName() + "X");

            }
            if(key == KeyEvent.VK_C) {
                GameMode.player.setName(GameMode.player.getName() + "C");

            }
            if(key == KeyEvent.VK_V) {
                GameMode.player.setName(GameMode.player.getName() + "V");

            }
            if(key == KeyEvent.VK_B) {
                GameMode.player.setName(GameMode.player.getName() + "B");

            }
            if(key == KeyEvent.VK_N) {
                GameMode.player.setName(GameMode.player.getName() + "N");

            }
            if(key == KeyEvent.VK_M) {
                GameMode.player.setName(GameMode.player.getName() + "M");

            }
            if(key == KeyEvent.VK_1) {
                GameMode.player.setName(GameMode.player.getName() + "1");

            }
            if(key == KeyEvent.VK_2) {
                GameMode.player.setName(GameMode.player.getName() + "2");

            }
            if(key == KeyEvent.VK_3) {
                GameMode.player.setName(GameMode.player.getName() + "3");

            }
            if(key == KeyEvent.VK_4) {
                GameMode.player.setName(GameMode.player.getName() + "4");

            }
            if(key == KeyEvent.VK_5) {
                GameMode.player.setName(GameMode.player.getName() + "5");

            }
            if(key == KeyEvent.VK_6) {
                GameMode.player.setName(GameMode.player.getName() + "6");

            }
            if(key == KeyEvent.VK_7) {
                GameMode.player.setName(GameMode.player.getName() + "7");

            }
            if(key == KeyEvent.VK_8) {
                GameMode.player.setName(GameMode.player.getName() + "8");

            }
            if(key == KeyEvent.VK_9) {
                GameMode.player.setName(GameMode.player.getName() + "9");

            }
            if(key == KeyEvent.VK_0) {
                GameMode.player.setName(GameMode.player.getName() + "0");

            }
            if(key == KeyEvent.VK_PLUS) {
                GameMode.player.setName(GameMode.player.getName() + "+");

            }
            if(key == KeyEvent.VK_MINUS) {
                GameMode.player.setName(GameMode.player.getName() + "-");

            }
        }

        repaint();


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
