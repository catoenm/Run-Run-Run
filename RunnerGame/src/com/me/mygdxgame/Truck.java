package com.me.mygdxgame;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Truck {
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	double speed = WIDTH/150;
	Rectangle bounds1,bounds2,bounds3, overallBounds;
	
	public Rectangle getOverallBounds() {
		return overallBounds;
	}

	public void setOverallBounds(Rectangle overallBounds) {
		this.overallBounds = overallBounds;
	}

	public Truck(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
		
		bounds1 = new Rectangle(position.x, position.y, WIDTH/4, HEIGHT/7);
		bounds2 = new Rectangle(position.x , position.y + WIDTH/10, WIDTH/15, HEIGHT/6);
		bounds3 = new Rectangle(position.x + WIDTH/10, position.y + HEIGHT/5 + HEIGHT/75, WIDTH/6, HEIGHT/40);
		overallBounds = new Rectangle(position.x, position.y, WIDTH/4, HEIGHT/7);
	}
	
	void update(){
		position.x -= WIDTH/85;
		
		bounds1.set(position.x, position.y, WIDTH/10, HEIGHT/17);
		bounds2.set(position.x + WIDTH/12, position.y-HEIGHT/50 , WIDTH/15, HEIGHT/9);
		bounds3.set(position.x + WIDTH/10, position.y + HEIGHT/7 + HEIGHT/75, WIDTH/6, HEIGHT/45);
		overallBounds.set(position.x, position.y, WIDTH/4, HEIGHT/7);
	}
	public void draw(){
		batch.draw(texture, position.x, position.y, WIDTH/4, WIDTH/7);
		
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
	
	public Rectangle getBounds3() {
		return bounds3;
	}
	
}