package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameMain extends Game {

	GameMain game;
	
	public GameMain() {

	}

	/*public GameMain() {
		super();
	}*/

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new SimpleDirectionGestgureDetector(new SimpleDirectionGestgureDetector.DirectionListener() {

			public void onUp() {
				
			}

			public void onRight() {

			}

			public void onLeft() {

			}

			public void onDown() {

			}
			}));
		game = this;
		setScreen(new FMainMenu(game));
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
