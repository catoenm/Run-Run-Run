package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player{

	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	final int col = 5;
	final int row = 2;
	Vector2 position;

	int frame = 0;
	Animation animation;
	Texture playerTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	float stateTime = 0f;
	int playerY;
	int accel;
	Rectangle bounds;
	//TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
	public Player(Texture sheet, Vector2 position){
		playerTexture = new Texture(Gdx.files.internal("sheet.png"));
		TextureRegion[][] tmp = TextureRegion
				.split(playerTexture, playerTexture.getWidth() / col,
						playerTexture.getHeight() / row);
		frames = new TextureRegion[row * col];
		this.position = position;
		int index = 0;
		bounds = new Rectangle(position.x, position.y, WIDTH/10, HEIGHT/5);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		animation = new Animation(0.075f, frames);
		currentFrame = animation.getKeyFrame(0);

	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void updatePlayer(){
		stateTime += Gdx.graphics.getDeltaTime();    
        currentFrame = animation.getKeyFrame(stateTime, true);
        bounds.set(position.x, position.y - HEIGHT/25, WIDTH/10, HEIGHT/6);
	}
	
	public TextureRegion getTexture(){
		return currentFrame;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	
}
