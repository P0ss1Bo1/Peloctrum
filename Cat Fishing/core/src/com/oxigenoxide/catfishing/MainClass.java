package com.oxigenoxide.catfishing;

        import com.badlogic.gdx.ApplicationAdapter;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.Preferences;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
        import com.badlogic.gdx.math.MathUtils;
        import com.badlogic.gdx.math.Vector2;
        import com.badlogic.gdx.math.Vector3;
        import com.badlogic.gdx.physics.box2d.Body;
        import com.badlogic.gdx.physics.box2d.BodyDef;
        import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
        import com.badlogic.gdx.physics.box2d.ChainShape;
        import com.badlogic.gdx.physics.box2d.Fixture;
        import com.badlogic.gdx.physics.box2d.FixtureDef;
        import com.badlogic.gdx.physics.box2d.Joint;
        import com.badlogic.gdx.physics.box2d.PolygonShape;
        import com.badlogic.gdx.physics.box2d.QueryCallback;
        import com.badlogic.gdx.physics.box2d.World;
        import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
        import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
        import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
        import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
        import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
        import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

        import java.util.ArrayList;

public class MainClass extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    OrthographicCamera cam;

    boolean takeAngleOffScreen, letFishFall, changeFishInit;
    public static int coinsReward;
    public static float NewWidth = 1920 / 250f;
    public static float NewHeight = 1080 / 250f;
    Sound soundAlarm;
    public static World world;
    public static Body[] ropeSegments;
    Box2DDebugRenderer b2dr;
    MouseJointDef mouseJointDef;
    MouseJoint mouseJoint;
    Body ground;
    com.oxigenoxide.catfishing.Cat cat;
    ShapeRenderer sr;
    TextureRegion number;
    TextureRegion number_second;
    TextureRegion highscoreNumber;
    TextureRegion highscoreNumber_second;
    TextureRegion highscoreNumber_third;
    TextureRegion timeBar;
    TextureRegion backGround;
    TextureRegion buttonBaseTex;
    TextureRegion scoreboardTex;
    TextureRegion unlockedBarTex;
    TextureRegion shopCanvas;
    TextureRegion ratewindow;
    TextureRegion[] timeDot;
    TextureRegion[] numbers;
    public static TextureRegion[] button;
    Vector2 shopPos;
    Vector2 scoreboardPos;
    Vector2 timeDotPos;
    Vector2 screenShake;
    Vector2 camposition;
    Vector2 unlockedBarPos;
    Vector2 touchStart;
    Vector2 anglePos;
    Vector2 handPos;
    public static Vector2[] buttonPos;
    Vector2[] shopOffersPos;
    Sound soundPick;
    Sound soundUnlock;
    int highscore;
    boolean picked;
    short groundMask = 0x0001;
    short ropeMask = 0x0002;
    short wallMask = 0x0004;
    short fishMask = 0x0008;
    float[] shopOfferSize;
    public static int ballType = 0;
    TextureRegion lockTex;

    public static int unlocks;
    Preferences prefs;
    TextureRegion[] medal;
    TextureRegion unlockItem;
    boolean showShop;

    private double currentTime;
    private double accumulator;
    TextureRegion tapForNextFishText;

TextureRegion plusCharacter;
    public static TextureRegion coinTex;
    boolean showTutorial;

    boolean fishEaten;
    boolean ropeUp;

    FixtureDef fixtureDef;
    int handType;

    boolean canTakeFish = true;

    Vector2 numberCoinsCollectedPos;
    Vector2 coinsEarnedPos;
    TextureRegion[] coinAni;
    int[] price;
    int priceCurrentItem;
    Vector2 pricePos;
    Vector2 coinDisplayPos;
    TextureRegion pressForFishHand;
    TextureRegion pressForFishButton;
    Vector2 pressFishInfoPos;
    MouseJoint ropeControlJoint;
    TextureRegion splash;
    TextureRegion[] splashTex;
    Vector2 splashPos;
    boolean showSplash;
    TextureRegion[] jumpExplosionTex;
    TextureRegion jumpExplosion;
    public static boolean showJumpExplosion;
    Vector2 jumpExplosionPos;
    int[] prize;
    ArrayList<Particle> particles;
    ArrayList<Particle> particlesClean;
    TextureRegion locked_text;
    boolean[] unlocked;
    float scissorExtra;
    Sound getCoins;
    int[] shopItem;
    public static TextureAtlas atlas;
    public static boolean adLoaded;
    int priceNextFish;
    boolean canUnlockFish;


    TextureRegion coinwindow;

    Sound soundBreak;
    Sound click0;
    Sound click1;

    TextureRegion arrow;
    TextureRegion catchtext;
    TextureRegion catchtextflip;
    TextureRegion taptext;
    TextureRegion timetext;
    TextureRegion arrowDown;
    TextureRegion timeuptext;
    TextureRegion newfishtext;
    Vector3 rbg;
    Vector3 rbg_;
    Vector3 rbgOld;
    boolean showRateWindow;

    boolean[] buttonVisible;
    boolean gameReady;


    int displayCoins;
    boolean newHighscore;

    boolean fishOnRight;
    int gamesPlayed;
    TextureRegion selected;

    boolean rbgSwitch;
    public static boolean showCoinWindow;
    int frames;



    final AdMob adMob;
    final GPGS gpgs;

	public MainClass(AdMob adMob, GPGS gpgs) {

		this.adMob = adMob;
		this.gpgs = gpgs;

	}



    /// Config ///
    public static int anglePosOffset = 65;
    public static int barDownMax = 65;
    public static int floor = 22;
    public static int numberHeight = 70;
    public static int timeBarHeight = 2;
    public static int shopDownMax = 73;
    public static int fishType = 1;
    public static float step = 1f / 60;
    public static int coins = 0;
    public static boolean portrait = true;
    public static final int menuFish = 5;
    /// Config ///

    @Override
    public void create () {
        Gdx.input.setInputProcessor(this);

        gpgs.connect();
        adMob.hide();
        adMob.loadAd();
        adMob.loadInterstitial();

        rbg = new Vector3(1,1,1);
        rbg_ = new Vector3(1,1,1);
        rbgDiff = new Vector3(0,0,0);
        rbgOld = new Vector3(1,1,1);
        atlas = new TextureAtlas("Images/Images.atlas");
        fishJoints = new ArrayList<Joint>();
        fishBodies = new ArrayList<Body>();
        particlesClean = new ArrayList<Particle>();


        //Assets.load();
        //Assets.manager.update();
        //while(!Assets.manager.update())
        //    System.out.println(Assets.manager.getProgress() * 100 + "% assets loaded");



        //System.out.println("width: " + ((float)Gdx.graphics.getHeight() * (9f/16f)) / (float)Gdx.graphics.getWidth());
        if(portrait) {
            numberHeight = 200;
            floor = 28;
            NewWidth = 1080 / (((float)Gdx.graphics.getHeight() * (9f/16f)) / (float)Gdx.graphics.getWidth()) / 250f;
            NewHeight = 1920 / 250f;
            timeBarHeight = 5;
            barDownMax = 130;
        }
        scissorExtra = (Gdx.graphics.getWidth() - Gdx.graphics.getHeight() * (9f/16f)) / (Gdx.graphics.getHeight() * (9f/16f));

        particles = new ArrayList<Particle>();

        ratewindow = atlas.findRegion("ratewindow");
        coinwindow = atlas.findRegion("coinswindow");
        selected = atlas.findRegion("selected");
        catchtext = atlas.findRegion("catchtext");
        catchtextflip = atlas.findRegion("catchtextflip");
        arrow = atlas.findRegion("arrowpointing");
        taptext = atlas.findRegion("taptext");
        timetext = atlas.findRegion("timetext");
        arrowDown = atlas.findRegion("ArrowDown");
        timeuptext = atlas.findRegion("timeuptext");
        newfishtext = atlas.findRegion("newfishtext");

        coinAni = new TextureRegion[4];
        coinAni[0] = atlas.findRegion("Coin");
        coinAni[1] = atlas.findRegion("coinAnimation", 1);
        coinAni[2] = atlas.findRegion("coinAnimation", 2);
        coinAni[3] = atlas.findRegion("coinAnimation", 3);
        smallNumbers = new TextureRegion[10];
        smallNumbers[0] = atlas.findRegion("smallNumber", 0);
        smallNumbers[1] = atlas.findRegion("smallNumber", 1);
        smallNumbers[2] = atlas.findRegion("smallNumber", 2);
        smallNumbers[3] = atlas.findRegion("smallNumber", 3);
        smallNumbers[4] = atlas.findRegion("smallNumber", 4);
        smallNumbers[5] = atlas.findRegion("smallNumber", 5);
        smallNumbers[6] = atlas.findRegion("smallNumber", 6);
        smallNumbers[7] = atlas.findRegion("smallNumber", 7);
        smallNumbers[8] = atlas.findRegion("smallNumber", 8);
        smallNumbers[9] = atlas.findRegion("smallNumber", 9);

        soundUnlock = Gdx.audio.newSound(Gdx.files.internal("Sounds/unlock.wav"));
        getCoins = Gdx.audio.newSound(Gdx.files.internal("Sounds/coins.wav"));
        click0 = Gdx.audio.newSound(Gdx.files.internal("Sounds/click.wav"));
        click1 = Gdx.audio.newSound(Gdx.files.internal("Sounds/click2.wav"));

        tapForNextFishText = atlas.findRegion("tapForNextFish");

        pressForFishHand = atlas.findRegion("hand", 1);
        pressForFishButton = atlas.findRegion("fishPress", 0);

        touchDiff = new Vector2(0,0);
        touchStart = new Vector2(0,0);
        pressFishInfoPos = new Vector2(NewWidth * 25f / 2, NewHeight * 25f / 2);

        hand = new TextureRegion[2];
        hand[0] = atlas.findRegion("hand", 2);
        hand[1] = atlas.findRegion("hand", 1);
        handPos = new Vector2(0,0);

        splashTex = new TextureRegion[5];
        splashTex[0] = atlas.findRegion("Splash", 0);
        splashTex[1] = atlas.findRegion("Splash", 1);
        splashTex[2] = atlas.findRegion("Splash", 2);
        splashTex[3] = atlas.findRegion("Splash", 3);
        splashTex[4] = atlas.findRegion("Splash", 4);

        jumpExplosionTex = new TextureRegion[6];
        jumpExplosionTex[0] = atlas.findRegion("jumpExplosion", 0);
        jumpExplosionTex[1] = atlas.findRegion("jumpExplosion", 1);
        jumpExplosionTex[2] = atlas.findRegion("jumpExplosion", 2);
        jumpExplosionTex[3] = atlas.findRegion("jumpExplosion", 3);
        jumpExplosionTex[4] = atlas.findRegion("jumpExplosion", 4);
        jumpExplosionTex[5] = atlas.findRegion("jumpExplosion", 5);

        jumpExplosion = jumpExplosionTex[0];

        jumpExplosionPos = new Vector2(0,0);

        splashPos = new Vector2(0,0);

        locked_text = atlas.findRegion("locked_text");

        lockTex = atlas.findRegion("Lock");
        unlockedBarTex = atlas.findRegion("UnlockedBar");
        unlockedBarPos = new Vector2((int)(NewWidth * 25 / 2 - unlockedBarTex.getRegionWidth() / 2), NewHeight * 25 + 100);
        shopCanvas = atlas.findRegion("shopCanvas");
        shopPos = new Vector2(NewWidth * 25 / 2, NewHeight * 25 + 50);
        soundAlarm = Gdx.audio.newSound(Gdx.files.internal("Sounds/alarm.wav"));
        medal = new TextureRegion[3];
        medal[0] = atlas.findRegion("medal_bronze");
        medal[1] = atlas.findRegion("medal_silver");
        medal[2] = atlas.findRegion("medal_gold");

        prefs = Gdx.app.getPreferences("nothing to see here");
        highscore = prefs.getInteger("highscore");
        coins = prefs.getInteger("coins");
        unlocks = prefs.getInteger("unlocks");
        gamesPlayed = prefs.getInteger("gamesPlayed");
        fishType = prefs.getInteger("selectedFish") + 1;

        displayCoins = coins;

        coinTex = atlas.findRegion("Coin");
        plusCharacter = atlas.findRegion("PlusCharacter");

        coinsEarnedPos = new Vector2(NewWidth / 2 * 25f, NewHeight / 2 * 25f);
        pricePos = new Vector2(NewWidth / 2 * 25f + 1, NewHeight / 2 * 25f - 8);
        coinDisplayPos = new Vector2(NewWidth / 2 * 25f + 1, 7);

        buttonBaseTex = atlas.findRegion("buttonBase");
        scoreboardTex = atlas.findRegion("scoreboard");
        scoreboardPos = new Vector2((int)(NewWidth * 25 / 2) - scoreboardTex.getRegionWidth() / 2, -scoreboardTex.getRegionHeight() - 5);
        numberCoinsCollectedPos = new Vector2(scoreboardPos.x + 73, 72);
        backGround = atlas.findRegion("background");

        numbers = new TextureRegion[10];
        numbers[0] = atlas.findRegion("0");
        numbers[1] = atlas.findRegion("1");
        numbers[2] = atlas.findRegion("2");
        numbers[3] = atlas.findRegion("3");
        numbers[4] = atlas.findRegion("4");
        numbers[5] = atlas.findRegion("5");
        numbers[6] = atlas.findRegion("6");
        numbers[7] = atlas.findRegion("7");
        numbers[8] = atlas.findRegion("8");
        numbers[9] = atlas.findRegion("9");
        number = numbers[0];

        timeBar = atlas.findRegion("timeBar");

        timeDot = new TextureRegion[2];
        timeDot[0] = atlas.findRegion("timeDot");
        timeDot[1] = atlas.findRegion("timeDot_red");
        timeDotPos = new Vector2(NewWidth * 25 / 2 + timeBar.getRegionWidth() / 2 - 5, timeBarHeight + 4);

        soundPick = Gdx.audio.newSound(Gdx.files.internal("Sounds/grab.wav"));
        soundBreak = Gdx.audio.newSound(Gdx.files.internal("Sounds/break.wav"));
        soundGameover = Gdx.audio.newSound(Gdx.files.internal("Sounds/gameover.wav"));


                screenShake = new Vector2(0,0);

        int buttonAmount = 13;

        buttonVisible = new boolean[buttonAmount];
        for(int i = 0; i < buttonAmount; i++) { buttonVisible[i] = true; }
        buttonVisible[11] = false;
        buttonVisible[12] = false;

        button = new TextureRegion[buttonAmount];
        button[0] = atlas.findRegion("Button_Play");
        button[1] = atlas.findRegion("Button_WatchAd");
        button[2] = atlas.findRegion("Button_Restart");
        button[3] = atlas.findRegion("Button_Shop");
        button[4] = atlas.findRegion("Button_ArrowLeft");
        button[5] = atlas.findRegion("Button_ArrowRight");
        button[6] = atlas.findRegion("Button_Select");
        button[7] = atlas.findRegion("Button_Close");
        button[8] = atlas.findRegion("Button_Leaderboards");
        button[9] = atlas.findRegion("Button_Shop");
        button[10] = atlas.findRegion("okbutton");
        button[11] = atlas.findRegion("Button_Yes");
        button[12] = atlas.findRegion("Button_No");


        buttonPos = new Vector2[buttonAmount];
        buttonPos[0] = new Vector2((int)((NewWidth * 25) / 2f - button[0].getRegionWidth() / 2), 3);
        buttonPos[1] = new Vector2(shopPos.x - button[1].getRegionWidth() / 2f, 0);
        buttonPos[2] = new Vector2(scoreboardPos.x + scoreboardTex.getRegionWidth() / 2 - button[2].getRegionWidth() - 1, 0);
        buttonPos[3] = new Vector2(buttonPos[0].x + button[0].getRegionWidth(), 4);
        buttonPos[4] = new Vector2(61,0);
        buttonPos[5] = new Vector2(113,0);
        buttonPos[6] = new Vector2(79,0);
        buttonPos[7] = new Vector2(shopPos.x + 50,0);
        buttonPos[8] = new Vector2(buttonPos[0].x - button[8].getRegionWidth(),4);
        buttonPos[9] = new Vector2(scoreboardPos.x + scoreboardTex.getRegionWidth() / 2 + 1, 0);
        buttonPos[10] = new Vector2(NewWidth * 25f / 2f - button[10].getRegionWidth() / 2f, NewHeight * 25f / 2f - 20);
        buttonPos[11] = new Vector2(NewWidth * 25f / 2f, NewHeight * 25f / 2f - 25);
        buttonPos[12] = new Vector2(NewWidth * 25f / 2f - button[10].getRegionWidth() + 3, NewHeight * 25f / 2f - 25);

        if(portrait) {
            buttonPos[4] = new Vector2(shopPos.x - shopCanvas.getRegionWidth() / 2,0);
            buttonPos[6] = new Vector2(shopPos.x - shopCanvas.getRegionWidth() / 2 + 18,0);
            buttonPos[5] = new Vector2(shopPos.x - shopCanvas.getRegionWidth() / 2 + 52,0);
            buttonPos[7] = new Vector2(shopPos.x + 20,0);
        }

        int offers = 7;

        shopItem = new int[] {0,3,1,5,2,4,6};
        shopItem[0] = 0; //tuna
        shopItem[1] = 3; //nemo
        shopItem[2] = 1; //blob
        shopItem[3] = 5; //dolp
        shopItem[4] = 2; //eeel
        shopItem[5] = 4; //sail
        shopItem[6] = 6; //whal

        prize = new int[offers];
        prize[0] = 1;
        prize[1] = 2;
        prize[2] = 7;
        prize[3] = 1;
        prize[4] = 4;
        prize[5] = 3;
        prize[6] = 4;

        shopOffers = new TextureRegion[offers];
        shopOffers[0] = atlas.findRegion("fishIcon", 0);
        shopOffers[1] = atlas.findRegion("fishIcon", 1);
        shopOffers[2] = atlas.findRegion("fishIcon", 2);
        shopOffers[3] = atlas.findRegion("fishIcon", 3);
        shopOffers[4] = atlas.findRegion("fishIcon", 4);
        shopOffers[5] = atlas.findRegion("fishIcon", 5);
        shopOffers[6] = atlas.findRegion("fishIcon", 6);

        shopOffersPos = new Vector2[offers];
        shopOffersPos[0] = new Vector2(NewWidth * 25 / 2, 60);
        shopOffersPos[1] = new Vector2(NewWidth * 25 / 2 + 16*1, 60);
        shopOffersPos[2] = new Vector2(NewWidth * 25 / 2 + 16*2, 60);
        shopOffersPos[3] = new Vector2(NewWidth * 25 / 2 + 16*3, 60);
        shopOffersPos[4] = new Vector2(NewWidth * 25 / 2 + 16*4, 60);
        shopOffersPos[5] = new Vector2(NewWidth * 25 / 2 + 16*5, 60);
        shopOffersPos[6] = new Vector2(NewWidth * 25 / 2 + 16*6, 60);


        price = new int[offers];
        price[0] = 0;
        price[1] = 10;
        price[2] = 50;
        price[3] = 5;
        price[4] = 66;
        price[5] = 25;
        price[6] = 99;

        priceCurrentItem = price[0];

        unlocked = new boolean[offers];
        unlocked[0] = true;

        for(int i = 0; i < unlocked.length; i++)
            unlocked[i] = prefs.getBoolean("unlock" + String.valueOf(i));


        //for(int i = 0; i < unlocked.length; i++){
        //    unlocked[i] = false;
        //}

        unlocked[0] = true;

        shopOfferSize = new float[offers];
        shopOfferSize[0] = 1;
        for(int i = 1; i < offers; i++){
            shopOfferSize[i] = 0.4f;
        }
        //shopOfferSize[1] = 0.4f;

        sr = new ShapeRenderer();
        //rope = new Texture("Images/Rope.png");

        b2dr = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -9.81f * 2), true);
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, NewWidth, NewHeight);

        cat = new Cat();

        if(!portrait)
            ropeSegments = createFishingRod(35);
        else
            ropeSegments = createFishingRod(30);


        newFish(menuFish);
        cat.fishTakeBite(100, 0, menuFish);

        anglePos = new Vector2(NewWidth / 2, NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength));

        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(-5,floor / 25f - 100);

        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(0, 0), new Vector2(20, 0)});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.filter.categoryBits = groundMask;
        fixtureDef.filter.maskBits = (short)(ropeMask | fishMask);
        fixtureDef.restitution = 0;
        fixtureDef.friction = 100;

        ground = world.createBody(groundDef);
        ground.createFixture(fixtureDef);

        ChainShape leftWall = new ChainShape();
        leftWall.createChain(new Vector2[]{new Vector2(0, -10), new Vector2(0, NewHeight*3)});
        fixtureDef.filter.categoryBits = wallMask;
        fixtureDef.filter.maskBits = (short)(ropeMask | fishMask);
        fixtureDef.shape = leftWall;
        fixtureDef.restitution = 2;
        groundDef.position.set(0,0);
        Body leftWallBody = world.createBody(groundDef);
        leftWallBody.createFixture(fixtureDef);

        groundDef.position.set(NewWidth,0);
        Body rightWallBody = world.createBody(groundDef);
        rightWallBody.createFixture(fixtureDef);

        mouseJointDef = new MouseJointDef();
        mouseJointDef.collideConnected = true;
        mouseJointDef.bodyA = ground;
        mouseJointDef.maxForce = 100;

        //unlockItem = cat.ballTex[1];
        if(!portrait)
            specialNumber = Gdx.graphics.getHeight() / 192 * 2;
        else
            specialNumber = Gdx.graphics.getHeight() / 192f;

        camposition = new Vector2(cam.position.x, cam.position.y);

        /*
        MouseJointDef mDef = new MouseJointDef();
        mDef.bodyA = ground;
        mDef.bodyB = ropeSegments[0];
        mDef.maxForce = 10000.0f;
        mDef.target.set(ropeSegments[0].getPosition());
        System.out.println("test: " + ropeControlJoint);
        ropeControlJoint = (MouseJoint)world.createJoint(mDef);
        ropeControlJoint.setTarget(anglePos);
        */
        ropeControlAdd = new Vector2(0,0);
        catVelocityAfterFall = new Vector2(0,0);
        System.out.println(NewWidth * 25);
        getCoins.play(0.1f);
    }


    boolean showTutorial2;
    public static int tapX, tapY;
    int timeTillNewPoint = 50;
    float AngleDif;
    float count;
    public static int buttonPressed;
    public static boolean takeAngleDown;
    float specialNumber;
    public static boolean playState;
    int playStateCount;
    int takeAngleDownTrigger;
    int timeAfterLetGoCount;
    int score;
    float numberShake;
    float numberShakeTime;
    int timeTillGameOver = 200;
    float tillGameOverCount = timeTillGameOver;
    boolean gameOverTrigger;
    Sound soundGameover;
    boolean gameOver;
    float b5o;
    float medalWidth;
    float timeTillColorChange;
    int medalScore;
    int dotColor = 0;
    boolean shopOpenTrigger;
    public static boolean shopOpen;
    boolean menuButtonsDown;
    boolean menuButtonsUp;
    float magic;
    int buttonMoveTime;
    boolean shopUp, shopDown;
    boolean barUp, barDown;
    boolean playStateTrigger;
    boolean closeShop;
    TextureRegion[] shopOffers;
    int middle;
    int pageTurn;
    int pageTurnTime;
    int pageCurrent;
    boolean unlockEvent;
    int unlockEventTime;
    float unlockItemOffset;
    boolean angleUp;
    boolean gameOverOnceCheck;
    TextureRegion[] hand;
    int tutorialLoop;

    float handRandomDirection = 0;
    int handRightOrLeft;
    float handSpeed = 2;
    boolean tutorialFinished;
    float timeSpeed = 1;
    boolean showTextNextFish;

    boolean showEarnedCoins;

    float coinAniTime;
    int angleUpDelay;
    int coinsEarned = 1;

    int fishTypeAniCount;
    boolean showFishPressInfo;
    Vector2 ropeControlAdd;
    boolean ropePluck;

    int ropePluckCount;
    int showSplashCount;
    int jumpExplosionCount;
    int showEarnedCoinsCount;
    float showEarnedCoinsOffset;
    TextureRegion number_third;
    float ropePluckDelay;
    int ropeSegmentsVisible;
    int angleUpCount;
    boolean speedUpTime;
    float taptextOffset;
    int taptextOffsetUpDown = 1;
    float timeDotShake;
    float timeDotShakeTime;
    boolean tutorial2Finished;
    boolean numbersDown;
    boolean dontShowTutorial = false;
    boolean Freeze;
    Vector3 rbgDiff;
    float colorSat = 0.85f;
    float unlockfishtextOffset;
    int unlockfishtextOffsetTime;

    //public static RopeJoint[] takeFishJoint;
    void update(){

        unlockfishtextOffsetTime++;
        int l = 0;
        if(unlockfishtextOffsetTime > 15){
            l = unlockfishtextOffsetTime - 15;
        }
        unlockfishtextOffset = (unlockfishtextOffsetTime - l * 2)/ (15f / 2f);

        if(unlockfishtextOffsetTime >= 30)
            unlockfishtextOffsetTime = 0;





        boolean tempBool_ = false;
        for(int i = 0; i < unlocked.length; i++) {
            if (unlocked[shopItem[i]] == false && !tempBool_) {
                priceNextFish = price[shopItem[i]];
                tempBool_ = true;
            }
            if(tempBool_ == false){
                priceNextFish = -1;
            }

        }

        if(coins >= priceNextFish && priceNextFish != -1)
            canUnlockFish = true;
        else
            canUnlockFish = false;



        if(rbgSwitch){
            rbgOld.x = rbg_.x;
            rbgOld.y = rbg_.y;
            rbgOld.z = rbg_.z;

            if(rbg_.x == 1 && rbg_.y == 1 && rbg_.z == 1){
                rbg_.set(colorSat,colorSat,1);
            }
            else if(rbg_.x == 1 && rbg_.y == colorSat && rbg_.z == colorSat){
                rbg_.set(1,colorSat,1);
            }
            else if(rbg_.x == 1 && rbg_.y == colorSat && rbg_.z == 1){
                rbg_.set(colorSat,colorSat,1);
            }
            else if(rbg_.x == colorSat && rbg_.y == colorSat && rbg_.z == 1){
                rbg_.set(colorSat,1,1);
            }
            else if(rbg_.x == colorSat && rbg_.y == 1 && rbg_.z == 1){
                rbg_.set(colorSat,1,colorSat);
            }
            else if(rbg_.x == colorSat && rbg_.y == 1 && rbg_.z == colorSat){
                rbg_.set(1,1,colorSat);
            }
            else if(rbg_.x == 1 && rbg_.y == 1 && rbg_.z == colorSat){
                //rbg_.set(1,colorSat,colorSat);
                rbg_.set(1,colorSat,1);
            }

            rbgSwitch = false;
            rbgDiff.set(rbg_.x - rbgOld.x , rbg_.y - rbgOld.y, rbg_.z - rbgOld.z);
        }

        rbgDiff.set(rbg_.x - rbg.x, rbg_.y - rbg.y, rbg_.z - rbg.z);

        //if(rbg.x + rbgDiff.x >= rbg_.x && rbg.x + rbgDiff.x <= 1f)
            rbg.x += rbgDiff.x / 30f;
        //if(rbg.y + rbgDiff.y >= rbg_.y && rbg.y + rbgDiff.y <= 1f)
            rbg.y += rbgDiff.y / 30f;
        //if(rbg.z + rbgDiff.z >= rbg_.z && rbg.z + rbgDiff.z <= 1f)
            rbg.z += rbgDiff.z / 30f;






        if(gamesPlayed > 2){
            dontShowTutorial = true;
        }
        if(dontShowTutorial && !tutorial2Finished && playState){
            tutorial2Finished = true;
            numbersDown = true;
        }
        taptextOffset += 0.1 * taptextOffsetUpDown;
        if(taptextOffset >= 1){
            taptextOffsetUpDown = -1;
        }if(taptextOffset <= -1){
            taptextOffsetUpDown = 1;
        }


        float pos = fishBodies.get(fishBodies.size() - 1).getPosition().x;
        if(pos > NewWidth / 2f){
            fishOnRight = true;
        }
        else fishOnRight = false;

        int tempPositivity;
        if(coinsToAdd != 0) {
            tempPositivity = (int)(coinsToAdd / Math.abs(coinsToAdd));
            coinsToAdd-=0.25f * tempPositivity;
            if(coinsToAdd % 1f == 0) {

                displayCoins += tempPositivity;

                if(displayCoins < 0){
                    displayCoins = 0;
                }
            }
        }


        if(coinsReward > 0){
            addCoins(coinsReward);
            coinsReward = 0;
        }


        if(showFishPressInfo){
            pressForFishHand = hand[Math.round(fishTypeAniCount / 20)];
            if(fishTypeAniCount < 20)
                pressForFishButton = atlas.findRegion("fishPress", 0);
            if(fishTypeAniCount >= 20)
                pressForFishButton = atlas.findRegion("fishPress", 1);
            fishTypeAniCount++;
            if(fishTypeAniCount >= 40)
                fishTypeAniCount = 0;
        }

        if(unlocked[shopItem[pageCurrent]] && button[6] != atlas.findRegion("Button_Select")){
            button[6] = atlas.findRegion("Button_Select");
        }
        else if(!unlocked[shopItem[pageCurrent]] && button[6] != atlas.findRegion("Button_Buy"))
            button[6] = atlas.findRegion("Button_Buy");

        if(adLoaded && button[1] != atlas.findRegion("Button_WatchAd")){
            button[1] = atlas.findRegion("Button_WatchAd");
        }
        else if(!adLoaded && button[1] != atlas.findRegion("Button_WatchAd_wait"))
            button[1] = atlas.findRegion("Button_WatchAd_wait");

        priceCurrentItem = price[shopItem[pageCurrent]];

        if(coinAniTime >= 40){
            coinTex = coinAni[(int)Math.floor((coinAniTime - 40) / 5f)];
        }
        coinAniTime++;
        if(coinAniTime >= 60) {
            coinTex = coinAni[0];
            coinAniTime = 0;
        }


        if(cat.segmentsEaten >= fishLength){
            if(showEarnedCoinsCount < 1) {
                showEarnedCoins = true;
                coinsEarned = prize[fishType - 1];
                particle_summon(NewWidth * 25 / 2f + 3, NewHeight * 25 / 2f,0,6,1,coinsEarned,7,0,true);
            }
            takeAngleOffScreen = true;
            if(!(anglePos.y < NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength) * 2 - anglePosOffset / 25f + ropeSegments.length * height)){
                showEarnedCoins = false;
                angleUpDelay = 0;
                showFishPressInfo = true;
                if(Gdx.input.justTouched()){
                    takeAngleOffScreen = false;
                    letFishFall = true;
                    showEarnedCoinsCount = 0;
                    coinsCollected += coinsEarned;
                    showFishPressInfo = false;
                    timeSpeed = 0.01f;
                    speedUpTime = true;
                    newFish(fishType);
                    cat.fishTakeBite(0,0, fishType);
                    fishBodies.get(fishBodies.size() - 1).setTransform(fishBodies.get(fishBodies.size() - 1).getPosition().x, NewHeight + 10 / 25f, fishBodies.get(fishBodies.size() - 1).getAngle());
                    for(int i = 0; i < ropeSegments.length - 1; i++)
                        ropeSegments[i + 1].setLinearVelocity(ropeSegments[i + 1].getLinearVelocity().x + (float)Math.random() * 20 - 10, ropeSegments[i + 1].getLinearVelocity().y - 15);
                }
            }
        }

        if(changeFishInit){
            takeAngleOffScreen = true;
            if(!(anglePos.y < NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength) * 2 - anglePosOffset / 25f + ropeSegments.length * height)){
                newFish(fishType);
                cat.fishTakeBite(0, 0, fishType);
                gameReady = true;
                changeFishInit = false;
                takeAngleOffScreen = false;
                letFishFall = true;
            }
        }

        if(takeAngleOffScreen){
            if(anglePos.y < NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength) * 2 - anglePosOffset / 25f + ropeSegments.length * height){
                if(angleUpDelay > 0 && angleUpDelay < 20)
                    anglePos.y += 4f / 25f;
                else if (angleUpDelay >= 50)
                    angleUpDelay = 0;
                angleUpDelay++;
                timeSpeed = 0f;
                canTakeFish = false;
            }
            else{
                angleUpDelay = 0;
                anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength)* 2 - anglePosOffset / 25f + ropeSegments.length * height;
            }
        }

        if(letFishFall){
            System.out.println("Adhwofojioijfesojaaefuiopf");
            anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength);
            canTakeFish = true;
            letFishFall = !true;
        }

        if(speedUpTime){
            timeSpeed += 0.01f;
            if(timeSpeed >= 1f) {
                speedUpTime = false;
                timeSpeed = 1f;
            }
        }


        if(showEarnedCoins){
            showEarnedCoinsCount++;
            if(showEarnedCoinsCount == 18)
                getCoins.play(0.1f);
            if(showEarnedCoinsCount <= 30 && showEarnedCoinsCount > 20)
                showEarnedCoinsOffset += 5 / 10f;
            if(showEarnedCoinsCount <= 40 && showEarnedCoinsCount > 30)
                showEarnedCoinsOffset -= 5 / 10f;
            if(showEarnedCoinsCount > 80) {
                showEarnedCoinsOffset = 0;
                showEarnedCoins = false;
            }
        }

        if(cat.segmentsEaten == fishBodies.size())
            fishEaten = true;



        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            ropePluck = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            particle_summon(NewWidth * 25 / 2, NewHeight * 25 / 2, 0, 10, 10, 5, 0, 12, true);
        }


        if(playState && !tutorialFinished && !gameOver)
            showTutorial = true;
        else
            showTutorial = false;



        if(tutorialFinished && !gameOver && playState && !tutorial2Finished)
            showTutorial2 = true;
        else if(showTutorial2) {
            showTutorial2 = false;
            timeSpeed = 1f;
        }




        if(ropePluck){
            ropePluckCount++;
            /*
            if(ropePluckCount == 1) {
                int totalForce = 90;
                int forcePerSegment = totalForce / fishBodies.size();
                //for (int i = 0; i < fishBodies.size(); i++) {
                    //fishBodies.get(0).setLinearVelocity(fishBodies.get(0).getLinearVelocity().x, totalForce);
                //}
                ropeSegments[ropeSegments.length - 1 - ropeSegmentsVisible].setLinearVelocity(ropeSegments[ropeSegments.length - 1 - ropeSegmentsVisible].getLinearVelocity().x, totalForce);
                for (int i = 0; i < ropeSegments.length - ropeSegments.length / 2 * 1.5f ; i++) {
                    ropeSegments[ropeSegments.length - 1 - i].setLinearVelocity(ropeSegments[ropeSegments.length - 1 - i].getLinearVelocity().x, 5);
                }
            }
            */

            if(ropePluckCount > 0 && ropePluckCount <= 3) {
                anglePos.y += 20 / 25f;

            }

            if(ropePluckCount >= 20) {
                anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength);
                ropePluckCount = 0;
                ropePluck = false;
            }
        }







        /*
        if(score > highscore) {
            switch (score) {
                case 1:
                    unlocks += 1;
                    unlockEvent = true;
                    break;
                case 2:
                    unlocks += 1;
                    unlockEvent = true;
                    break;
                case 3:
                    unlocks += 1;
                    unlockEvent = true;
                    break;
                case 4:
                    unlocks += 1;
                    unlockEvent = true;
                    break;
                case 25:
                    break;
            }
            unlockItem = cat.ballTex[unlocks];
        }
        */



        if(unlockEvent){
            if(unlockEventTime == 0)
                if(!barUp)
                barDown = true;

            unlockEventTime++;

            if(unlockEventTime > 30 && unlockEventTime <= 40)
                unlockItemOffset += 3f / (10);

            if(unlockEventTime > 40 && unlockEventTime <= 50)
                unlockItemOffset -= 3f / (10);

            if(unlockEventTime >= 100){
                barUp = true;
                unlockEvent = false;
                unlockEventTime = 0;
            }
        }


        ropePluckDelay += Gdx.graphics.getDeltaTime() / step;

        if(ropePluckDelay > 150){
            if(!takeAngleDown && !(cat.segmentsEaten >= fishLength) && playState && tutorial2Finished && tutorialFinished)
                ropePluck = true;
            ropePluckDelay = (float)Math.random() * 80;
        }




        if(pageTurn != 0){
            pageTurnTime++;
            int time = 8;
            int distance_x = 16;

            if(!(middle == 0 && pageTurn == 1 || middle == shopOffers.length - 1 && pageTurn == -1)) {
                for (int i = 0; i < shopOffersPos.length; i++) {
                    if (i == middle) {
                        shopOfferSize[i] -= 0.6f / time;
                    }
                    if (i == middle + 1 && pageTurn == -1) {
                        shopOfferSize[i] += 0.6f / time;
                    }
                    if (i == middle - 1 && pageTurn == 1) {
                        shopOfferSize[i] += 0.6f / time;
                    }
                    shopOffersPos[i].x += (distance_x / time) * pageTurn;
                }
            }
            else{
                pageTurnTime = 0;
                pageTurn = 0;
            }
            if(pageTurnTime >= time) {
                middle -= pageTurn;
                pageCurrent -= pageTurn;
                pageTurnTime = 0;
                pageTurn = 0;
                shopOfferSize[middle] = 1.0f;
                for (int i = 0; i < shopOffersPos.length; i++) {
                    if(i != middle)
                        shopOfferSize[i] = 0.4f;
                }
            }
        }


        if(closeShop){
            cat.floor = this.floor + 6;
            shopDown = false;
            shopUp = true;
            closeShop = false;
            shopOpen = false;
        }
        if(playStateTrigger){
            gamesPlayed++;
            prefs.putInteger("gamesPlayed", gamesPlayed);
            prefs.flush();
            menuButtonsDown = true;
            playState = true;
            angleUp = true;
            playStateTrigger = false;
            changeFishInit = true;
        }



        if(shopOpenTrigger){
            cat.floor = this.floor;
            cat.inFloor = false;
            showShop = true;
            shopUp = false;
            shopDown = true;
            menuButtonsDown = true;
            shopOpenTrigger = false;
        }
        if(shopOpen){
        }




        if(tillGameOverCount < 130 && !gameOver) {
            magic = (float)tillGameOverCount * 0.005f + 0.2f;
            //if((Math.pow(tillGameOverCount, 3) * 0.000001f) > 0)
            //dotColor = (int)((float)tillGameOverCount / magic) % 2;
            timeTillColorChange -= 1 * (Gdx.graphics.getDeltaTime() / step);
            if(timeTillColorChange <= 0) {
                timeDotShakeTime = 3f;
                timeTillColorChange = (int)(15 * magic);
                dotColor = (dotColor + 1) % 2;
                if(dotColor == 1)
                    soundAlarm.play(0.1f);
            }
        }

        if(score > highscore) {
            highscore = score;
            newHighscore = true;
        }

        if(tillGameOverCount > 0 && playState) {
            tillGameOverCount -= 1 * (Gdx.graphics.getDeltaTime() / step) * timeSpeed;
            timeDotPos.x -= 46f / ((timeTillGameOver / (Gdx.graphics.getDeltaTime() / step) / timeSpeed));
        }
        if(tillGameOverCount <= 0 && !gameOverOnceCheck){
            gameOverOnceCheck = true;
            //tillGameOverCount = timeTillGameOver;
            //timeDotPos.x = NewWidth * 25 / 2 + timeBar.getWidth() / 2 - 5;
            gameOverTrigger = true;
        }

        if(gameOverTrigger){
            soundGameover.play(0.2f);
            canTakeFish = false;
            addCoins(coinsCollected);
            gameOver = true;
            gameOverTrigger = false;
            prefs.putInteger("highscore", highscore);
            prefs.flush();
            System.out.println("submit: " + highscore);
            gpgs.submitScore(highscore);
        }


        if(gameOver){

            if (score >= 5 && score < 10){
                medalScore = 0;
            }
            else if(score >= 10 && score < 25){
                medalScore = 1;
            }
            else if(score >= 25){
                medalScore = 2;
            }

            if(scoreboardPos.y < 60)
                scoreboardPos.y += 5;

            if (b5o < 1.00f)
                b5o += 0.04f;
            else
                b5o = 1f;
        }
        else
            b5o = 0;



        if(barDown){
            if(unlockedBarPos.y > barDownMax)
                unlockedBarPos.y -= 5;
            if(unlockedBarPos.y <= barDownMax) {
                unlockedBarPos.y = barDownMax;
                barDown = false;
            }
        }

        if(barUp){
            if(unlockedBarPos.y < NewHeight * 25)
                unlockedBarPos.y += 5;
            if(unlockedBarPos.y >= NewHeight * 25) {
                unlockedBarPos.y = NewHeight * 25;
                barUp = false;
            }
        }

        if(shopDown){
            if(shopPos.y > shopDownMax){
                shopPos.y -= 10;
            }
            if(shopPos.y <= shopDownMax) {
                shopPos.y = shopDownMax;
                shopDown = false;
                shopOpen = true;
            }
        }

        if(numbersDown){
            if(numberHeight > 130){
                numberHeight -= 5;
            }
            if(numberHeight <= 130) {
                numberHeight = 130;
                numbersDown = false;
            }
        }

        if(shopUp){
            if(shopPos.y < NewHeight * 25 + 40){
                shopPos.y += 10;
            }
            else {
                shopUp = false;
                showShop = false;
            }
        }

        if(menuButtonsDown){
            if(buttonPos[0].y > -button[0].getRegionHeight()){
                buttonMoveTime++;
                buttonPos[0].y = 3 + (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
                buttonPos[3].y = 3 + (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
                buttonPos[8].y = 3 + (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
            }
            else {
                buttonMoveTime = 0;
                menuButtonsDown = false;
            }
        }

        if(menuButtonsUp){
            if(buttonPos[0].y < 3){

                buttonMoveTime++;
                buttonPos[0].y = -button[0].getRegionHeight() - (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
                buttonPos[3].y = -button[0].getRegionHeight() - (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
                buttonPos[8].y = -button[0].getRegionHeight() - (float)(-Math.pow((buttonMoveTime - 2), 2) + 4);
            }
            if(buttonPos[0].y >= 3) {
                buttonPos[0].y = 3;
                buttonPos[3].y = 4;
                buttonPos[8].y = 4;
                buttonMoveTime = 0;
                menuButtonsUp = false;
            }

        }




        if(angleUp){
            angleUpCount++;
            anglePos.y += 4 + 0.5 * angleUpCount;
            if(anglePos.y >= NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength)){
                System.out.println("adlwoloawt");
                anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength);
                angleUp = false;
                angleUpCount = 0;
            }
        }


        if(playState){
            playStateCount++;
            //buttonPos[0].y = 3 + (float)(-Math.pow((playStateCount - 2), 2) + 4);
            //buttonPos[3].y = 3 + (float)(-Math.pow((playStateCount - 2), 2) + 4);
        }

        //System.out.println("graph; x = " + playStateCount + "; " + ( -Math.pow((2 * 1 - 2), 2) + 4));

        /*if(Gdx.input.justTouched()) {
            if(playState && !gameOver) {
                if (tapX > (NewWidth * 25) / 2)
                    if (cat.onGround)
                        cat.jump(true);

                if (tapX < (NewWidth * 25) / 2)
                    if (cat.onGround)
                        cat.jump(false);
            }
        }*/
        if(cat.position.x < fishBodies.get(fishBodies.size() - 1).getPosition().x * 25 + 10 && cat.position.x > fishBodies.get(fishBodies.size() - 1).getPosition().x * 25 - 10)
            if(cat.position.y < fishBodies.get(fishBodies.size() - 1).getPosition().y * 25 + 10 && cat.position.y > fishBodies.get(fishBodies.size() - 1).getPosition().y * 25 - 10){
                if(!cat.inFloor)
                    if(canTakeFish) {
                        takeAngleDownTrigger += 1;
                        if (!tutorialFinished) {
                            tutorialFinished = true;
                        }
                    }
            }

        if(Gdx.input.justTouched()){
            for(int i = 0; i < button.length; i++)
                if(tapX > buttonPos[i].x && tapX < buttonPos[i].x + button[i].getRegionWidth())
                    if(tapY > buttonPos[i].y && tapY < buttonPos[i].y + button[i].getRegionHeight())
                        if((i != 10 || i == 10 && showCoinWindow) && buttonVisible[i])
                            click0.play(0.1f);
        }

        if(Gdx.input.isTouched()){
            for(int i = 0; i < button.length; i++)
                if(tapX > buttonPos[i].x && tapX < buttonPos[i].x + button[i].getRegionWidth())
                    if(tapY > buttonPos[i].y && tapY < buttonPos[i].y + button[i].getRegionHeight())
                        if((i != 10 || i == 10 && showCoinWindow) && buttonVisible[i])
                            if(!showRateWindow || i == 11 || i == 12)
                            buttonPressed = i + 1;
        }
        else{
            switch(buttonPressed){
                case 1: playStateTrigger = true;
                    break;
                case 2:
                    if(adLoaded) {
                       adMob.showAd();
                        adLoaded = false;
                    }
                    break;
                case 3: restart();
                    break;
                case 4: shopOpenTrigger = true;

                    break;
                case 5:
                    if(pageTurn == 0)
                        pageTurn = 1;
                    break;
                case 6:
                    if(pageTurn == 0)
                        pageTurn = -1;
                    break;
                case 7:
                    if(unlocked[shopItem[pageCurrent]] && shopOpen) {
                        fishType = shopItem[pageCurrent] + 1;
                        newFish(fishType);
                        cat.fishTakeBite(100, 0, fishType);
                        anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength);
                        prefs.putInteger("selectedFish", fishType - 1);
                        prefs.flush();
                    }
                    boolean tempBool = false;
                    for(int i = 0; i < unlocked.length; i++) {
                        if (unlocked[shopItem[i]] == false && !tempBool) {
                            if(pageCurrent == i){
                                if(coins >= price[shopItem[pageCurrent]]){
                                    soundUnlock.play(0.3f);
                                    soundBreak.play(0.3f);
                                    unlocked[shopItem[pageCurrent]] = true;

                                    for(int o = 0; o < unlocked.length; o++)
                                        prefs.putBoolean("unlock" + String.valueOf(o), unlocked[o]);

                                    prefs.flush();

                                    int offset = 6;
                                    addCoins(-price[shopItem[pageCurrent]]);
                                    Vector2[] lockParticlePos = new Vector2[6];
                                    lockParticlePos[0] = new Vector2((NewWidth * 25 - 108) / 2f + 52 + atlas.findRegion("lockBreakParticle", 0).getRegionWidth() / 2, offset+83 + atlas.findRegion("lockBreakParticle", 0).getRegionHeight() / 2);
                                    lockParticlePos[1] = new Vector2((NewWidth * 25 - 108) / 2f + 57 + atlas.findRegion("lockBreakParticle", 1).getRegionWidth() / 2, offset+89 + atlas.findRegion("lockBreakParticle", 1).getRegionHeight() / 2);
                                    lockParticlePos[2] = new Vector2((NewWidth * 25 - 108) / 2f + 48 + atlas.findRegion("lockBreakParticle", 2).getRegionWidth() / 2, offset+92 + atlas.findRegion("lockBreakParticle", 2).getRegionHeight() / 2);
                                    lockParticlePos[3] = new Vector2((NewWidth * 25 - 108) / 2f + 39 + atlas.findRegion("lockBreakParticle", 3).getRegionWidth() / 2, offset+82 + atlas.findRegion("lockBreakParticle", 3).getRegionHeight() / 2);
                                    lockParticlePos[4] = new Vector2((NewWidth * 25 - 108) / 2f + 45 + atlas.findRegion("lockBreakParticle", 4).getRegionWidth() / 2, offset+83 + atlas.findRegion("lockBreakParticle", 4).getRegionHeight() / 2);
                                    lockParticlePos[5] = new Vector2((NewWidth * 25 - 108) / 2f + 39 + atlas.findRegion("lockBreakParticle", 5).getRegionWidth() / 2, offset+91 + atlas.findRegion("lockBreakParticle", 5).getRegionHeight() / 2);

                                    for(int o = 0; o < 6; o++)
                                        particle_summon(lockParticlePos[o].x, lockParticlePos[o].y, 0, 3, 1, 1, o + 1, 0, false);
                                }
                            }
                            tempBool = true;
                        }
                    }
                    break;
                case 8: menuButtonsUp = true;
                    closeShop = true;
                    break;
                case 9:

                    if(gpgs.isConnected()) {
                        //gpgs.submitScore(highscore);
                        gpgs.showLeaderboard();
                    }
                    else
                    gpgs.connect();

                    /*
                    fishType = (int)(Math.random() * 3) + 1;
                    newFish(fishType);
                    cat.fishTakeBite(0,0);
                    */
                    /*
                    for(int i = 0; i < ropeSegments.length; i++){
                        ropeSegments[i].destroyFixture(ropeSegments[i].getFixtureList().get(0));
                        world.destroyBody(ropeSegments[i]);
                    }
                    */

                    /*
                    Array<Joint> allBodies = new Array<Joint>();
                    world.getJoints(allBodies);
                    for(int i = 0; i < allBodies.size; i++){
                        world.destroyJoint(allBodies.get(i));
                    }
                    ground.setTransform(0, floor / 25f, 0);
                    /*
                    Array<Body> allBodies = new Array<Body>();
                    world.getBodies(allBodies);
                    for(int i = 0; i < allBodies.size; i++){
                        allBodies.get(i).destroyFixture(allBodies.get(i).getFixtureList().get(0));
                        world.destroyBody(allBodies.get(i));
                    }
                    */
                    break;
                case 10:
                    restart();
                    shopOpenTrigger = true;
                    break;
                case 11:
                    showCoinWindow = false;
                    addCoins(5);
                    break;
                case 12:
                    showRateWindow(false);
                    gpgs.rateGame();
                    break;
                case 13:
                    showRateWindow(false);
                    break;

            }
            if(buttonPressed != 0)
                click1.play(0.1f);
            buttonPressed = 0;
            buttonOffset = 0;
        }



        if(cat.catSinkTrigger) {
            anglePos.y = NewHeight + sumFishBodies(fishBodies.size()) - 20 / 25f;
        }


        float vx = cat.velocity.x * 2, vy = cat.velocity.y * 2.25f;
        if(takeAngleDownTrigger == 1){


            for(Body fb : fishBodies){
                fb.setLinearVelocity(fb.getLinearVelocity().x + vx, fb.getLinearVelocity().y + vy);
            }

            showSplash = true;
            splashPos.set(fishBodies.get(fishBodies.size() - 1).getPosition().x * 25, fishBodies.get(fishBodies.size() - 1).getPosition().y * 25);
            timeSpeed = 0;
            /*
            for(int i = 0; i < ropeSegments.length; i++)
                ropeSegments[i].setLinearVelocity(ropeSegments[i].getLinearVelocity().x + cat.velocity.x * 1f, ropeSegments[i].getLinearVelocity().y + cat.velocity.y * 0.5f);
            ropeSegments[MainClass.ropeSegments.length - 2].setLinearVelocity(ropeSegments[MainClass.ropeSegments.length - 2].getLinearVelocity().x + cat.velocity.x * 5, ropeSegments[MainClass.ropeSegments.length - 2].getLinearVelocity().y + cat.velocity.y * 2);
            ropeSegments[MainClass.ropeSegments.length - 1].setLinearVelocity(ropeSegments[MainClass.ropeSegments.length - 1].getLinearVelocity().x + cat.velocity.x * 5, ropeSegments[MainClass.ropeSegments.length - 1].getLinearVelocity().y + cat.velocity.y * 2);
            */

            if(!gameOver && (tutorial2Finished && !dontShowTutorial || tutorialFinished && dontShowTutorial)) {
                score++;
            }

            if(!tutorial2Finished && showTutorial2) {
                tutorial2Finished = true;
                numbersDown = true;
            }

            tillGameOverCount = timeTillGameOver;
            timeDotPos.x = NewWidth * 25 / 2 + timeBar.getRegionWidth() / 2 - 5;
            numberShakeTime = 3.0f;
            timeDotShakeTime = 3;
            takeAngleDown = true;
            soundPick.play(0.1f);
            dotColor = 0;
        }

        if(takeAngleDownTrigger > 0 && takeAngleDownTrigger < 2)
            takeAngleDownTrigger+= 1;

        timeAfterLetGoCount++;

        if(takeAngleDown){
            timeAfterLetGoCount = 0;
			/*
			RopeJointDef ropeJointDef = new RopeJointDef();
			ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 1];
			ropeJointDef.bodyB = ropeSegments[ropeSegments.length - 1];
			*/



            //ropeSegments[ropeSegments.length - 1].setType(BodyDef.BodyType.StaticBody);
            //ropeSegments[ropeSegments.length - 1].setTransform(cat.position.x / 25f, cat.position.y / 25f, 0);
        }

        if(cat.letAngleGoTrigger == 1){
            rbgSwitch = true;
            screenshakeTime = 1;
            timeSpeed = 1f;
            takeAngleDown = false;
            takeAngleDownTrigger = 0;

            if(cat.segmentsEaten < fishLength)
                angleUp = true;
        }


        if(showSplash){
            splash = splashTex[(int)Math.floor(showSplashCount / 3f)];
            showSplashCount++;
            if(showSplashCount >= 15){
                showSplash = false;
                showSplashCount = 0;
            }
        }



        /*
        if(cat.inFloor)
        if(takeFishJoint != null) {
            for (int i = 0; i < takeFishJoint.length; i++) {
                if (takeFishJoint[i] != null) {
                    takeFishJoint[i].setUserData(null);
                    world.destroyJoint(takeFishJoint[i]);
                    takeFishJoint[i] = null;
                }
            }
        }
*/

        count += (int)(Gdx.graphics.getDeltaTime() / step);

        if(count >= timeTillNewPoint) {
            AngleDif = (float)((Math.random() * 40 + 25) * ((int)(Math.random() * 2) * 2 - 1)) / 25f;

            if(AngleDif + anglePos.x > NewWidth)
                AngleDif = AngleDif * -1;

            if(AngleDif + anglePos.x < 0)
                AngleDif = AngleDif * -1;
            count = 0;
        }

        if(anglePos.x + (AngleDif / timeTillNewPoint) * (Gdx.graphics.getDeltaTime() / step) < NewWidth -40 / 25f && anglePos.x + (AngleDif / timeTillNewPoint) * (Gdx.graphics.getDeltaTime() / step) > 0.0f + 40 / 25f)
        anglePos.x += (AngleDif / timeTillNewPoint) * (Gdx.graphics.getDeltaTime() / step);

        //ropeControlAdd.set((anglePos.x - ropeSegments[0].getPosition().x) * 10, (anglePos.y - ropeSegments[0].getPosition().y) * 10);
       // ropeSegments[0].setLinearVelocity(ropeControlAdd.x, ropeControlAdd.y);
        ropeSegments[0].setTransform(anglePos, 0);
        //ropeControlJoint.setTarget(anglePos);


        cat.update();

        if(cat.fallTrigger){
            tutorialLoop = 0;
        }

        if(showTutorial && gameReady) {
            timeSpeed = 0.1f;
            if(tutorialLoop == 0){
                handPos.set((int)cat.position.x - 5, 19);
                handRandomDirection = (int)(Math.random() * 90);
                handRightOrLeft = (int)(Math.random() * 2) * 2 - 1;
            }
            tutorialLoop++;

            if(tutorialLoop <= 30){
                handType = 0;
            }
            if(tutorialLoop > 30){
                handType = 1;
            }

            if(tutorialLoop <= 70 && tutorialLoop > 40) {
                //handPos.x += 1 / (Math.tan(handRandomDirection * MathUtils.degreesToRadians) / 90);
                //handPos.y += 1 * (Math.tan(handRandomDirection * MathUtils.degreesToRadians) / 90);
                handPos.x += (1 - (handRandomDirection / 90)) * handRightOrLeft * handSpeed;
                handPos.y += (1 * (handRandomDirection / 90)) * handSpeed;
            }
            if(tutorialLoop > 80){
                handType = 0;
            }

            if(tutorialLoop > 150){
                tutorialLoop = 0;
            }
        }

        int mtime = 15;
        if(screenshakeTime > 0){
            screenshakeTime++;
            if(screenshakeTime > mtime)
                screenshakeTime = 0;
        }



        int intensity = 5;
        if(screenshakeTime > 0 && screenshakeTime <= mtime){
            screenShake.set((float)Math.random() * (intensity / 2f - screenshakeTime / mtime) - (intensity / 2f - screenshakeTime / mtime) / 2, (float)Math.random() * (intensity - screenshakeTime / mtime) - (intensity - screenshakeTime / mtime) / 2);
        }
        else if(screenShake.x + screenShake.y != 0f){
            screenShake.set(0,0);
        }


        //score = Gdx.graphics.getFramesPerSecond();


        int tempScore = score;
        if(score >= 1000)
            score = 999;


        number = numbers[(score % 100) % 10];
        if(score >= 10)
            number_second = numbers[(int) Math.floor((score % 100) / 10)];
        if (score >= 100)
            number_third = numbers[(int) Math.floor(score / 100)];

        score = tempScore;
        if(numberShakeTime > 0f)
            numberShakeTime -= 0.1f;
        else numberShakeTime = 0;

        if(timeDotShakeTime > 0f)
            timeDotShakeTime -= 0.1f;
        else timeDotShakeTime = 0;

        int tempHighscore = highscore;
        if(highscore >= 1000)
            highscore = 999;

        highscoreNumber = numbers[highscore % 100 % 10];
        if(highscore >= 10)
            highscoreNumber_second = numbers[(int)Math.floor(highscore % 100 / 10)];
        if(highscore >= 100)
            highscoreNumber_third = numbers[(int) Math.floor(highscore / 100)];

        highscore = tempHighscore;

        if(numberShakeTime > 0f)
            numberShakeTime -= 0.1f;
        else numberShakeTime = 0;

        numberShake = MathUtils.random(-6 * (numberShakeTime / 3), 6 * (numberShakeTime / 3));
        timeDotShake = MathUtils.random(-6 * (timeDotShakeTime / 3), 6 * (timeDotShakeTime / 3));

        buttonPos[2].y = scoreboardPos.y - 31;
        buttonPos[9].y = scoreboardPos.y - 30;

        medalWidth = 27*3 - b5o * 27*2;

        buttonPos[4].y = shopPos.y - 13;
        buttonPos[5].y = shopPos.y - 13;
        buttonPos[6].y = shopPos.y - 13;
        buttonPos[7].y = shopPos.y + 61;
        buttonPos[1].y = shopPos.y - 35;

        if(Gdx.input.isTouched()){
            touch();
        }
        else{
            touchTime = 0;
            catJumped = false;
            cat.catReady = false;
        }

        if(showJumpExplosion){
            if(jumpExplosionCount == 0)
                jumpExplosionPos.set(cat.position.x, floor);
            jumpExplosion = jumpExplosionTex[(int)Math.floor(jumpExplosionCount / 3f)];
            jumpExplosionCount++;
            if(jumpExplosionCount >= 18){
                showJumpExplosion = false;
                jumpExplosionCount = 0;
            }
        }

        numberCoinsCollected = smallNumbers[coinsCollected % 10];
        if(coinsCollected >= 10)
            numberCoinsCollected_second = smallNumbers[(int)Math.floor(coinsCollected / 10)];

        for(Particle p : particles) {
            p.update();
            if(p.remove)
                particlesClean.add(p);
        }

        for(Particle p : particlesClean){
            particles.remove(p);
        }

        particlesClean.clear();

        if(cat.fallTrigger)
            particle_summon(cat.position.x + MathUtils.random(-5, 5), floor - 2, 1 * catVelocityAfterFall.x, 5, 2, 5, 0, 5, false);
    }

    public static Vector2 catVelocityAfterFall;

    public void particle_summon(float ppx, float ppy, float vx, float vy, int scatter, int particleAmount, int type, int spread, boolean floorCollision) {
        for (int i = 0; i < particleAmount; i++) {
            particles.add(new Particle(ppx + MathUtils.random(-spread, spread), ppy + MathUtils.random(-spread, spread), MathUtils.random(vx - scatter, vx + scatter), MathUtils.random(vy - scatter, vy + scatter), type, floorCollision));
        }
    }

    int screenshakeTime;
    TextureRegion numberCoinsCollected;
    TextureRegion numberCoinsCollected_second;
    TextureRegion[] smallNumbers;
    int coinsCollected;
    int touchTime;
    Vector2 touchDiff;
    boolean catJumped;

    void showRateWindow(boolean show){
        if(show) {
            showRateWindow = true;
            buttonVisible[11] = true;
            buttonVisible[12] = true;
        }
        else{
            showRateWindow = false;
            buttonVisible[11] = false;
            buttonVisible[12] = false;
        }

    }
    void touch(){

        if (touchTime == 0)
            touchStart.set(tapX, tapY);
        if(touchStart.x > cat.position.x - 30 && touchStart.x < cat.position.x + 30 && touchStart.y > cat.position.y - 15 && touchStart.y < cat.position.y + 40) {
            if (cat.onGround && playState && !gameOver)
                cat.catReady = true;
            if (!catJumped && playState && !gameOver) {

                touchDiff.set(tapX - touchStart.x, tapY - touchStart.y);

                if (Math.sqrt(Math.pow(Math.abs(touchDiff.x), 2) + Math.pow(Math.abs(touchDiff.y), 2)) >= 20f) {


                    /*

                    if (touchDiff.y < 10) {
                        touchDiff.y = 10;
                    }
                    if (touchDiff.y > 15) {
                        touchDiff.y = 15;
                    }
                    if (touchDiff.x > 15)
                        touchDiff.x = 15;
                    if (touchDiff.x < -15)
                        touchDiff.x = -15;

*/

                    int positivity = 0;
                    if(touchDiff.x >= 0)
                        positivity = 1;
                    if(touchDiff.x < 0)
                        positivity = -1;


                    if(Math.atan(Math.abs(touchDiff.y) / Math.abs(touchDiff.x)) * MathUtils.radiansToDegrees < 45f)
                        touchDiff.set(positivity * (float)(1f - (Math.atan(Math.abs(touchDiff.y) / Math.abs(touchDiff.x)) * MathUtils.radiansToDegrees) / 90), (float)(Math.atan(Math.abs(touchDiff.y) / Math.abs(touchDiff.x)) * MathUtils.radiansToDegrees) / 45);
                    else
                        touchDiff.set(positivity * (float)(1f - (Math.atan(Math.abs(touchDiff.y) / Math.abs(touchDiff.x)) * MathUtils.radiansToDegrees) / 90), 1);

                    if (portrait) {
                        cat.jump(touchDiff.x * 10, touchDiff.y * 10);
                    }
                    else {
                        cat.jump(touchDiff.x * 10, touchDiff.y * 8);
                    }
                    catJumped = true;
                }
            }
        }
        touchTime++;

    }

    int buttonOffset;
    @Override
    public void render () {

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {

            if(!Freeze)
            Freeze = true;
            else
                Freeze = false;
        }



        cam.position.set(camposition.x + screenShake.x / 75, camposition.y + screenShake.y / 75, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        tapX = MathUtils.roundPositive((Gdx.input.getX() / 4) * (NewWidth * 25 / (Gdx.graphics.getWidth() / 4f)));
        tapY = MathUtils.roundPositive((-(Gdx.input.getY() / 4 - Gdx.graphics.getHeight() / 4) * (NewHeight * 25 / (Gdx.graphics.getHeight() / 4f))));

        if(!Freeze)
        update();


        Gdx.gl.glClearColor((150 / 255f) * rbg.x, (150 / 255f) * rbg.y, (150 / 255f) * rbg.z, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.setColor(rbg.x,rbg.y,rbg.z,1);
        batch.draw(backGround, NewWidth / 2 - backGround.getRegionWidth() / 50f,0,backGround.getRegionWidth() / 25f, backGround.getRegionHeight() / 25f);

        batch.setColor(1,1,1,1);


        if(!gameOver) {
            if (score < 10)
                batch.draw(number, NewWidth / 2 - number.getRegionWidth() / 25f / 2, numberHeight / 25f + numberShake / 25f, number.getRegionWidth() / 25f, number.getRegionHeight() / 25f);
            if (score >= 10 && score < 100) {
                batch.draw(number, NewWidth / 2 - number.getRegionWidth() / 25f / 2 + number_second.getRegionWidth() / 2 / 25f, numberHeight / 25f + numberShake / 25f, number.getRegionWidth() / 25f, number.getRegionHeight() / 25f);
                batch.draw(number_second, NewWidth / 2 - number.getRegionWidth() / 25f / 2 + number_second.getRegionWidth() / 2 / 25f - number_second.getRegionWidth() / 25f - 1 / 25f, numberHeight / 25f + numberShake / 25f, number_second.getRegionWidth() / 25f, number_second.getRegionHeight() / 25f);
            }
            if (score >= 100) {
                batch.draw(number, NewWidth / 2 - (number.getRegionWidth() / 25f + number_third.getRegionWidth() / 25f + number_second.getRegionWidth() / 25f) / 2, numberHeight / 25f + numberShake / 25f, number.getRegionWidth() / 25f, number.getRegionHeight() / 25f);
                batch.draw(number_second, NewWidth / 2 - (number.getRegionWidth() / 25f + number_third.getRegionWidth() / 25f + number_second.getRegionWidth() / 25f) / 2 + (number.getRegionWidth() + 1) / 25f, numberHeight / 25f + numberShake / 25f, number_second.getRegionWidth() / 25f, number_second.getRegionHeight() / 25f);
                batch.draw(number_third, NewWidth / 2 - (number.getRegionWidth() / 25f + number_third.getRegionWidth() / 25f + number_second.getRegionWidth() / 25f) / 2 + (number.getRegionWidth() + 2 + number_second.getRegionWidth()) / 25f, numberHeight / 25f + numberShake / 25f, number_third.getRegionWidth() / 25f, number_third.getRegionHeight() / 25f);
            }
        }
        numberCoinsCollected = smallNumbers[coinsCollected % 10];
        if(coinsCollected >= 10)
            numberCoinsCollected_second = smallNumbers[(int)Math.floor(coinsCollected / 10)];

        if(showEarnedCoins){
            float lineWidth = smallNumbers[coinsEarned % 10].getRegionWidth() + plusCharacter.getRegionWidth() + coinTex.getRegionWidth() + 2 + 1;
            if(coinsEarned >= 10)
                lineWidth = smallNumbers[coinsEarned % 10].getRegionWidth() + smallNumbers[(int)Math.floor(coinsEarned / 10)].getRegionWidth() + plusCharacter.getRegionWidth() + coinTex.getRegionWidth() + 2 + 1 + 1;

            batch.draw(plusCharacter, (coinsEarnedPos.x - lineWidth / 2) / 25f, (coinsEarnedPos.y + 1) / 25f, plusCharacter.getRegionWidth() / 25f, plusCharacter.getRegionHeight() / 25f);

            if (coinsEarned < 10)
                batch.draw(smallNumbers[coinsEarned % 10], (coinsEarnedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1) / 25f, coinsEarnedPos.y / 25f, smallNumbers[coinsEarned % 10].getRegionWidth() / 25f, smallNumbers[coinsEarned % 10].getRegionHeight() / 25f);
            if (coinsEarned >= 10) {
                batch.draw(smallNumbers[(int)Math.floor(coinsEarned / 10)], (coinsEarnedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1) / 25f, coinsEarnedPos.y / 25f, smallNumbers[(int)Math.floor(coinsEarned / 10)].getRegionWidth() / 25f, smallNumbers[(int)Math.floor(coinsEarned / 10)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[coinsEarned % 10], (coinsEarnedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1 + smallNumbers[(int)Math.floor(coinsEarned / 10)].getRegionWidth() + 1) / 25f, coinsEarnedPos.y / 25f, smallNumbers[coinsEarned % 10].getRegionWidth() / 25f, smallNumbers[coinsEarned % 10].getRegionHeight() / 25f);
            }

            batch.draw(coinTex, (coinsEarnedPos.x + lineWidth / 2f - coinTex.getRegionWidth()) / 25f, (coinsEarnedPos.y - 1 + showEarnedCoinsOffset) / 25f, coinTex.getRegionWidth() / 25f, coinTex.getRegionHeight() / 25f);
        }

        batch.draw(unlockedBarTex, unlockedBarPos.x / 25f, unlockedBarPos.y / 25f, unlockedBarTex.getRegionWidth() / 25f, unlockedBarTex.getRegionHeight() / 25f);
        //batch.draw(unlockItem, (unlockedBarPos.x + 45 - unlockItem.getRegionWidth() / 2) / 25f, (unlockedBarPos.y + 16 - unlockItem.getRegionHeight() / 2 + unlockItemOffset) / 25f, unlockItem.getRegionWidth() / 25f, unlockItem.getRegionHeight() / 25f);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.5f, 0.5f, 0.5f, 1);


        batch.end();

        ropeSegmentsVisible = 0;
        for(int i = 0; i < ropeSegments.length - 1; i++){
            if(ropeSegments[i].getPosition().y < NewHeight + height + 1 / 25f) {
                ropeSegmentsVisible++;
                if (i % 2 == 0) {
                    sr.setColor(81 / 255f, 56 / 255f, 37 / 255f, 1);
                } else
                    sr.setColor(52 / 255f, 30 / 255f, 15 / 255f, 1);

                sr.rectLine(ropeSegments[i].getPosition().x * 25 * specialNumber, ropeSegments[i].getPosition().y * 25 * specialNumber, ropeSegments[i + 1].getPosition().x * 25 * specialNumber, ropeSegments[i + 1].getPosition().y * 25 * specialNumber, specialNumber * 2);
            }
        }
        sr.setColor(52 / 255f, 30 / 255f, 15 / 255f, 1);
        sr.rectLine(ropeSegments[ropeSegments.length - 1].getPosition().x * 25 * specialNumber, ropeSegments[ropeSegments.length - 1].getPosition().y * 25 * specialNumber, fishBodies.get(1).getPosition().x * 25 * specialNumber, fishBodies.get(1).getPosition().y * 25 * specialNumber, specialNumber * 2);
        //sr.circle(tapX * specialNumber, tapY * specialNumber, 10);
        sr.end();

        batch.begin();

        for(Particle p : particles)
        if(p.type == 0)
            p.render(batch);

        cat.render(batch);

        if(showSplash){
            batch.draw(splash, (splashPos.x - splash.getRegionWidth() / 2) / 25f, (splashPos.y - splash.getRegionHeight() / 2) / 25f, splash.getRegionWidth() / 25f, splash.getRegionHeight() / 25f);
        }

        if(showJumpExplosion){
            batch.draw(jumpExplosion, (jumpExplosionPos.x - jumpExplosion.getRegionWidth() / 2) / 25f, (jumpExplosionPos.y) / 25f, jumpExplosion.getRegionWidth() / 25f, jumpExplosion.getRegionHeight() / 25f);
        }

        if(showFishPressInfo){
            batch.draw(pressForFishButton, (pressFishInfoPos.x - pressForFishButton.getRegionWidth() / 2) / 25f, (pressFishInfoPos.y - pressForFishButton.getRegionHeight() / 2) / 25f, pressForFishButton.getRegionWidth() / 25f, pressForFishButton.getRegionHeight() / 25f);
            batch.draw(pressForFishHand, (pressFishInfoPos.x - pressForFishHand.getRegionWidth() / 2) / 25f, (pressFishInfoPos.y - pressForFishHand.getRegionHeight() / 2) / 25f, pressForFishHand.getRegionWidth() / 25f, pressForFishHand.getRegionHeight() / 25f);
        }

        //batch.draw(buttonBaseTex, (scoreboardPos.x - 40) / 25f, scoreboardPos.y / 25f, buttonBaseTex.getWidth() / 25f, buttonBaseTex.getHeight() / 25f);

        batch.draw(scoreboardTex, scoreboardPos.x / 25f, scoreboardPos.y / 25f, scoreboardTex.getRegionWidth() / 25f, scoreboardTex.getRegionHeight() / 25f);

        if(showTextNextFish)
        batch.draw(tapForNextFishText, 14 / 25f, 80 / 25f, tapForNextFishText.getRegionWidth() / 25f, tapForNextFishText.getRegionHeight() / 25f);

        if(buttonPressed == 8){ buttonOffset = 1;}
        batch.draw(button[7], buttonPos[7].x / 25f, (buttonPos[7].y - buttonOffset) / 25f, button[7].getRegionWidth() / 25f, button[7].getRegionHeight() / 25f);
        buttonOffset = 0;

        if(shopPos.y - 20 < NewHeight * 25) {
            batch.draw(shopCanvas, (shopPos.x - shopCanvas.getRegionWidth() / 2) / 25f, shopPos.y / 25f, shopCanvas.getRegionWidth() / 25f, shopCanvas.getRegionHeight() / 25f);
        }

        //Gdx.gl.glScissor(92 * Gdx.graphics.getWidth() / 432, 250 * Gdx.graphics.getHeight() / 768, 245 * Gdx.graphics.getWidth() / 432, 666 * Gdx.graphics.getHeight() / 768);

        batch.end();


        if(showShop) {


            Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
            Gdx.gl.glScissor((int)((shopPos.x + 6 - shopCanvas.getRegionWidth() / 2f) / ((NewWidth * 25) / Gdx.graphics.getWidth())),(int)(shopPos.y / ((NewHeight * 25) / Gdx.graphics.getHeight())),(int)((shopCanvas.getRegionWidth() - 6 * 2) / ((NewWidth * 25) / Gdx.graphics.getWidth())),(int)(shopCanvas.getRegionHeight() / ((NewHeight * 25) / Gdx.graphics.getHeight())));
            //Gdx.gl.glScissor((int) ((Gdx.graphics.getWidth() + Gdx.graphics.getWidth() * scissorExtra / 2f) / 768f * 178), (int)((Gdx.graphics.getHeight() / 768f) * 280), (int) ((Gdx.graphics.getWidth() / 768f) * 413), (int) ((Gdx.graphics.getHeight() / 432f) * 666));
            //Gdx.gl.glScissor((int)((Gdx.graphics.getWidth() / 768f) * 178), (int)((Gdx.graphics.getHeight() / 768f) * 280), (int) (((Gdx.graphics.getHeight() * (9f/16f)) / 768f) * 413), (int) ((Gdx.graphics.getHeight() / 432f) * 666));
            //Gdx.gl.glScissor((int) ((Gdx.graphics.getWidth() / 768f) * 178), (int)((Gdx.graphics.getHeight() / 768f) * 280), (int) ((Gdx.graphics.getWidth() / 768f) * 413), (int) ((Gdx.graphics.getHeight() / 432f) * 666));
            //Gdx.gl.glScissor((int) ((Gdx.graphics.getWidth() / NewHeight) * 2.3f), (int)((Gdx.graphics.getHeight() / 768f) * 280), (int) ((Gdx.graphics.getWidth() / NewHeight) * 3.1f), (int) ((Gdx.graphics.getHeight() / 432f) * 666));
            batch.begin();

            for (int i = 0; i < shopOffers.length; i++) {
                batch.draw(shopOffers[i], (shopOffersPos[i].x - (shopOffers[i].getRegionWidth() * shopOfferSize[i]) / 2) / 25f, (shopOffersPos[i].y - (shopOffers[i].getRegionHeight() * shopOfferSize[i]) / 2 + shopPos.y - 20) / 25f, (shopOffers[i].getRegionWidth() * shopOfferSize[i]) / 25f, (shopOffers[i].getRegionHeight() * shopOfferSize[i]) / 25f);
            }
            for (int i = 0; i < shopOffers.length; i++) {
                //if(fishType - 1 == shopItem[i])
                   // batch.draw(selected, (shopOffersPos[i].x - (selected.getRegionWidth() * shopOfferSize[i]) / 2) / 25f, (shopOffersPos[i].y - (selected.getRegionHeight() * shopOfferSize[i]) / 2 + shopPos.y - 20 - 20) / 25f, (selected.getRegionWidth() * shopOfferSize[i]) / 25f, (selected.getRegionHeight() * shopOfferSize[i]) / 25f);
            }
            if(fishType - 1 == shopItem[pageCurrent]) {
                batch.draw(selected, (shopOffersPos[pageCurrent].x - (selected.getRegionWidth() * shopOfferSize[pageCurrent]) / 2) / 25f, (shopOffersPos[pageCurrent].y - (selected.getRegionHeight() * shopOfferSize[pageCurrent]) / 2 + shopPos.y - 20 - 20) / 25f, (selected.getRegionWidth() * shopOfferSize[pageCurrent]) / 25f, (selected.getRegionHeight() * shopOfferSize[pageCurrent]) / 25f);
            }

            for (int i = 0; i < shopOffers.length; i++) {
                if(unlocked[shopItem[i]] == false)
                    batch.draw(lockTex, (shopOffersPos[i].x - (lockTex.getRegionWidth() * shopOfferSize[i]) / 2) / 25f, (shopOffersPos[i].y - ((lockTex.getRegionHeight()) * shopOfferSize[i]) / 2 + shopPos.y - 20) / 25f, (lockTex.getRegionWidth() * shopOfferSize[i]) / 25f, (lockTex.getRegionHeight() * shopOfferSize[i]) / 25f);
            }
            for (int i = 0; i < shopOffers.length; i++) {
                if(unlocked[shopItem[i]] == false)
                    if (shopOfferSize[i] > 0.4f) {
                        batch.draw(lockTex, (shopOffersPos[i].x - (lockTex.getRegionWidth() * shopOfferSize[i]) / 2) / 25f, (shopOffersPos[i].y - ((lockTex.getRegionHeight()) * shopOfferSize[i]) / 2 + shopPos.y - 20) / 25f, (lockTex.getRegionWidth() * shopOfferSize[i]) / 25f, (lockTex.getRegionHeight() * shopOfferSize[i]) / 25f);
                    }
            }
            batch.end();
            Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        }

        batch.begin();


        if(shopOpen) {
            boolean tempBool = false;
            for(int i = 0; i < unlocked.length; i++) {
                if (unlocked[shopItem[i]] == false && !tempBool) {
                    if(pageCurrent == i) {
                        float lineWidth = smallNumbers[priceCurrentItem % 10].getRegionWidth() + coinTex.getRegionWidth() + 2;
                        if (priceCurrentItem >= 10)
                            lineWidth = smallNumbers[priceCurrentItem % 10].getRegionWidth() + smallNumbers[(int) Math.floor(priceCurrentItem / 10)].getRegionWidth() + coinTex.getRegionWidth() + 2 + 1;

                        if (priceCurrentItem < 10)
                            batch.draw(smallNumbers[priceCurrentItem % 10], (pricePos.x - lineWidth / 2) / 25f, (shopPos.y + 28) / 25f, smallNumbers[priceCurrentItem % 10].getRegionWidth() / 25f, smallNumbers[priceCurrentItem % 10].getRegionHeight() / 25f);
                        if (priceCurrentItem >= 10) {
                            batch.draw(smallNumbers[(int) Math.floor(priceCurrentItem / 10)], (pricePos.x - lineWidth / 2) / 25f, (shopPos.y + 28) / 25f, smallNumbers[(int) Math.floor(priceCurrentItem / 10)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor(priceCurrentItem / 10)].getRegionHeight() / 25f);
                            batch.draw(smallNumbers[priceCurrentItem % 10], (pricePos.x - lineWidth / 2 + smallNumbers[(int) Math.floor(priceCurrentItem / 10)].getRegionWidth() + 1) / 25f, (shopPos.y + 28) / 25f, smallNumbers[priceCurrentItem % 10].getRegionWidth() / 25f, smallNumbers[priceCurrentItem % 10].getRegionHeight() / 25f);
                        }
                        batch.draw(coinTex, (pricePos.x + lineWidth / 2f - coinTex.getRegionWidth()) / 25f, (shopPos.y + 27) / 25f, coinTex.getRegionWidth() / 25f, coinTex.getRegionHeight() / 25f);
                    }
                    tempBool = true;
                }
            }

            tempBool = false;
            for(int i = 0; i < unlocked.length; i++) {

                if (!unlocked[shopItem[i]] && tempBool && pageCurrent == i) {
                    batch.draw(locked_text, (pricePos.x - locked_text.getRegionWidth() / 2 - 1) / 25f, (shopPos.y + 28) / 25f, locked_text.getRegionWidth() / 25f, locked_text.getRegionHeight() / 25f);
                }

                if(!unlocked[shopItem[i]]){
                    tempBool = true;
                }
            }

            int tempCoins = displayCoins;
            if(displayCoins >= 10000) {
                displayCoins = 9999;
            }

            float lineWidth = smallNumbers[displayCoins % 10].getRegionWidth() + coinTex.getRegionWidth() + 2;
            if (displayCoins >= 10 && displayCoins < 100)
                lineWidth = smallNumbers[displayCoins % 10].getRegionWidth() + smallNumbers[(int) Math.floor(displayCoins / 10)].getRegionWidth() + coinTex.getRegionWidth() + 2 + 1;
            if (displayCoins >= 100 && displayCoins < 1000)
                lineWidth = smallNumbers[(displayCoins % 100) % 10].getRegionWidth() + smallNumbers[(int) Math.floor(displayCoins / 100)].getRegionWidth() + coinTex.getRegionWidth() + 2 + 1 + smallNumbers[(int) Math.floor((displayCoins % 10) / 10)].getRegionWidth() + 1;
            if (displayCoins >= 1000)
                lineWidth = smallNumbers[coins % 1000 % 100 % 10].getRegionWidth() + 1 + smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionWidth() + coinTex.getRegionWidth() + 2 + 1 + smallNumbers[(int) Math.floor((displayCoins % 1000 % 100) / 10)].getRegionWidth() + 1 + smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)].getRegionWidth();

            if (displayCoins < 10)
                batch.draw(smallNumbers[coins], (coinDisplayPos.x - lineWidth / 2) / 25f, coinDisplayPos.y / 25f, smallNumbers[displayCoins % 10].getRegionWidth() / 25f, smallNumbers[displayCoins % 10].getRegionHeight() / 25f);
            if (displayCoins >= 10 && coins < 100) {
                batch.draw(smallNumbers[(int) Math.floor(displayCoins / 10)], (coinDisplayPos.x - lineWidth / 2) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor(displayCoins / 10)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor(displayCoins / 10)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[displayCoins % 10], (coinDisplayPos.x - lineWidth / 2 + smallNumbers[(int) Math.floor(displayCoins / 10)].getRegionWidth() + 1) / 25f, coinDisplayPos.y / 25f, smallNumbers[displayCoins % 10].getRegionWidth() / 25f, smallNumbers[displayCoins % 10].getRegionHeight() / 25f);
            }
            if (displayCoins >= 100 && displayCoins < 1000) {
                batch.draw(smallNumbers[(int) Math.floor(displayCoins / 100)], (coinDisplayPos.x - lineWidth / 2) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor(displayCoins / 100)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor(displayCoins / 100)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[(int) Math.floor((displayCoins % 100) / 10)], (coinDisplayPos.x - lineWidth / 2 + 1 + smallNumbers[(int) Math.floor((displayCoins / 100))].getRegionWidth()) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor((displayCoins % 100) / 10)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor((displayCoins % 100) / 10)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[(displayCoins % 100) % 10], (coinDisplayPos.x - lineWidth / 2 + smallNumbers[(int) Math.floor(displayCoins / 100)].getRegionWidth() + 1 + smallNumbers[(int) Math.floor((displayCoins % 100) / 10)].getRegionWidth() + 1) / 25f, coinDisplayPos.y / 25f, smallNumbers[(displayCoins % 100) % 10].getRegionWidth() / 25f, smallNumbers[(displayCoins % 100) % 10].getRegionHeight() / 25f);
            }
            if (displayCoins >= 1000 && displayCoins < 10000) {
                batch.draw(smallNumbers[(int) Math.floor(displayCoins / 1000)], (coinDisplayPos.x - lineWidth / 2) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)], (coinDisplayPos.x - lineWidth / 2 + smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionWidth() + 1) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[(int) Math.floor((displayCoins % 1000 % 100) / 10)], (coinDisplayPos.x - lineWidth / 2 + 1 + smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)].getRegionWidth() + smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionWidth() + 1) / 25f, coinDisplayPos.y / 25f, smallNumbers[(int) Math.floor((displayCoins % 1000 % 100) / 10)].getRegionWidth() / 25f, smallNumbers[(int) Math.floor((displayCoins % 1000 % 100) / 10)].getRegionHeight() / 25f);
                batch.draw(smallNumbers[displayCoins % 1000 % 100 % 10], (coinDisplayPos.x - lineWidth / 2 + 1 + smallNumbers[(int) Math.floor(displayCoins / 1000)].getRegionWidth() + smallNumbers[(int) Math.floor((displayCoins % 1000) / 100)].getRegionWidth() + 1 + smallNumbers[(int) Math.floor((displayCoins % 1000 % 100) / 10)].getRegionWidth() + 1) / 25f, coinDisplayPos.y / 25f, smallNumbers[displayCoins % 1000 % 100 % 10].getRegionWidth() / 25f, smallNumbers[displayCoins % 1000 % 100 % 10].getRegionHeight() / 25f);
            }

            batch.draw(coinTex, (coinDisplayPos.x + lineWidth / 2f - coinTex.getRegionWidth()) / 25f, (coinDisplayPos.y - 1) / 25f, coinTex.getRegionWidth() / 25f, coinTex.getRegionHeight() / 25f);

            displayCoins = tempCoins;
        }

        for(Particle p : particles)
            if(p.type > 0)
                p.render(batch);


        if(gameOver){

            batch.draw(timeuptext, NewWidth / 2f - timeuptext.getRegionWidth() / 50f, 140 / 25f, timeuptext.getRegionWidth() / 25f, timeuptext.getRegionHeight() / 25f);
            batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, b5o);
            if(score >= 5) {
                batch.draw(medal[medalScore], MathUtils.ceil((scoreboardPos.x + 73 - medalWidth / 2)) / 25.0f, MathUtils.ceil(102 - medalWidth / 2) / 25.0f, medalWidth / 25.0f, medalWidth / 25.0f);

            }
            batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 1);

            if (score < 10)
                batch.draw(number, (scoreboardPos.x + 28 - number.getRegionWidth() / 2) / 25f, (scoreboardPos.y + 40) / 25f + numberShake / 25f, number.getRegionWidth() / 25f, number.getRegionHeight() / 25f);
            if (score >= 10) {
                batch.draw(number, (scoreboardPos.x + 28 - number.getRegionWidth() / 2) / 25f + number_second.getRegionWidth() / 2 / 25f, (scoreboardPos.y + 40) / 25f + numberShake / 25f, number.getRegionWidth() / 25f, number.getRegionHeight() / 25f);
                batch.draw(number_second, (scoreboardPos.x + 28 - number.getRegionWidth() / 2) / 25f + number_second.getRegionWidth() / 2 / 25f - number_second.getRegionWidth() / 25f - 1 / 25f, (scoreboardPos.y + 40) / 25f + numberShake / 25f, number_second.getRegionWidth() / 25f, number_second.getRegionHeight() / 25f);
            }

            if (highscore < 10)
                batch.draw(highscoreNumber, (scoreboardPos.x + 28 - highscoreNumber.getRegionWidth() / 2) / 25f, (scoreboardPos.y + 7) / 25f, highscoreNumber.getRegionWidth() / 25f, highscoreNumber.getRegionHeight() / 25f);
            if (highscore >= 10) {
                batch.draw(highscoreNumber, (scoreboardPos.x + 28 - highscoreNumber.getRegionWidth() / 2) / 25f + highscoreNumber_second.getRegionWidth() / 2 / 25f, (scoreboardPos.y + 7) / 25f, highscoreNumber.getRegionWidth() / 25f, highscoreNumber.getRegionHeight() / 25f);
                batch.draw(highscoreNumber_second, (scoreboardPos.x + 28 - highscoreNumber.getRegionWidth() / 2) / 25f + highscoreNumber_second.getRegionWidth() / 2 / 25f - highscoreNumber_second.getRegionWidth() / 25f - 1 / 25f, (scoreboardPos.y + 7) / 25f, highscoreNumber_second.getRegionWidth() / 25f, highscoreNumber_second.getRegionHeight() / 25f);
            }
            if (highscore >= 100) {
                batch.draw(highscoreNumber, NewWidth / 2 - (highscoreNumber.getRegionWidth() / 25f + highscoreNumber_third.getRegionWidth() / 25f + highscoreNumber_second.getRegionWidth() / 25f) / 2, (scoreboardPos.y + 7) / 25f, highscoreNumber.getRegionWidth() / 25f, highscoreNumber.getRegionHeight() / 25f);
                batch.draw(highscoreNumber_second, NewWidth / 2 - (highscoreNumber.getRegionWidth() / 25f + highscoreNumber_third.getRegionWidth() / 25f + highscoreNumber_second.getRegionWidth() / 25f) / 2 + (highscoreNumber.getRegionWidth() + 1) / 25f, (scoreboardPos.y + 7) / 25f, highscoreNumber_second.getRegionWidth() / 25f, highscoreNumber_second.getRegionHeight() / 25f);
                batch.draw(highscoreNumber_third, NewWidth / 2 - (highscoreNumber.getRegionWidth() / 25f + highscoreNumber_third.getRegionWidth() / 25f + highscoreNumber_second.getRegionWidth() / 25f) / 2 + (highscoreNumber.getRegionWidth() + 2 + highscoreNumber_second.getRegionWidth()) / 25f, (scoreboardPos.y + 7) / 25f, highscoreNumber_third.getRegionWidth() / 25f, highscoreNumber_third.getRegionHeight() / 25f);
            }

            if(coinsCollected > 0) {
                float lineWidth = numberCoinsCollected.getRegionWidth() + plusCharacter.getRegionWidth() + coinTex.getRegionWidth() + 2 + 1;
                if (coinsCollected >= 10)
                    lineWidth = numberCoinsCollected.getRegionWidth() + numberCoinsCollected_second.getRegionWidth() + plusCharacter.getRegionWidth() + coinTex.getRegionWidth() + 2 + 1 + 1;

                batch.draw(plusCharacter, (numberCoinsCollectedPos.x - lineWidth / 2) / 25f, (numberCoinsCollectedPos.y + 1) / 25f, plusCharacter.getRegionWidth() / 25f, plusCharacter.getRegionHeight() / 25f);

                if (coinsCollected < 10)
                    batch.draw(numberCoinsCollected, (numberCoinsCollectedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1) / 25f, numberCoinsCollectedPos.y / 25f, numberCoinsCollected.getRegionWidth() / 25f, numberCoinsCollected.getRegionHeight() / 25f);
                if (coinsCollected >= 10) {
                    batch.draw(numberCoinsCollected_second, (numberCoinsCollectedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1) / 25f, numberCoinsCollectedPos.y / 25f, numberCoinsCollected_second.getRegionWidth() / 25f, numberCoinsCollected_second.getRegionHeight() / 25f);
                    batch.draw(numberCoinsCollected, (numberCoinsCollectedPos.x - lineWidth / 2 + plusCharacter.getRegionWidth() + 1 + numberCoinsCollected_second.getRegionWidth() + 1) / 25f, numberCoinsCollectedPos.y / 25f, numberCoinsCollected.getRegionWidth() / 25f, numberCoinsCollected.getRegionHeight() / 25f);
                }

                batch.draw(coinTex, (numberCoinsCollectedPos.x + lineWidth / 2f - coinTex.getRegionWidth()) / 25f, (numberCoinsCollectedPos.y - 1) / 25f, coinTex.getRegionWidth() / 25f, coinTex.getRegionHeight() / 25f);
            }


        }


        if((playState && !gameOver) || (gameOver && !canUnlockFish)) {
            batch.draw(timeBar, NewWidth / 2 - timeBar.getRegionWidth() / 25f / 2, timeBarHeight / 25f, timeBar.getRegionWidth() / 25f, timeBar.getRegionHeight() / 25f);
            batch.draw(timeDot[dotColor], timeDotPos.x / 25f - timeDot[dotColor].getRegionWidth() / 25f / 2, timeDotPos.y / 25f + timeDotShake / 25f / 3, timeDot[dotColor].getRegionWidth() / 25f, timeDot[dotColor].getRegionHeight() / 25f);
        }


        if(showRateWindow){
            batch.draw(ratewindow, (NewWidth / 2f - ratewindow.getRegionWidth() / 50f) - (NewWidth / 2f - ratewindow.getRegionWidth() / 50f) % (1 / 25f), NewHeight / 2f - ratewindow.getRegionHeight() / 50, ratewindow.getRegionWidth() / 25f, ratewindow.getRegionHeight() / 25f);
        }

        for(int i = 1; i < button.length; i++){
            if(buttonPressed == i + 1){ buttonOffset = 1; }
            if(i != 7 && i != 10 && buttonVisible[i])
                batch.draw(button[i], buttonPos[i].x / 25f, (buttonPos[i].y - buttonOffset) / 25f, button[i].getRegionWidth() / 25f, button[i].getRegionHeight() / 25f);
            buttonOffset = 0;
        }

        if(canUnlockFish && gameOver){
            batch.draw(newfishtext, (buttonPos[9].x + button[9].getRegionWidth() / 2f - newfishtext.getRegionWidth() / 2f) / 25f, (buttonPos[9].y - 25 + unlockfishtextOffset) / 25f, newfishtext.getRegionWidth() / 25f, newfishtext.getRegionHeight() / 25f);
        }


        //batch.draw(rope, NewWidth / 2, NewHeight / 2, rope.getWidth() / 25f, rope.getHeight() / 25f);
        //batch.draw(outline, 0, 0, outline.getWidth() / 25f, outline.getHeight() / 25f);
        //batch.draw(img, 0, 0, img.getWidth() / 25f, img.getHeight() / 25f);


/*
		for(int i = 0; i < ropeSegments.length; i++){
			ropeSprite.setRotation(ropeSegments[i].getAngle() * MathUtils.radiansToDegrees);
			ropeSprite.setPosition(ropeSegments[i].getPosition().x - rope2x2.getWidth() / 25f / 2, ropeSegments[i].getPosition().y - rope2x2.getHeight() / 25f / 2);
			ropeSprite.setSize(rope2x2.getWidth() / 25f, rope2x2.getHeight() / 25f);
			ropeSprite.draw(batch);
			ropeSprite.draw(batch);
		}*/


        if(showTutorial && gameReady) {
            //batch.draw(catchtext, NewWidth / 2f - catchtext.getRegionWidth() / 50f, (120 + catchtextOffset) / 25f, catchtext.getRegionWidth() / 25f, catchtext.getRegionHeight() / 25f);

            if(!fishOnRight)
                batch.draw(catchtext, fishBodies.get(fishBodies.size() - 1).getPosition().x + 10 / 25f, fishBodies.get(fishBodies.size() - 1).getPosition().y - catchtextflip.getRegionHeight() / 50f, catchtext.getRegionWidth() / 25f, catchtext.getRegionHeight() / 25f);
            else
                batch.draw(catchtextflip, fishBodies.get(fishBodies.size() - 1).getPosition().x - 10 / 25f - catchtextflip.getRegionWidth() / 25f, fishBodies.get(fishBodies.size() - 1).getPosition().y - catchtextflip.getRegionHeight() / 50f, catchtextflip.getRegionWidth() / 25f, catchtextflip.getRegionHeight() / 25f);

            if(cat.onGround || cat.inFloor) {
                batch.draw(taptext, cat.position.x / 25f - taptext.getRegionWidth() / 50f + 2 / 25f, (48 + taptextOffset) / 25f, taptext.getRegionWidth() / 25f, taptext.getRegionHeight() / 25f);
                batch.draw(hand[handType], handPos.x / 25f - (int) (hand[handType].getRegionWidth() / 2 / 25f), handPos.y / 25f - (int) (hand[handType].getRegionHeight() / 2 / 25f), hand[handType].getRegionWidth() / 25f, hand[handType].getRegionHeight() / 25f);
            }
        }

        if(showTutorial2){
            batch.draw(timetext, NewWidth / 2 - timetext.getRegionWidth() / 50f + 2 / 25f, (48 + taptextOffset) / 25f, timetext.getRegionWidth() / 25f, timetext.getRegionHeight() / 25f);
            batch.draw(arrowDown, timeDotPos.x / 25f - arrowDown.getRegionWidth() / 50f, (18 + taptextOffset) / 25f, arrowDown.getRegionWidth() / 25f, arrowDown.getRegionHeight() / 25f);
            timeSpeed = 0.5f;
        }
        if(showCoinWindow){
            if(buttonPressed == 11){ buttonOffset = 1; }
            batch.draw(coinwindow, (NewWidth / 2f - coinwindow.getRegionWidth() / 50f), NewHeight / 2f - coinwindow.getRegionHeight() / 50, coinwindow.getRegionWidth() / 25f, coinwindow.getRegionHeight() / 25f);
            batch.draw(button[10], buttonPos[10].x / 25f, (buttonPos[10].y - buttonOffset - 5) / 25f, button[10].getRegionWidth() / 25f, button[10].getRegionHeight() / 25f);
            buttonOffset = 0;
        }

        batch.end();

        //b2dr.render(world, cam.combined);

        if(!Freeze)
            if(!doNotStep)
                if(frames > 5)
                    world.step(Gdx.graphics.getDeltaTime(), 8, 3);

        frames++;
    }

    boolean doNotStep;
    void restart(){

        gameReady = false;
        rbg_.set(1,1,1);
        numberHeight = 200;
        tutorial2Finished = false;
        if(newHighscore && gamesPlayed > 2)
            adMob.showInterstitial();

        if(newHighscore){
            int r = (int)(Math.random() * 3);
            if(r == 0 && gamesPlayed > 5){
                showRateWindow(true);
            }
        }

        newHighscore = false;

        cat.floor = this.floor + 6;
        cat.position.x = MainClass.NewWidth * 25 / 2;

        cat.catFallReset = true;
        coinsCollected = 0;
        canTakeFish = true;
        anglePos.y = NewHeight + anglePosOffset / 25f + sumFishBodies(fishLength);
        System.out.println(menuFish + " = MenuFish");
        newFish(menuFish);
        cat.fishTakeBite(0, 0, menuFish);
        dotColor = 0;
        buttonPos[0].y = 3;
        buttonPos[3].y = 4;
        buttonPos[8].y = 4;
        timeDotPos.x = NewWidth * 25 / 2 + timeBar.getRegionWidth() / 2 - 5;
        tillGameOverCount = timeTillGameOver;
        scoreboardPos.y = -scoreboardTex.getRegionHeight() - 5;
        gameOver = false;
        playState = false;
        playStateCount = 0;
        score = 0;
        gameOverOnceCheck = false;
        tutorialFinished = false;
        tutorialLoop = 0;

    }
    @Override
    public void dispose () {
        soundAlarm.dispose();
        soundBreak.dispose();
        soundGameover.dispose();
        soundPick.dispose();
        soundUnlock.dispose();
        gpgs.signOut();
        batch.dispose();
        atlas.dispose();
        cat.dispose();
    }

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();
    Body grabBody;


    Sprite ropeSprite;
    public static Body[] fishBody;
    public static Vector2[] fishSize;
    ArrayList<Joint> fishJoints;
    RevoluteJointDef jointDef;

    int fishLength;

    float averageDensity = 0.1f;
    float extraSegmentDensity = 0.5f;

    void newFish(int type){

        float totalSurface;

        float densityPerSegment;
        System.out.println("newFish()");
        for(Joint fishJoint: fishJoints) {
            world.destroyJoint(fishJoint);
        }
        for(Body fishSegs: fishBodies){
            fishSegs.destroyFixture(fishSegs.getFixtureList().get(0));
            world.destroyBody(fishSegs);
        }
        if(extraSegment != null) {
            extraSegment.destroyFixture(extraSegment.getFixtureList().get(0));
            world.destroyBody(extraSegment);
        }
        fishJoints.clear();
        fishBodies.clear();

        bodyDef.position.set(ropeSegments[ropeSegments.length - 2].getPosition().x, ropeSegments[ropeSegments.length - 2].getPosition().y);

        fixtureDef.filter.categoryBits = fishMask;
        fixtureDef.filter.maskBits = (short)(wallMask | groundMask);

        ropeJointDef.localAnchorA.set(0, 0);
        ropeJointDef.localAnchorB.set(0, 0);

        switch(type){
            case 1:

                fishLength = 4;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);
                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1) + 2 * height;
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 4];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                ropeJointDef.maxLength = sumFishBodies(fishBodies.size());
                ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                ropeJointDef.bodyB = fishBodies.get(fishBodies.size() - 1);
                fishJoints.add(world.createJoint(ropeJointDef));

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 2:

                fishLength = 4;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 10 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 14 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);
                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++) {
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1) + 2 * height;
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 4];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }


                ropeJointDef.maxLength = sumFishBodies(fishBodies.size());
                ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                ropeJointDef.bodyB = fishBodies.get(fishBodies.size() - 1);
                fishJoints.add(world.createJoint(ropeJointDef));

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 3:

                fishLength = 10;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 14 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[4] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[5] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[6] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[7] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[8] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[9] = new Vector2(10 / 25f, 10 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);
                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }
                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1) + 2 * height;
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 4];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 2 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y + fishSize[i+ 2].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 2);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 3 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y + fishSize[i + 2].y + fishSize[i + 3].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 3);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 5 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y + fishSize[i + 2].y + fishSize[i + 3].y + fishSize[i + 4].y + fishSize[i + 5].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 5);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                ropeJointDef.maxLength = sumFishBodies(fishBodies.size());
                ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                ropeJointDef.bodyB = fishBodies.get(fishBodies.size() - 1);
                fishJoints.add(world.createJoint(ropeJointDef));

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 4:

                fishLength = 4;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);

                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                /*
                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1) + 2 * height;
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 4];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }
                */


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                /*
                ropeJointDef.maxLength = sumFishBodies(fishBodies.size());
                ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                ropeJointDef.bodyB = fishBodies.get(fishBodies.size() - 1);
                fishJoints.add(world.createJoint(ropeJointDef));
                */

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 5:

                fishLength = 6;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 14 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[4] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[5] = new Vector2(10 / 25f, 16 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);

                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                /*
                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1) + 2 * height;
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 4];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }
                */


                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                /*
                ropeJointDef.maxLength = sumFishBodies(fishBodies.size());
                ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                ropeJointDef.bodyB = fishBodies.get(fishBodies.size() - 1);
                fishJoints.add(world.createJoint(ropeJointDef));
                */

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 6:

                fishLength = 6;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 15 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[4] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[5] = new Vector2(10 / 25f, 15 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);

                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
            case 7:

                fishLength = 7;

                fishSize = new Vector2[20];
                fishSize[0] = new Vector2(10 / 25f, 10 / 25f);
                fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[2] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[3] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[4] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[5] = new Vector2(10 / 25f, 8 / 25f);
                fishSize[6] = new Vector2(10 / 25f, 16 / 25f);

                fixtureDef.density = getDensityPerSegment();

                for(int i = 0; i < fishLength; i++){
                    shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
                    fixtureDef.shape = shape;
                    fishBodies.add(world.createBody(bodyDef));
                    fishBodies.get(i).createFixture(fixtureDef);

                }

                shape.setAsBox((5f / 25), 3 / 25f / 2);
                fixtureDef.density = extraSegmentDensity;
                fixtureDef.filter.maskBits = 0;

                extraSegment = world.createBody(bodyDef);
                extraSegment.createFixture(fixtureDef);

                for(int i = 0; i < fishBodies.size(); i++){
                    ropeJointDef.maxLength = sumFishBodies(i + 1);
                    ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                    ropeJointDef.bodyB = fishBodies.get(i);
                    fishJoints.add(world.createJoint(ropeJointDef));
                }

                for(int i = 0; i < fishBodies.size(); i++){
                    if(i + 1 < fishBodies.size()) {
                        ropeJointDef.maxLength = fishSize[i].y / 2 + fishSize[i + 1].y / 2;
                        ropeJointDef.bodyA = fishBodies.get(i);
                        ropeJointDef.bodyB = fishBodies.get(i + 1);
                        fishJoints.add(world.createJoint(ropeJointDef));
                    }
                }

                jointDef = new RevoluteJointDef();
                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                jointDef.localAnchorA.set(0, -height / 2);
                jointDef.localAnchorB.set(0, fishSize[0].y / 2 - height);
                jointDef.bodyA = ropeSegments[ropeSegments.length - 1];
                jointDef.bodyB = fishBodies.get(0);
                fishJoints.add(world.createJoint(jointDef));

                for(int i = 0; i < fishBodies.size() - 1; i++){
                    jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
                    jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
                    jointDef.bodyA = fishBodies.get(i);
                    jointDef.bodyB = fishBodies.get(i + 1);
                    fishJoints.add(world.createJoint(jointDef));
                }

                jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
                jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
                jointDef.bodyA = fishBodies.get(fishBodies.size() - 1);
                jointDef.bodyB = extraSegment;
                fishJoints.add(world.createJoint(jointDef));

                break;
        }
    }

    float getDensityPerSegment(){
        float totalSurface = 0;
        for(int i = 0; i < fishLength; i++){
            totalSurface += fishSize[i].x * 25 * fishSize[i].y * 25;
        }
        float densityPerSegment = 320 / totalSurface * averageDensity;
        return densityPerSegment;
    }

    float sumFishBodies(int size){
        float sum = 0;
        sum += height / 2;
        for(int i = 0; i < size - 1; i++){
            sum += fishSize[i].y;
        }
        sum += fishSize[size - 1].y / 2;
        return sum;
    }

    float coinsToAdd;
    public void addCoins(int coinsToAdd){
        this.coinsToAdd += coinsToAdd;
        coins += coinsToAdd;
        prefs.putInteger("coins", coins);
        prefs.flush();
    }

    public static ArrayList<Body> fishBodies;
    Body extraSegment;
    PolygonShape shape;
    BodyDef bodyDef;
    float width, height;

    RopeJointDef ropeJointDef;
    Body[] createFishingRod(int length){
        fishBody = new Body[3];

        ropeSegments = new Body[length];
        width = 2 / 25f;
        height = 6 / 25f;
        float ropeDensity = 1;
        shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        fixtureDef = new FixtureDef();
        fixtureDef.density = ropeDensity;
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = ropeMask;
        fixtureDef.filter.maskBits = groundMask;
        bodyDef = new BodyDef();
        bodyDef.position.set(NewWidth / 2, NewHeight + 15 / 25f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        for(int i = 0; i < ropeSegments.length; i++) {
            if(i >= length - 30)
                fixtureDef.filter.maskBits = (short)(wallMask | groundMask);
            fixtureDef.density = ropeDensity;
            ropeSegments[i] = world.createBody(bodyDef);
            ropeSegments[i].createFixture(fixtureDef);
            ropeDensity -= 0.7f / length;
        }


        /*
        fishSize = new Vector2[3];
        fishSize[0] = new Vector2(10 / 25f, 7 / 25f);
        fishSize[1] = new Vector2(10 / 25f, 8 / 25f);
        fishSize[2] = new Vector2(10 / 25f, 11 / 25f);

        fixtureDef.density = 0.05f;
        fixtureDef.filter.categoryBits = fishMask;
        fixtureDef.filter.maskBits = (short)(wallMask);
        for(int i = 0; i < 3; i++){
            shape.setAsBox(fishSize[i].x / 2, fishSize[i].y / 2);
            fishBody[i] = world.createBody(bodyDef);
            fishBody[i].createFixture(fixtureDef);
        }*/

        /*
        shape.setAsBox((5f / 25), 3 / 25f / 2);
        fixtureDef.density = 0.6f;
        fixtureDef.filter.maskBits = 0;
        Body extraSegment;
        extraSegment = world.createBody(bodyDef);
        extraSegment.createFixture(fixtureDef);
        */



        RevoluteJointDef jointDef = new RevoluteJointDef();
        /*
        jointDef.localAnchorA.set(0, -height / 2);
        jointDef.localAnchorB.set(0, fishSize[0].y / 2);
        jointDef.bodyA = ropeSegments[length - 2];
        jointDef.bodyB = fishBody[0];

        world.createJoint(jointDef);


        jointDef.localAnchorA.set(0, -height / 2);
        jointDef.localAnchorB.set(0, fishSize[0].y / 2 - (height + height / 2));
        jointDef.bodyA = ropeSegments[length - 1];
        jointDef.bodyB = fishBody[0];

        world.createJoint(jointDef);

        for(int i = 0; i < fishBody.length - 1; i++){
            jointDef.localAnchorA.set(0, -fishSize[i].y / 2);
            jointDef.localAnchorB.set(0, fishSize[i + 1].y / 2);
            jointDef.bodyA = fishBody[i];
            jointDef.bodyB = fishBody[i + 1];
            world.createJoint(jointDef);
        }

        jointDef.localAnchorA.set(0, -fishSize[fishBody.length - 1].y / 2);
        jointDef.localAnchorB.set(0, 3f / 25 / 2 / 2);
        jointDef.bodyA = fishBody[fishBody.length - 1];
        jointDef.bodyB = extraSegment;
        world.createJoint(jointDef);
        */

        /*
        jointDef.localAnchorA.set(0, -(fishSize[0].y + fishSize[1].y + fishSize[2].y / 2 + height / 2) / 2);
        jointDef.localAnchorB.set(0, (fishSize[0].y + fishSize[1].y + fishSize[2].y / 2 + height / 2) / 2);
        jointDef.bodyA = ropeSegments[ropeSegments.length - 2];
        jointDef.bodyB = fishBody[fishBody.length - 1];
        world.createJoint(jointDef);
*/

        /*
        jointDef.localAnchorA.set(0, -fishSize[fishSize.length - 1].y / 2);
        jointDef.localAnchorB.set(0, height / 2);
        jointDef.bodyA = fishBody[fishBody.length - 1];
        jointDef.bodyB = extraSegment;
        world.createJoint(jointDef);
        */

        RevoluteJoint[] joints = new RevoluteJoint[length - 1];
        jointDef.localAnchorA.set(0, -height / 2);
        jointDef.localAnchorB.set(0, height / 2);

        for(int i = 0; i < joints.length; i++) {
            jointDef.bodyA = ropeSegments[i];
            jointDef.bodyB = ropeSegments[i + 1];
            joints[i] = (RevoluteJoint) world.createJoint(jointDef);
        }

		/*
		jointDef.localAnchorA.set(0, -height * 2 / 2);
		jointDef.localAnchorB.set(0, height * 2 / 2);

		for(int i = 0; i < joints.length; i++) {
			if(i + 2 <= length - 1) {
				jointDef.bodyA = ropeSegments[i];
				jointDef.bodyB = ropeSegments[i + 2];
				world.createJoint(jointDef);
			}
		}
*/
        RopeJoint[] ropeJoints = new RopeJoint[length];
        ropeJointDef = new RopeJointDef();

        ropeJointDef.maxLength = height;
        ropeJointDef.localAnchorA.set(0, 0);
        ropeJointDef.localAnchorB.set(0, 0);

        for(int i = 0; i < length; i++) {
            if(i + 1 <= ropeSegments.length - 1) {
                ropeJointDef.bodyA = ropeSegments[i];
                ropeJointDef.bodyB = ropeSegments[i + 1];
                world.createJoint(ropeJointDef);
            }
        }



        ropeJointDef.maxLength = height * 2;
        for(int i = 0; i < length; i++) {
            if(i + 2 <= ropeSegments.length - 1) {
                ropeJointDef.bodyA = ropeSegments[i];
                ropeJointDef.bodyB = ropeSegments[i + 2];
                world.createJoint(ropeJointDef);
            }
        }



        /*
        ropeJointDef.maxLength = height * 3;
        for(int i = 0; i < length; i++) {
            if(i + 3 <= ropeSegments.length - 1) {
                ropeJointDef.bodyA = ropeSegments[i];
                ropeJointDef.bodyB = ropeSegments[i + 3];
                world.createJoint(ropeJointDef);
            }
        }
        */


		ropeJointDef.maxLength = height * 5;
		for(int i = 0; i < length; i++) {
			if(i + 5 <= ropeSegments.length - 1) {
				ropeJointDef.bodyA = ropeSegments[i];
				ropeJointDef.bodyB = ropeSegments[i + 5];
				world.createJoint(ropeJointDef);
			}
		}

/*

		ropeJointDef.maxLength = height * 4;
		for(int i = 0; i < length; i++) {
			if(i + 4 <= ropeSegments.length - 1) {
				ropeJointDef.bodyA = ropeSegments[i];
				ropeJointDef.bodyB = ropeSegments[i + 4];
				world.createJoint(ropeJointDef);
			}
		}


*/
		ropeJointDef.maxLength = height * 10;
		for(int i = 0; i < length; i++) {
			if(i + 10 <= ropeSegments.length - 1) {
				ropeJointDef.bodyA = ropeSegments[i];
				ropeJointDef.bodyB = ropeSegments[i + 10];
				world.createJoint(ropeJointDef);
			}
		}


        /*
        ropeJointDef.maxLength = height / 2 + fishSize[0].y / 2;
        ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
        ropeJointDef.bodyB = fishBody[0];
        world.createJoint(ropeJointDef);

        ropeJointDef.maxLength = height / 2 + fishSize[0].y + fishSize[1].y / 2;
        ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
        ropeJointDef.bodyB = fishBody[1];
        world.createJoint(ropeJointDef);

        ropeJointDef.maxLength = height / 2 + fishSize[0].y + fishSize[1].y + fishSize[2].y / 2;
        ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
        ropeJointDef.bodyB = fishBody[2];
        world.createJoint(ropeJointDef);
        */

        /*
        ropeJointDef.maxLength = height / 2 + fishSize[0].y + fishSize[1].y + fishSize[2].y + 3f / 2 / 25 / 2;
        ropeJointDef.bodyA = ropeSegments[ropeSegments.length - 2];
        ropeJointDef.bodyB = extraSegment;
        world.createJoint(ropeJointDef);
*/



        ropeJointDef.maxLength = height * (length);
        ropeJointDef.bodyA = ropeSegments[0];
        ropeJointDef.bodyB = ropeSegments[length - 1];

        ropeJointDef.localAnchorA.set(0, 0);
        ropeJointDef.localAnchorB.set(0,0);

        //world.createJoint(ropeJointDef);

        ropeSegments[0].setType(BodyDef.BodyType.KinematicBody);
        ropeSegments[0].setFixedRotation(true);


        //System.out.println(fishBodies.get(0));

        return ropeSegments;
    }

    private QueryCallback queryCallback = new QueryCallback() {

        @Override
        public boolean reportFixture(Fixture fixture) {
            if(!fixture.testPoint(tmp.x, tmp.y))
                return true;
            if(playState){
                return true;
            }
            grabBody = fixture.getBody();
            mouseJointDef.bodyB = fixture.getBody();
            mouseJointDef.target.set(tmp.x, tmp.y);
            mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);

            return false;
        }

    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cam.unproject(tmp.set(screenX, screenY, 0));
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(mouseJoint == null)
            return false;

        cam.unproject(tmp.set(screenX, screenY, 0));

        mouseJoint.setTarget(tmp2.set(tmp.x, tmp.y));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(mouseJoint == null)
            return false;

        world.destroyJoint(mouseJoint);
        mouseJoint = null;
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
