package com.imiza.mytut;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by imiza on 18/12/16.
 */

public class AdmobManager implements AdManager
{

    private final int ADSHOW = 1;
    private final int ADHIDE = 0;
    private final String admobId;
    private final String TEST_DEVICE = "Ad Your Test Device id here";// get from log of android studio



    public AdView adView   = null;
    public RelativeLayout.LayoutParams adParams = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage( Message msg ) {
            switch ( msg.what ) {
                case ADSHOW:
                    adView.setVisibility( View.VISIBLE );
                    break;
                case ADHIDE:
                    adView.setVisibility( View.GONE );
                    break;
            }
        }
    };

    public AdmobManager( String id )
    {
        this.admobId = id;
    }

    public void init( Context context )
    {
        adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

//        adParams.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM ); Shows Ads on Left Bottom
//        adParams.addRule( RelativeLayout.ALIGN_PARENT_LEFT );

        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);// Shows Ads on Center Bottom
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

//        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); Shows Ads on Right Bottom
//        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

//        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL); Shows Ads on Center Top
//        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

//        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); Shows Ads on Top Right
//        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

//        adParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT); Shows Ads on Left  Top
//        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

        adView = new AdView( context );
        adView.setAdSize( AdSize.BANNER );
        adView.setAdUnitId( admobId );
        AdRequest.Builder requestBuilder = new AdRequest.Builder();
        requestBuilder.addTestDevice( TEST_DEVICE );
        adView.loadAd( requestBuilder.build() );
    }

    @Override
    public void show() {
        handler.sendEmptyMessage( ADSHOW );
    }

    @Override
    public void hide() {
        handler.sendEmptyMessage( ADHIDE );
    }
}
