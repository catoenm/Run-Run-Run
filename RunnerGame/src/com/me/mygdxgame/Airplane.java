package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Airplane{
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	double speed = WIDTH/150;
	Rectangle bounds1, bounds2;
	
	public Airplane(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;

		bounds1 = new Rectangle(position.x, position.y + HEIGHT/55, WIDTH/5, HEIGHT/16);
		bounds2 = new Rectangle(position.x + WIDTH/25, position.y + HEIGHT/30, WIDTH/75, HEIGHT/12);
		}
	
	void update(double gameSpeed){
		position.x -= gameSpeed;

		bounds1.set(position.x, position.y + HEIGHT/55, WIDTH/5, HEIGHT/16);
		bounds2.set(position.x + WIDTH/25, position.y + HEIGHT/30, WIDTH/75, HEIGHT/10);
	}
	
	public void draw(){
		batch.draw(texture, position.x, position.y, WIDTH/4, WIDTH/7);
		
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

	public Rectangle getBounds1() {
		return bounds1;
	}
	public Rectangle getBounds2() {
		return bounds2;
	}


	
}