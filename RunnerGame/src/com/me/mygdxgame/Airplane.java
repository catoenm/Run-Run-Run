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


	Rectangle bounds;
	
	public Airplane(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
		
		bounds = new Rectangle(position.x, position.y, WIDTH/4, HEIGHT/4);
		}
	
	void update(){
		position.x -= WIDTH/85;
		
		bounds.set(position.x, position.y, WIDTH/10, HEIGHT/4);

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

	public Rectangle getBounds() {
		return bounds;
	}


	
}