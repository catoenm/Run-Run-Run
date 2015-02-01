package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Crate {
	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	double speed = WIDTH/150;
	Rectangle bounds;
	
	public Crate(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
		bounds = new Rectangle(position.x, position.y, HEIGHT/10, HEIGHT/10);
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void update(){
		position.x -= speed;
		if (position.x < -WIDTH/5){
			position.x = WIDTH;
			position.y = (float) (HEIGHT/9 * 4 + Math.random() * 5 * HEIGHT/9);
			bounds.set(position.x, position.y, HEIGHT/10,HEIGHT/10);
		}
	}
	public void draw(){
		batch.draw(texture, position.x, position.y, WIDTH/5, WIDTH/10);
	}
	public float getX(){
		return position.x;
	}
	public float getY(){
		return position.y;
	}
	
}
