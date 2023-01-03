package Java_Adventure_Game;

public class Spieler {

	
	private int xPose = 0;
	private int yPose = 0;
	private int Dmg;
	private int Def;
	private int Hp;
	private int Acc;
	private int Dge;
	private int Geld = 0;
	private String Name = "";
	
	public Spieler() {
		
	}


	public void setSpieler(int dmg, int def, int hp, int acc, int dge) {
		this.Dmg = dmg;
		this.Def = def;
		this.Hp = hp;
		this.Acc = acc;
		this.Dge = dge;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def;
	}

	public int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}

	public int getAcc() {
		return Acc;
	}

	public void setAcc(int acc) {
		Acc = acc;
	}

	public int getDge() {
		return Dge;
	}

	public void setDge(int dge) {
		Dge = dge;
	}

	public void setDmg(int dmg) {
		Dmg = dmg;
	}
	
	public int getDmg() {
		return Dmg;
	}
	
	public int getGeld() {
		return this.Geld;
	}

	public void setGeld(int geld) {
		Geld = geld;
	}
	
	public void TestCords() {
		if(GameMode.player.xPose <= 0) {
			GameMode.player.xPose = 0;
		}
		if(xPose == 480) {
			xPose = 460;
			//x460
		}
		if(yPose <= 0) {
			yPose = 0;
		}
		if(yPose == 300) {
			yPose = 280;
		}
	}
	
	public void setX(int x) {
		this.xPose = x;
	}
	public void setY(int y) {
		this.yPose = y;
	}
	
	public int getX() {
		return this.xPose;
	}
	public int getY() {
		return this.yPose;
	}

}
