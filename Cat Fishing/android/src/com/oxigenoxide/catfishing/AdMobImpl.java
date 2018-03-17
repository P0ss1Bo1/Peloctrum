package com.oxigenoxide.catfishing;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


/**
 * Created by Andrey (cb) Mikheev
 * GPGS
 * 26.09.2016
 */
public class AdMobImpl implements AdMob {

    private final int SHOW_AD = 1;
    private final int HIDE_AD = 0;
    private final int SHOW_VIDAD = 2;
    private final int LOAD_VIDAD = 3;
    private final int SHOW_INTER = 4;
    private final int LOAD_INTER = 5;

    private final String id;

    //private final String TEST_DEVICE1 = "073D9C239F33183A0076A4EE5AF62B70";

    public AdView adView = null;
    public RelativeLayout.LayoutParams adParams = null;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage( Message msg ) {
            switch ( msg.what ) {
                case SHOW_AD:
                    adView.setVisibility( View.VISIBLE );
                    break;
                case HIDE_AD:
                    adView.setVisibility( View.GONE );
                    break;
                case SHOW_VIDAD:
                    if(AndroidLauncher.rewardedVideoAd.isLoaded())
                        AndroidLauncher.rewardedVideoAd.show();
                    break;
                case LOAD_VIDAD:
                    AndroidLauncher.rewardedVideoAd.loadAd("ca-app-pub-3518231835991100/1575553073", new AdRequest.Builder().build());
                    break;
                case SHOW_INTER:
                    AndroidLauncher.interstitialAd.show();
                    break;
                case LOAD_INTER:
                    AdRequest request = new AdRequest.Builder()
                            .build();
                    AndroidLauncher.interstitialAd.loadAd(request);
                    break;
            }
        }
    };

    public AdMobImpl( String id ) {
        this.id = id;
    }
    public void init( Context context ) {



        adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //adParams.addRule( RelativeLayout.ALIGN_PARENT_TOP );
        adParams.addRule( RelativeLayout.ALIGN_PARENT_TOP);

        adView = new AdView( context );
        adView.setAdSize( AdSize.SMART_BANNER );
        adView.setAdUnitId( id );
        AdRequest.Builder requestBuilder = new AdRequest.Builder();
        //requestBuilder.addTestDevice( TEST_DEVICE1 );
        adView.loadAd( requestBuilder.build() );
        adView.setVisibility(View.INVISIBLE);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                handler.sendEmptyMessage( SHOW_AD );
            }
        });
    }

    @Override
    public void show() {
        handler.sendEmptyMessage( SHOW_AD );
    }

    @Override
    public void hide() {
        handler.sendEmptyMessage( HIDE_AD );
    }

    @Override
    public void showAd() {
        handler.sendEmptyMessage( SHOW_VIDAD );
    }

    @Override
    public void loadAd() {
        handler.sendEmptyMessage( LOAD_VIDAD );
    }

    @Override
    public void showInterstitial() {handler.sendEmptyMessage( SHOW_INTER );
    }
    public void loadInterstitial() {handler.sendEmptyMessage( LOAD_INTER );
    }
}
