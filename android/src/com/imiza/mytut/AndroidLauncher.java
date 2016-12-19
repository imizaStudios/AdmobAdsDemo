package com.imiza.mytut;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.imiza.mytut.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {


	final AndroidLauncher context = this;

	final AdmobManager adMob;
	final MyGdxGame game;

	public AndroidLauncher()
	{
		adMob = new AdmobManager("Your Admob ad id here");
		game = new MyGdxGame(adMob);
	}

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		adMob.init( context );
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		View gameView = initializeForView( game, config );
		RelativeLayout layout = new RelativeLayout( this );
		layout.addView(gameView);
		layout.addView( adMob.adView, adMob.adParams );
		setContentView( layout );
	}
}
