public class Shop {


    private int [] ShopPose = new int[2];
    private int ShopLevel = 1;
    private int MaximumWeaponLevel = 100;
    private int Preise = 10;



    public int getShopPose(int xy) {
        return ShopPose[xy];
    }
    public void setShopPose(int Pose, int xy) {
        ShopPose[xy] = Pose;
    }
    public int getShopLevel() {
        return ShopLevel;
    }
    public void setShopLevel(int shopLevel) {
        ShopLevel = shopLevel;
    }
    public int getMaximumWeaponLevel() {
        return MaximumWeaponLevel;
    }
    public void setMaximumWeaponLevel(int maximumWeaponLevel) {
        MaximumWeaponLevel = maximumWeaponLevel;
    }
    public int getPreise() {
        return Preise;
    }
    public void setPreise(int preise) {
        Preise = preise;
    }

    public void spawnShop() {
        int rndm1 = (int)(Math.random()*23);
        if(rndm1 == 0) {
            rndm1 = 1;
        }
        if(rndm1 > 23) {
            rndm1 = 23;
        }
        if(rndm1 == GameMode.player.getX()/20) {
            rndm1 = 6;
        }
        //x460(23) y280(14)
        GameMode.shop.setShopPose(rndm1*20,0);

        int rndm2 = (int)(Math.random()*14);
        if(rndm2 <= 0) {
            rndm2 = 1;
        }
        if(rndm2 > 14) {
            rndm2 = 14;
        }
        if(rndm2 == GameMode.player.getY()/20) {
            rndm2 = 6;
        }
        GameMode.shop.setShopPose(rndm2*20,1);
        System.out.println("X = " + GameMode.shop.getShopPose(0) + " | Y = " + GameMode.shop.getShopPose(1));
    }
}
