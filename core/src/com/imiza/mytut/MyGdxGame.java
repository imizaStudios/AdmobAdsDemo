package com.imiza.mytut;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter
{

	final AdManager ads;
	SpriteBatch batch;
	Texture show,hide;

	Rectangle showRectangle,hideRectangle;
	Vector3 touchPoint;
	OrthographicCamera guiCam;

	public MyGdxGame(AdManager adMob)
	{
		this.ads=adMob;
	}

	@Override
	public void create () {
		batch=new SpriteBatch();
		guiCam = new OrthographicCamera(800, 1280);
		guiCam.position.set(800 / 2,1280 / 2, 0);
		touchPoint = new Vector3();
		show=new Texture("show.png");// 373*102
		hide=new Texture("hide.png");// 377*102
		showRectangle=new Rectangle(213.5f,300,373,102);
		hideRectangle=new Rectangle(211.5f,500,377,102);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batch.setProjectionMatrix(guiCam.combined);
		batch.begin();
		batch.draw(show,213.5f,300);
		batch.draw(hide,211.5f,500);
		batch.end();

		if ( Gdx.input.justTouched() ) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (showRectangle.contains(touchPoint.x, touchPoint.y))
			{
				ads.show();
			}
			if (hideRectangle.contains(touchPoint.x, touchPoint.y))
			{
				ads.hide();
			}
		}
	}
	
	@Override
	public void dispose () {

	}
}
