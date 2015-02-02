package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Airplane{
<<<<<<< HEAD
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	double speed = WIDTH/150;
	Rectangle bounds1, bounds2;
=======
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	Rectangle bounds;
>>>>>>> efd7e639175cb50eaf717c3b6bce6dfcedda6f51
	
	public Airplane(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
<<<<<<< HEAD

		bounds1 = new Rectangle(position.x, position.y+ HEIGHT/125, WIDTH/16, HEIGHT/5);
		bounds2 = new Rectangle(position.x + WIDTH/25, position.y + HEIGHT/15, WIDTH/9, HEIGHT/30);
		}
	
	void update(){
		position.x -= WIDTH/85;

		bounds1.set(position.x, position.y+ HEIGHT/125, WIDTH/16, HEIGHT/5);
		bounds2.set(position.x + WIDTH/25, position.y + HEIGHT/15, WIDTH/9, HEIGHT/30);
=======
		
		bounds = new Rectangle(position.x, position.y, WIDTH/4, HEIGHT/7);
		}
	
	void update(boolean airplaneHit){
		if (airplaneHit)
			position.x=-WIDTH/2;
		else if (position.x < -WIDTH/2)
			position.x = (float) (WIDTH + 1000*Math.random());
		position.x -= WIDTH/85;
		
		bounds.set(position.x, position.y, WIDTH/10, HEIGHT/16);
>>>>>>> efd7e639175cb50eaf717c3b6bce6dfcedda6f51

	}
	public void draw(){
		batch.draw(texture, position.x, position.y, WIDTH/4, WIDTH/7);
		
	}
<<<<<<< HEAD
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

=======
>>>>>>> efd7e639175cb50eaf717c3b6bce6dfcedda6f51
	public float getX(){
		return position.x;
	}
	public float getY(){
		return position.y;
	}

<<<<<<< HEAD
	public Rectangle getBounds1() {
		return bounds1;
	}
	public Rectangle getBounds2() {
		return bounds2;
=======
	public Rectangle getBounds() {
		return bounds;
>>>>>>> efd7e639175cb50eaf717c3b6bce6dfcedda6f51
	}


	
}