package gameObjects;

public class Projectile extends GameObject{
	double direction;
	double speed;
	public Projectile(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		direction = 5; //skal hentes fra StartWindow og angives i grader
		direction = Math.toRadians(direction); // konverteres til radianer
		speed = 5; // skal hentes fra StartWindow
	}
	public void drawShape() {
		
	}
	public void step() {
		
	}
	public void collision() {
		
	}
	
	
}
