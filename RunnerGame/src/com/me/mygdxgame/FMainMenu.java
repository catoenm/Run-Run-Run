package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FMainMenu implements Screen{

	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	Game game;
	SpriteBatch batch;
	Texture background, playerSheet, back, roadLines;
	CButton play_button;
	Player player;
	Vector2 backPos1, backPos2,linePosition, playerPos;
	int posY = (int) (HEIGHT/10);
	

	public FMainMenu(Game game){
		this.game = game;
	}
	@Override
	public void render(float delta) {
		if (play_button.isHit()){
			game.setScreen(new PlayScreen(game, backPos1, backPos2, linePosition));
		}
		
		player.updatePlayer();
		updateLinePos();
		
		batch.begin();
		batch.draw(back, backPos1.x, 0, WIDTH * 2, HEIGHT);
		batch.draw(back, backPos2.x, 0, WIDTH * 2  + WIDTH/20, HEIGHT);
		batch.draw(background, 0, 0, WIDTH, HEIGHT);
		//batch.draw(roadLines, linePosition.x, linePosition.y, WIDTH * 2, HEIGHT/5);
		play_button.drawButton();
		batch.draw(player.getTexture(), WIDTH/10, posY, WIDTH/7, HEIGHT/5);
		batch.end();

	}

	void updateLinePos(){
		linePosition.x -= WIDTH/150;
		if (linePosition.x < -WIDTH)
			linePosition.x = 0;
		backPos1.x -= WIDTH/150;
		if (backPos1.x < -2 * WIDTH)
			backPos1.x = 2 * WIDTH;
		backPos2.x -= WIDTH/150;
		if (backPos2.x < -2 * WIDTH)
			backPos2.x = 2 * WIDTH;
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Texture.setEnforcePotImages(false);
		batch = new SpriteBatch();
		linePosition = new Vector2(0, WIDTH/20);
		//roadLines = new Texture(Gdx.files.internal("RoadLines.png"));
		playerSheet = new Texture(Gdx.files.internal("sheet.png"));
		playerPos = new Vector2(WIDTH/10, HEIGHT/10);
		player = new Player(playerSheet, playerPos);
		background = new Texture(Gdx.files.internal("Background.png"));

			play_button = new CButton(
					(int)WIDTH / 2 - (int) (WIDTH / 5.4), (int)HEIGHT
					/ 3,(int) (WIDTH / 2.7), (int) (HEIGHT / 4.8),
					"PlayButton.png", batch);
			backPos1 = new Vector2(0, 0);
			backPos2 = new Vector2(WIDTH * 2, 0);		
		
			back = new Texture(Gdx.files.internal("Hexagon Background.png"));
		//background 
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
