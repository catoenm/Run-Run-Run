package com.me.mygdxgame;
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
		
		bounds1 = new Rectangle(position.x-HEIGHT/100, position.y, WIDTH/4, HEIGHT/7);
		bounds2 = new Rectangle(position.x , position.y + WIDTH/10, WIDTH/15, HEIGHT/6);
		bounds3 = new Rectangle(position.x + WIDTH/12, position.y + HEIGHT/6, WIDTH/6, HEIGHT/32);
		overallBounds = new Rectangle(position.x - WIDTH/125, position.y, WIDTH/4, HEIGHT/5);
	}
	
	void update(double gameSpeed){
		position.x -= gameSpeed;
		
		bounds1.set(position.x+HEIGHT/125, position.y, WIDTH/15, HEIGHT/14);
		bounds2.set(position.x + WIDTH/15, position.y, WIDTH/15, HEIGHT/8);
		bounds3.set(position.x + WIDTH/12, position.y + HEIGHT/6, WIDTH/6, HEIGHT/32);
		overallBounds.set(position.x - WIDTH/125, position.y, WIDTH/4, HEIGHT/5);
		
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