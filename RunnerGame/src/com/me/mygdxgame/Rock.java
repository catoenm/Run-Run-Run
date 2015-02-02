package com.me.mygdxgame;

<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rock{
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	double speed = WIDTH/150;

	Circle bounds;
	
	public Rock(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
		bounds = new Circle(position, WIDTH/40);
	}
	
	void update(){
		position.x -= WIDTH/85;
		bounds.set(position.x,position.y, WIDTH/40);

	}
	public void draw(){
		batch.draw(texture, position.x, position.y, WIDTH/6, WIDTH/6);
		
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public float getX(){
		return position.x;
	}
	public float getY(){
		return position.y;
	}

	public Circle getBounds() {
		return bounds;
	}
	
}
=======
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rock {

	public Rock(int i, SpriteBatch batch, Texture rock, int wIDTH) {
		// TODO Auto-generated constructor stub
	}

}
>>>>>>> efd7e639175cb50eaf717c3b6bce6dfcedda6f51
