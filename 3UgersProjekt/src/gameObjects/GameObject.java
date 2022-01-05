package gameObjects;

import framework.Main;

public abstract class GameObject {
	
	public GameObject() {
		Main.objList.add(this);
	}
	
	public void run() {
		
	}

}
