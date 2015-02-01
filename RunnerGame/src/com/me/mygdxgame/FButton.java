package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FButton {
	private int width, height, posX, posY;
	private Texture texture;
	private SpriteBatch batch;
	private boolean isClicked;
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	public FButton(int height, SpriteBatch batch, Texture texture, int width){
		this.texture = texture;
		this.batch = batch;
		this.width = width;
		this.height = height;
		this.isClicked = false;
		posX = WIDTH/50;
		posY = HEIGHT/50;
	}
	
	
	public boolean isHit(){
		if(Gdx.input.isTouched()){
			if (Gdx.input.getX() > posX && Gdx.input.getX() < posX + width && 
				Gdx.input.getY() < Gdx.graphics.getHeight() - posY && Gdx.input.getY() > Gdx.graphics.getHeight() - posY - height){
				isClicked = true;
				
			}
		}
		else if (isClicked){
			isClicked = false;
			return true;
		}
		return false;
	}
	
	public void draw(){
		batch.draw(texture, posX, posY, width/10, height/10);
	}
	
	public boolean getIsClicked(){
		return isClicked;
	}
	
	public void setTexture(String name){
		this.texture = new Texture(Gdx.files.internal(name));
	}


	
}
