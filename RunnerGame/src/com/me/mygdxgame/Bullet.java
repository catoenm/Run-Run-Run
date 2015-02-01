package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	int rotation = 0;
	double time = 0;
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	Rectangle bounds;
	boolean active;
	Sprite sprite;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Bullet(int height, SpriteBatch batch, Texture texture, int width){
		this.texture = texture;
		position = new Vector2(WIDTH/9, height);
		this.batch = batch;
		sprite = new Sprite(texture);
		
		bounds = new Rectangle(position.x, position.y, WIDTH/11, HEIGHT/11);
	}
	
	public void update(){
		if (position.x <  WIDTH + WIDTH/10){
			position.x += WIDTH/125;
			bounds.set(position.x, position.y, WIDTH/11, HEIGHT/11);
		}
	}

	public float getX(){
		return position.x;
	}
	public float getY(){
		return position.y;
	}

	public void draw() {
		time += Gdx.graphics.getDeltaTime();
		if (time > 0.1)
			sprite.flip(false, true);
		batch.draw(sprite, position.x, position.y, WIDTH/10, HEIGHT/10);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

}
