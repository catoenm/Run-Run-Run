package com.me.mygdxgame;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class PlayScreen implements Screen{
	
	final float WIDTH = Gdx.graphics.getWidth();
	final float HEIGHT = Gdx.graphics.getHeight();
	
	ArrayList<Bullet> bullets;
	ArrayList<Truck> trucks;
	ArrayList<Airplane> planes;
	ArrayList<Crate> crates;
	ArrayList<Rock> rocks;
	ArrayList<Spaceship> spaceShips;
	Iterator <Bullet> bulletIterator;
	Iterator <Truck> truckIterator;
	Iterator <Airplane> planeIterator;
	Iterator <Crate> crateIterator;
	Iterator<Rock> rockIterator;
	Iterator<Spaceship> spaceshipIterator;
	CButton shootButton;
	Game game;
	SpriteBatch batch;
	Texture cloud, background, playerSheet, roadLines, hearts, truck, planeTex, rockTex, sShipTex, shootButtonTex, bulletTex, roadBack, spaceBack, crateTex;
	Player player;
	double vel = 0;
	boolean jumping = false, doubleJump = false, startCloud = false, waiting=false, shoot=false, truck1Hit=false, truck2Hit=false;
	double time = 0, scoreTime = 0, reloadTime = 0, truckTime=0, aircraftTime=0, crateTime=0, rockTime=0;;
	int score = 0, ammo = 3, numTrucks=0, numPlanes=0, numCrates=0, numRocks=0,numsShips=0;
	double gameSpeed = WIDTH/150;
	BitmapFont font;
	BitmapFont shadow;
	
	Cloud cloud1, cloud2;
	Vector2 cloudPosition, truckPosition, linePosition, backPos1, backPos2, playerPos, bulletPos;

	
	public PlayScreen(Game game, Vector2 backPos1, Vector2 backPos2, Vector2 linePosition){
		this.linePosition = linePosition;
		this.backPos1 = backPos1;
		this.backPos2 = backPos2;
		this.game = game;
	}
	@Override
	public void render(float delta) {
		
		updateLinePos();
		player.updatePlayer();
		checkFire();		
		checkJump();
		cloud1.update();
		cloud2.update();
		playerPos.y += vel;

		if (score == 50)
			background = roadBack;
		else if (score == 150)
			background = spaceBack;
		
		updateTimes();
		

		batch.begin();
		batch.draw(background, backPos1.x, 0, WIDTH * 2, HEIGHT);
		batch.draw(background, backPos2.x, 0, WIDTH * 2  + WIDTH/20, HEIGHT);
		if (score < 150)
			batch.draw(roadLines, linePosition.x, linePosition.y, WIDTH * 2, HEIGHT/5);
		batch.draw(player.getTexture(), WIDTH/10, playerPos.y, WIDTH/7, HEIGHT/5);
		font.draw(batch, " Score: " + score, WIDTH/2 - WIDTH/6, HEIGHT - HEIGHT/80);
		font.draw(batch, " Ammo: " + ammo, WIDTH/2 - WIDTH/6, HEIGHT - HEIGHT/8);
		
		updateBullets();
		updateCrates();
		makeCrates();
		if(score<0){
		
			updatePlanes();
			updateTrucks();
			makeTrucks();
			makePlanes();
			checkTruckCollisions();
			checkPlaneCollisions();
			bulletTruckCollision();
			bulletPlaneCollision();
			drawTrucks();
			drawPlanes();
		}
		
		if(score>0){
			updateRocks();
			updateShips();
			makeRocks();
			makeShips();
			bulletRockCollision();
			bulletSShipCollision();
			checkRockCollisions();
			checkSShipCollisions();
			drawRocks();
			drawsShips();
		}
		
		checkCrateCollisions();
		drawBullets();
		drawCrates();
		
		cloud1.draw();
		cloud2.draw();
		
		shootButton.drawButton();			
		batch.end();
	}

	void updateTimes(){
		scoreTime += Gdx.graphics.getDeltaTime();
		if (scoreTime > 1){
			score++;
			scoreTime = 0;
		}

		reloadTime += Gdx.graphics.getDeltaTime();
		
		if (reloadTime > 6){
			if (ammo < 3)
				ammo++;
			reloadTime = 0;
		}
		
		crateTime+= Gdx.graphics.getDeltaTime();
		if(crateTime > 25){
			numCrates++;
			crateTime=0;
		}
		
		aircraftTime+=Gdx.graphics.getDeltaTime();
		if(score<0){
			
			truckTime+= Gdx.graphics.getDeltaTime();
			if(truckTime > 3){
				numTrucks++;
				truckTime=0;
			}
		
		if(aircraftTime>6){
				numPlanes++;
				aircraftTime=0;
			}
		}
		else{
			if (aircraftTime>6){
				numsShips++;
				aircraftTime=0;
			}
		
			rockTime+=Gdx.graphics.getDeltaTime();
			if(rockTime > 3){
				numRocks++;
				rockTime=0;
			}
		}
	}
	
	void updateBullets(){
		bulletIterator = bullets.iterator();
		while(bulletIterator.hasNext()){
			Bullet nextBullet = bulletIterator.next();
			nextBullet.update();
			if (nextBullet.getX() > WIDTH)
				bulletIterator.remove();
		}
	}
	
	void drawBullets(){
		bulletIterator = bullets.iterator();
		while(bulletIterator.hasNext()){
			Bullet nextBullet = bulletIterator.next();
			nextBullet.draw();
		}
	}
	
	void updateTrucks(){
		truckIterator = trucks.iterator();
		while(truckIterator.hasNext()){
			Truck nextTruck = truckIterator.next();
			nextTruck.update(); 
			if (nextTruck.getX() < -WIDTH/5)
				truckIterator.remove();
		}
	}
	
	void drawTrucks(){
		truckIterator = trucks.iterator();
		while(truckIterator.hasNext()){
			Truck nextTruck = truckIterator.next();
			nextTruck.draw();
		}
	}

	void makeTrucks(){
		int n= (int) (Math.random()*4+1);
		if(n==1 && numTrucks>0){
				trucks.add (new Truck((int) (HEIGHT/10), batch, truck, (int) (WIDTH)));
				numTrucks--;
		}
	}
	
	void drawPlanes(){
		planeIterator = planes.iterator();
		while(planeIterator.hasNext()){
			Airplane nextPlane = planeIterator.next();
			nextPlane.draw();
		}
	}
	
	void updatePlanes(){
		planeIterator = planes.iterator();
		while(planeIterator.hasNext()){
			Airplane nextPlane = planeIterator.next();
			nextPlane.update(); 
			if (nextPlane.getX() < -WIDTH/5)
				planeIterator.remove();
		}
	}
	
	void makePlanes(){
		int height= (int)(Math.random()*HEIGHT/2+HEIGHT/3);
		int n= (int) (Math.random()*20+1);
		if(n==5 && numPlanes>0){
			planes.add(new Airplane(height, batch, planeTex, (int) WIDTH));
			numPlanes--;
		}
	}
	
	void updateCrates(){
		crateIterator = crates.iterator();
		while(crateIterator.hasNext()){
			Crate nextCrate = crateIterator.next();
			nextCrate.update();
			if (nextCrate.getX() < -WIDTH/5)
				crateIterator.remove();
		}
	}
	
	void drawCrates(){
		crateIterator = crates.iterator();
		while(crateIterator.hasNext()){
			Crate nextCrate = crateIterator.next();
			nextCrate.draw();
		}
	}
	
	void makeCrates(){
		int height= (int)(Math.random()*HEIGHT/2+HEIGHT/3);
		int n= (int) (Math.random()*20+1);
		if(n==5 && numCrates>0){
			crates.add(new Crate(height, batch, crateTex, (int) WIDTH));
			numCrates--;
		}
	}
	
	private void makeShips() {
		int height= (int)(Math.random()*HEIGHT/2+HEIGHT/3);
		int n= (int) (Math.random()*20+1);
		if(n==5 && numsShips>0){
			spaceShips.add(new Spaceship(height, batch, sShipTex, (int) WIDTH));
			numsShips--;
		}
	}
	
	void drawRocks(){
		rockIterator = rocks.iterator();
		while(rockIterator.hasNext()){
			Rock nextRock = rockIterator.next();
			nextRock.draw();
		}
	}
	
	private void makeRocks() {
		int n = (int) (Math.random()*4+1);
		if(n==1 && numRocks>0){
				rocks.add (new Rock((int) (HEIGHT/10), batch, rockTex, (int) (WIDTH)));
				numRocks--;
		}
		
	}
	
	void drawsShips(){
		spaceshipIterator = spaceShips.iterator();
		while(spaceshipIterator.hasNext()){
			Spaceship nextSpaceship =spaceshipIterator.next();
			nextSpaceship.draw();
		}
	}
	
	private void updateShips() {
		spaceshipIterator = spaceShips.iterator();
		while(spaceshipIterator.hasNext()){
			Spaceship nextSpaceship = spaceshipIterator.next();
			nextSpaceship.update(); 
			if (nextSpaceship.getX() < -WIDTH/5)
				spaceshipIterator.remove();
		}
		
	}
	
	private void updateRocks() {
		rockIterator = rocks.iterator();
		while(rockIterator.hasNext()){
			Rock nextRock = rockIterator.next();
			nextRock.update(); 
			if (nextRock.getX() < -WIDTH/5)
				rockIterator.remove();
		}
		
	}
	
	private void checkSShipCollisions() {
		spaceshipIterator = spaceShips.iterator();
		while(spaceshipIterator.hasNext()){
			Spaceship nextSpaceship = spaceshipIterator.next();
			if(player.getBounds().overlaps(nextSpaceship.getBounds1()) || player.getBounds().overlaps(nextSpaceship.getBounds2())){
				game.setScreen(new FMainMenu(game));
			}
		}
		
	}
	
	private void checkRockCollisions() {
		rockIterator = rocks.iterator();
		while(rockIterator.hasNext()){
			Rock nextRock = rockIterator.next();
			if(Intersector.overlapCircleRectangle(nextRock.getBounds(),player.getBounds())){
				game.setScreen(new FMainMenu(game));
			}
		}
	}
	
	private void bulletSShipCollision() {
		spaceshipIterator = spaceShips.iterator();
		bulletIterator = bullets.iterator();
		while (spaceshipIterator.hasNext()){
			Spaceship nextShip = spaceshipIterator.next();
			while(spaceshipIterator.hasNext()){
				Bullet nextBullet = bulletIterator.next();
				if (nextShip.getBounds1().overlaps(nextBullet.getBounds()) || nextShip.getBounds2().overlaps(nextBullet.getBounds())){
					spaceshipIterator.remove();
					bulletIterator.remove();
				}
			}
		}
	}
	
	private void bulletRockCollision() {
		rockIterator = rocks.iterator();
		bulletIterator = bullets.iterator();
		while (rockIterator.hasNext()){
			Rock nextRock = rockIterator.next();
			while(bulletIterator.hasNext()){
				Bullet nextBullet = bulletIterator.next();
				if (Intersector.overlapCircleRectangle(nextRock.getBounds(),nextBullet.getBounds())){
					rockIterator.remove();
					bulletIterator.remove();
				}
			}
		}
	}

	void bulletTruckCollision(){
		truckIterator = trucks.iterator();
		bulletIterator = bullets.iterator();
		while (truckIterator.hasNext()){
			Truck nextTruck = truckIterator.next();
			while(bulletIterator.hasNext()){
				Bullet nextBullet = bulletIterator.next();
				if (nextTruck.getOverallBounds().overlaps(nextBullet.getBounds())){
					truckIterator.remove();
					bulletIterator.remove();
				}
			}
		}
	}
	
	void bulletPlaneCollision(){
		planeIterator = planes.iterator();
		bulletIterator = bullets.iterator();
		while (planeIterator.hasNext()){
			Airplane nextPlane = planeIterator.next();
			while(bulletIterator.hasNext()){
				Bullet nextBullet = bulletIterator.next();
				if ((nextPlane.getBounds1()).overlaps(nextBullet.getBounds()) || nextPlane.getBounds2().overlaps(nextBullet.getBounds())){
					planeIterator.remove();
					bulletIterator.remove();
				}
			}
		}
	}
	
	void checkCrateCollisions(){
		crateIterator = crates.iterator();
		while(crateIterator.hasNext()){
			Crate nextCrate = crateIterator.next();
			if(player.getBounds().overlaps(nextCrate.getBounds())){
				crateIterator.remove();
				numCrates--;
				ammo=3;
			}
		}
	}
	
	void checkTruckCollisions(){
		truckIterator = trucks.iterator();
		while(truckIterator.hasNext()){
			Truck nextTruck = truckIterator.next();
			if(Gdx.input.isTouched() && (player.getBounds().overlaps(nextTruck.getBounds3()))){
				playerPos.y += HEIGHT/150;
				vel += HEIGHT/300;
				jumping = true;
				waiting=true;
			}
			
			else if (player.getBounds().overlaps(nextTruck.getBounds3())){
				vel=0;
				playerPos.y+= HEIGHT/250;
			}
	
			if (player.getBounds().overlaps(nextTruck.getBounds1()) || player.getBounds().overlaps(nextTruck.getBounds2()) ||player.getBounds().overlaps(nextTruck.getBounds2()))
				game.setScreen(new FMainMenu(game));
		}
	}

	
	void checkPlaneCollisions(){
		planeIterator = planes.iterator();
		while(planeIterator.hasNext()){
			Airplane nextPlane = planeIterator.next();
			if(player.getBounds().overlaps(nextPlane.getBounds1()) || player.getBounds().overlaps(nextPlane.getBounds2())){
				game.setScreen(new FMainMenu(game));
			}
		}
	}

	void checkJump(){
		if (Gdx.input.isTouched() && playerPos.y <= HEIGHT/9 && !shootButton.getIsClicked()){
			playerPos.y += HEIGHT/150;
			vel += HEIGHT/40;
			jumping = true;
			waiting=true;
		} else if (waiting && !Gdx.input.isTouched()) {
			waiting=false;
		} else if (Gdx.input.isTouched() && jumping && !waiting && !shootButton.getIsClicked()){
			playerPos.y += HEIGHT/150;
			vel=0;
			vel += HEIGHT/40;
			jumping=false;
			doubleJump=true;
		}
		else if (jumping || doubleJump){
			vel -= HEIGHT/825;
		}
	
	if ((jumping || doubleJump) && playerPos.y <= HEIGHT/10){
			jumping = false;
			doubleJump=false;
			vel = 0;
			playerPos.y += HEIGHT/150;
		}
	}
	
	
	boolean checkFire(){
		if(shootButton.isHit() && ammo > 0){
			bullets.add (new Bullet((int) (playerPos.y+ HEIGHT/10), batch, bulletTex, (int)WIDTH/15));
			ammo--;
		}
			return true;
	}
		
	void updateLinePos(){
		linePosition.x -= gameSpeed;
		if (linePosition.x < -WIDTH)
			linePosition.x = 0;
		backPos1.x -= gameSpeed;
		if (backPos1.x < -2 * WIDTH)
			backPos1.x = 2 * WIDTH;
		backPos2.x -= gameSpeed;
		if (backPos2.x < -2 * WIDTH)

			backPos2.x = 2 * WIDTH;	
	}
	
	public void show() {
		Texture.setEnforcePotImages(false);
		
		batch = new SpriteBatch();
		cloud = new Texture(Gdx.files.internal("Cloud.png"));
		roadLines = new Texture(Gdx.files.internal("RoadLines.png"));
		background = new Texture(Gdx.files.internal("back.png"));
		playerSheet = new Texture(Gdx.files.internal("sheet.png"));
		truck = new Texture(Gdx.files.internal("truck.png"));
		rockTex = new Texture(Gdx.files.internal("Rock.png"));
		sShipTex = new Texture(Gdx.files.internal("Spaceship.png"));
		bulletTex = new Texture(Gdx.files.internal("Bullet.png"));
		planeTex = new Texture(Gdx.files.internal("Plane.png"));
		crateTex = new Texture(Gdx.files.internal("Crate.png"));
		playerPos = new Vector2(WIDTH/10, HEIGHT/10);
		player = new Player(playerSheet, playerPos);
		cloudPosition = new Vector2(WIDTH, WIDTH/3);
		truckPosition = new Vector2(WIDTH, WIDTH/3);
		cloud1 = new Cloud ((int) (HEIGHT/2), batch, cloud, (int) WIDTH);
		cloud2 = new Cloud ((int) (HEIGHT/2 + HEIGHT/7), batch, cloud, (int) (WIDTH * 1.5));		
		font = new BitmapFont(Gdx.files.internal("text.fnt"), true);
		shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"), true);
		font.setScale(WIDTH / 900f, - WIDTH / 900f);
		shadow.setScale(WIDTH / 900f, - WIDTH / 900f);
		
		roadBack = new Texture(Gdx.files.internal("Volcano Background.png"));
		spaceBack = new Texture(Gdx.files.internal("GalacticSpace.png"));
		
		bullets = new ArrayList<Bullet>();
		trucks = new ArrayList <Truck>();
		planes = new ArrayList <Airplane>();
		crates = new ArrayList <Crate>();
		rocks = new ArrayList <Rock>();
		spaceShips = new ArrayList <Spaceship>();
		shootButton = new CButton((int)WIDTH/20, (int)HEIGHT*7/9, (int)HEIGHT/7, (int)HEIGHT/7, "ShootButton.png", batch);
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
	
	@Override
	
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

}
