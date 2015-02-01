package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Airplane{
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	Rectangle bounds;
	
	public Airplane(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
		
		bounds = new Rectangle(position.x, position.y, WIDTH/4, HEIGHT/7);
		}
	
	void update(boolean airplaneHit){
		if (airplaneHit)
			position.x=-WIDTH/2;
		else if (position.x < -WIDTH/2)
			position.x = (float) (WIDTH + 1000*Math.random());
		position.x -= WIDTH/85;
		
		bounds.set(position.x, position.y, WIDTH/10, HEIGHT/16);

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

	public Rectangle getBounds() {
		return bounds;
	}


	
}