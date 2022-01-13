package gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import framework.Main;

public class TestPower extends PowerUp {
	public TestPower(double posX, double posY, int width, int height) {
		super(posX, posY, width, height);
		
	}
	
	public void initShape() {
		image = new Image("2.png");
		
		super.initShape();
	}

	@Override
	public void usePower() {
		// TODO Auto-generated method stub

	}

	/*
	 * Id�er til powerups: 
	 * - Freeze (tager et liv og stopper ramtes n�ste tur) 
	 * - Metal (tager to liv) 
	 * - Big (rammer et st�rre omr�de) - evt. spread 
	 * - Shield (du mister ikke et liv n�ste gang du bliver ramt)
	 * - Slime (modstanderen kan ikke v�lge "Move" p� sin tur)
	 */
}
