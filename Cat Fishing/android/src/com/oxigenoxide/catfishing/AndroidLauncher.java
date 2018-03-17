package com.oxigenoxide.catfishing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements RewardedVideoAdListener, GPGS {
	final AndroidLauncher context = this;
	final AdMobImpl adMob;
	//final GPGSImpl gpgs;
	final MainClass game;
	public static InterstitialAd interstitialAd;
	public static RewardedVideoAd rewardedVideoAd;

	private GameHelper gameHelper;
	private final static int requestCode = 1;

	public AndroidLauncher(){
		adMob = new AdMobImpl("ca-app-pub-3518231835991100/9663896278");

		/*
		gpgs = new GPGSImpl();
		*/
		game = new MainClass(adMob, this);

	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener()
		{
			@Override
			public void onSignInFailed(){ }

			@Override
			public void onSignInSucceeded(){ }
		};

		gameHelper.setup(gameHelperListener);


		adMob.init( context );
		//gpgs.init( context );

		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId("ca-app-pub-3518231835991100/2993410673");
		interstitialAd.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				adMob.loadInterstitial();
			}
		});

		rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
		rewardedVideoAd.setRewardedVideoAdListener(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		View gameView = initializeForView( game, config );

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView( gameView );
		layout.addView( adMob.adView, adMob.adParams );

		setContentView( layout );

		/*
		RelativeLayout layout = new RelativeLayout(this);
		View gameView = initializeForView(new nunchakusmash(), config);
		layout.addView(gameView);
		adView = new AdView(this);

		adView.setAdListener(new AdListener() {

			@Override
			public void onAdLoaded() {
				int visibility = adView.getVisibility();
				adView.setVisibility(AdView.GONE);
				adView.setVisibility(visibility);
				System.out.println("Loaded an ad yay");
				Log.i(TAG, "Ad Loaded...");
			}
		});

		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-1235754856548433/2310516307");

		AdRequest.Builder builder = new AdRequest.Builder();
		//builder.addTestDevice("");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layout.addView(adView, adParams);
		adView.loadAd(builder.build());

		setContentView(layout);

		showScore();

*/
	}

	@Override
	public void connect() {
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void disconnect() {
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public boolean isConnected() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void signOut() {

	}

	@Override
	public void unlockAchievement(int n) {

	}

	@Override
	public void unlockIncrementAchievement(int n, int count) {

	}

	@Override
	public void showAchievements() {

	}

	@Override
	public void submitScore(long score) {
		if (isConnected() == true)
		{
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscores), score);
		}
	}

	@Override
	public void showLeaderboard() {
		if (isConnected() == true)
		{
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscores)), requestCode);
		}
		else
		{
			connect();
		}
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void rateGame()
	{
		String str = "https://play.google.com/store/apps/details?id=com.oxigenoxide.catfishing";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}


	/*
	void keepScreenOn(){
		runOnUiThread( new Runnable() {
			@Override
			public void run(){
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			}
		} );
	}

	void keepScreenOff(){
		runOnUiThread( new Runnable() {
			@Override
			public void run(){
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			}
		} );
	}
	*/


	/*
	@Override
	protected void onStart(){
		super.onStart();

		// Âî âðåìÿ ñòàðòà ïðèëîæåíèÿ, ïîäêëþ÷àåìñÿ ê GPGS
		// Òàê ðåêîìåíäóåò äåëàòü GOOGLE
		if(gpgs != null) gpgs.connect();

	}

	@Override
	protected void onStop(){
		super.onStop();
		if(gpgs != null) gpgs.disconnect();
	}

	@Override
	protected void onActivityResult(int request, int response, Intent intent){
		super.onActivityResult( request, response, intent );
		if(gpgs != null) gpgs.onActivityResult( request, response, intent );
	}
	*/


		/*
	@Override
	public void signIn(){
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("com.smooboo.nunchaku.AndroidLauncher", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut(){
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("com.smooboo.nunchaku.AndroidLauncher", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame(){
		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	//@Override
	//public void unlockAchievement(){
	//	Games.Achievements.unlock(gameHelper.getApiClient(),
	//			getString(R.string.achievement_dum_dum));
	//}

	@Override
	public void submitScore(long highScore){
		if (isSignedIn())
		{
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscores), highScore);
		}
	}

	//@Override
	//public void showAchievement(){
	//	if (isSignedIn())
	//	{
	//		startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient(),
	//				getString(R.string.achievement_dum_dum)), requestCode);
	//	}
	//	else
	//	{
	//		signIn();
	//	}
	//}


	@Override
	public void showScore(){
		if (isSignedIn())
		{
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscores)), requestCode);
		}
		else
		{
			signIn();
		}
	}

	@Override
	public boolean isSignedIn(){
		return gameHelper.isSignedIn();

	}

*/

	@Override
	public void onRewardedVideoAdLoaded() {
		MainClass.adLoaded = true;
	}

	@Override
	public void onRewardedVideoAdOpened() {

	}

	@Override
	public void onRewardedVideoStarted() {

	}

	@Override
	public void onRewardedVideoAdClosed() {
		adMob.loadAd();
	}

	@Override
	public void onRewarded(RewardItem rewardItem) {
		MainClass.showCoinWindow = true;
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {
		adMob.loadAd();
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {

	}
}
