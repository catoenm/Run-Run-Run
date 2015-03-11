package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cloud {
	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	SpriteBatch batch;
	Texture texture;
	Vector2 position;
	
	public Cloud(int height, SpriteBatch batch, Texture texture, int x){
		position = new Vector2(x, height);
		this.batch = batch;
		this.texture = texture;
	}
	
	public void update(double gameSpeed){
		position.x -= gameSpeed - WIDTH/80;
		if (position.x < -WIDTH/5){
			position.x = WIDTH;
			position.y = (float) (HEIGHT/9 * 4 + Math.random() * 4 * HEIGHT/9);
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
