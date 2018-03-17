package com.oxigenoxide.catfishing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

import java.util.ArrayList;

/**
 * Created by Frederik on 2/28/2017.
 */
public class Cat {

    TextureRegion tex;
    TextureRegion cat_jump;
    TextureRegion cat_holding;
    TextureRegion cat_holding_extra;
    TextureRegion cat;
    TextureRegion[] cat_head;
    TextureRegion texExtra;
    public Vector2 position;
    public Vector2 velocity;
    float GRAVITY = -9.8f / 15;
    public boolean onGround = true;
    public int onGroundCount = 2;
    int floor;
    boolean letAngleGo;
    public static int letAngleGoTrigger;
    boolean inFloor;
    Sprite ball;
    Vector2 ballPos;
    Sound soundJump;
    Sound soundFall;
    TextureRegion cat_ready;
    public boolean catReady;
    TextureRegion floorTex;

    TextureRegion[][] fishPieceFlesh;
    TextureRegion[][] fishPieceSkeleton;
    public static int segmentsEaten;
    float step;
    ArrayList<Sprite> fishPiece;
    Body pullBody;

    TextureRegion tail;
    TextureRegion[] tailTex;
    ArrayList<Trail> trails;
    ArrayList<Trail> trailremove;

    float floorPos;
    int trailDelay = 2;



    public static Body pointBody;
    public Cat(){
        trails = new ArrayList<Trail>();
        trailremove = new ArrayList<Trail>();
        fishPiece = new ArrayList<Sprite>();
        fishPiece.add(new Sprite(MainClass.atlas.findRegion("fishPiece", 0)));
        fishPiece.add(new Sprite(MainClass.atlas.findRegion("fishPiece", 1)));
        fishPiece.add(new Sprite(MainClass.atlas.findRegion("fishPiece", 2)));

        fishPieceFlesh = new TextureRegion[8][12];
        /*
        fishPieceFlesh[0][0] = Assets.manager.get(Assets.fishPiece_0);
        fishPieceFlesh[0][1] = Assets.manager.get(Assets.fishPiece_1);
        fishPieceFlesh[0][2] = Assets.manager.get(Assets.fishPiece_2);

        fishPieceFlesh[1][0] = Assets.manager.get(Assets.fishPiece_sardine_0);
        fishPieceFlesh[1][1] = Assets.manager.get(Assets.fishPiece_sardine_1);
        fishPieceFlesh[1][2] = Assets.manager.get(Assets.fishPiece_sardine_2);
        fishPieceFlesh[1][3] = Assets.manager.get(Assets.fishPiece_sardine_3);
        fishPieceFlesh[1][4] = Assets.manager.get(Assets.fishPiece_sardine_4);
        fishPieceFlesh[1][5] = Assets.manager.get(Assets.fishPiece_sardine_5);
        */

        fishPieceFlesh[0][0] = MainClass.atlas.findRegion("fishPiece_tuna", 0);
        fishPieceFlesh[0][1] = MainClass.atlas.findRegion("fishPiece_tuna", 1);
        fishPieceFlesh[0][2] = MainClass.atlas.findRegion("fishPiece_tuna", 2);
        fishPieceFlesh[0][3] = MainClass.atlas.findRegion("fishPiece_tuna", 3);

        fishPieceFlesh[1][0] = MainClass.atlas.findRegion("fishPiece_blob", 0);
        fishPieceFlesh[1][1] = MainClass.atlas.findRegion("fishPiece_blob", 1);
        fishPieceFlesh[1][2] = MainClass.atlas.findRegion("fishPiece_blob", 2);
        fishPieceFlesh[1][3] = MainClass.atlas.findRegion("fishPiece_blob", 3);

        fishPieceFlesh[2][0] = MainClass.atlas.findRegion("fishPiece_eel", 0);
        fishPieceFlesh[2][1] = MainClass.atlas.findRegion("fishPiece_eel", 1);
        fishPieceFlesh[2][2] = MainClass.atlas.findRegion("fishPiece_eel", 1);
        fishPieceFlesh[2][3] = MainClass.atlas.findRegion("fishPiece_eel", 2);
        fishPieceFlesh[2][4] = MainClass.atlas.findRegion("fishPiece_eel", 3);
        fishPieceFlesh[2][5] = MainClass.atlas.findRegion("fishPiece_eel", 3);
        fishPieceFlesh[2][6] = MainClass.atlas.findRegion("fishPiece_eel", 4);
        fishPieceFlesh[2][7] = MainClass.atlas.findRegion("fishPiece_eel", 5);
        fishPieceFlesh[2][8] = MainClass.atlas.findRegion("fishPiece_eel", 5);
        fishPieceFlesh[2][9] = MainClass.atlas.findRegion("fishPiece_eel", 6);

        fishPieceFlesh[3][0] = MainClass.atlas.findRegion("fishPiece_clown", 0);
        fishPieceFlesh[3][1] = MainClass.atlas.findRegion("fishPiece_clown", 1);
        fishPieceFlesh[3][2] = MainClass.atlas.findRegion("fishPiece_clown", 2);
        fishPieceFlesh[3][3] = MainClass.atlas.findRegion("fishPiece_clown", 3);

        fishPieceFlesh[4][0] = MainClass.atlas.findRegion("fishPiece_sailfish", 0);
        fishPieceFlesh[4][1] = MainClass.atlas.findRegion("fishPiece_sailfish", 1);
        fishPieceFlesh[4][2] = MainClass.atlas.findRegion("fishPiece_sailfish", 2);
        fishPieceFlesh[4][3] = MainClass.atlas.findRegion("fishPiece_sailfish", 3);
        fishPieceFlesh[4][4] = MainClass.atlas.findRegion("fishPiece_sailfish", 4);
        fishPieceFlesh[4][5] = MainClass.atlas.findRegion("fishPiece_sailfish", 5);

        fishPieceFlesh[5][0] = MainClass.atlas.findRegion("fishPiece_dolphin", 0);
        fishPieceFlesh[5][1] = MainClass.atlas.findRegion("fishPiece_dolphin", 1);
        fishPieceFlesh[5][2] = MainClass.atlas.findRegion("fishPiece_dolphin", 2);
        fishPieceFlesh[5][3] = MainClass.atlas.findRegion("fishPiece_dolphin", 3);
        fishPieceFlesh[5][4] = MainClass.atlas.findRegion("fishPiece_dolphin", 4);
        fishPieceFlesh[5][5] = MainClass.atlas.findRegion("fishPiece_dolphin", 5);

        fishPieceFlesh[6][0] = MainClass.atlas.findRegion("fishPiece_nar", 0);
        fishPieceFlesh[6][1] = MainClass.atlas.findRegion("fishPiece_nar", 1);
        fishPieceFlesh[6][2] = MainClass.atlas.findRegion("fishPiece_nar", 2);
        fishPieceFlesh[6][3] = MainClass.atlas.findRegion("fishPiece_nar", 3);
        fishPieceFlesh[6][4] = MainClass.atlas.findRegion("fishPiece_nar", 4);
        fishPieceFlesh[6][5] = MainClass.atlas.findRegion("fishPiece_nar", 5);
        fishPieceFlesh[6][6] = MainClass.atlas.findRegion("fishPiece_nar", 6);


        fishPieceSkeleton = new TextureRegion[8][12];
        /*
        fishPieceSkeleton[0][0] = Assets.manager.get(Assets.fishPiece_skeleton_0);
        fishPieceSkeleton[0][1] = Assets.manager.get(Assets.fishPiece_skeleton_1);
        fishPieceSkeleton[0][2] = Assets.manager.get(Assets.fishPiece_skeleton_2);
        fishPieceSkeleton[1][0] = Assets.manager.get(Assets.fishPiece_skeleton_0);
        fishPieceSkeleton[1][1] = Assets.manager.get(Assets.fishPiece_skeleton_1);
        fishPieceSkeleton[1][2] = Assets.manager.get(Assets.fishPiece_skeleton_1);
        fishPieceSkeleton[1][3] = Assets.manager.get(Assets.fishPiece_skeleton_1);
        fishPieceSkeleton[1][4] = Assets.manager.get(Assets.fishPiece_skeleton_1);
        fishPieceSkeleton[1][5] = Assets.manager.get(Assets.fishPiece_skeleton_2);
*/
        fishPieceSkeleton[0][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[0][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[0][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[0][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);


        fishPieceSkeleton[1][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[1][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[1][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[1][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);

        fishPieceSkeleton[2][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[2][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][4] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][5] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][6] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][7] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][8] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[2][9] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);

        fishPieceSkeleton[3][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[3][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[3][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[3][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);

        fishPieceSkeleton[4][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[4][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[4][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[4][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[4][4] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[4][5] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);

        fishPieceSkeleton[5][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[5][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[5][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[5][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[5][4] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[5][5] = MainClass.atlas.findRegion("fishPiece_skeleton", 2);

        fishPieceSkeleton[6][0] = MainClass.atlas.findRegion("fishPiece_skeleton", 0);
        fishPieceSkeleton[6][1] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][2] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][3] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][4] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][5] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][5] = MainClass.atlas.findRegion("fishPiece_skeleton", 1);
        fishPieceSkeleton[6][6] = MainClass.atlas.findRegion("fishPiece_nar_skeleton", 2);
        //fishPiece[0] = new Sprite(Assets.manager.get(Assets.fishPiece_skeleton_0));
        //fishPiece[1] = new Sprite(Assets.manager.get(Assets.fishPiece_skeleton_1));
        //fishPiece[2] = new Sprite(Assets.manager.get(Assets.fishPiece_skeleton_2));

        for(int i = 0; i < fishPiece.size(); i++) {
            fishPiece.get(i).setSize(fishPiece.get(i).getTexture().getWidth() / 25f, fishPiece.get(i).getTexture().getHeight() / 25f);
            fishPiece.get(i).setOriginCenter();
        }
        step = MainClass.step;
        soundFall = Gdx.audio.newSound(Gdx.files.internal("Sounds/land.wav"));
        soundJump = Gdx.audio.newSound(Gdx.files.internal("Sounds/jump.wav"));
        floorTex = MainClass.atlas.findRegion("floor");cat = MainClass.atlas.findRegion("Cat", 2);
        cat_jump = MainClass.atlas.findRegion("Cat_Jump");cat_holding = MainClass.atlas.findRegion("Cat_Holding", 0);cat_holding_extra = MainClass.atlas.findRegion("Cat_Holding", 1);
        tex = MainClass.atlas.findRegion("Cat", 2);

        floorPos = MainClass.floor;
        floor = MainClass.floor + 6;
        position = new Vector2((int)(MainClass.NewWidth * 25f / 2), floor);velocity = new Vector2(0,0);ballPos = new Vector2(0,0);

        cat_ready = MainClass.atlas.findRegion("Cat_Head_Ready");
        cat_head = new TextureRegion[4];
        cat_head[0] = MainClass.atlas.findRegion("Cat_Head", 0);
        cat_head[1] = MainClass.atlas.findRegion("Cat_Head", 1);
        cat_head[2] = MainClass.atlas.findRegion("Cat_Head", 2);
        cat_head[3] = MainClass.atlas.findRegion("Cat_Head", 1);

        tailTex = new TextureRegion[4];
        tailTex[0] = MainClass.atlas.findRegion("Cat_Tail", 0);
        tailTex[1] = MainClass.atlas.findRegion("Cat_Tail", 1);
        tailTex[2] = MainClass.atlas.findRegion("Cat_Tail", 2);
        tailTex[3] = MainClass.atlas.findRegion("Cat_Tail", 1);


        /*
        BodyDef pointDef = new BodyDef();
        pointDef.type = BodyDef.BodyType.StaticBody;
        pointDef.position.set(0,0);

        PolygonShape pointShape = new PolygonShape();
        pointShape.setAsBox(5 / 25f, 5 / 25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = pointShape;
        fixtureDef.filter.maskBits = 0;

        pointBody = MainClass.world.createBody(pointDef);
        */

        BodyDef pointDef = new BodyDef();
        pointDef.type = BodyDef.BodyType.StaticBody;
        pointDef.position.set(0,0);

        PolygonShape pointShape = new PolygonShape();
        pointShape.setAsBox(1 / 25f, 1 / 25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = pointShape;
        fixtureDef.filter.maskBits = 0;

        pullBody = MainClass.world.createBody(pointDef);
        pullJointTarget = new Vector2(0,0);

        position.x = MainClass.NewWidth * 25 / 2;
        floor = MainClass.floor + 6;
        inFloor = true;
    }

    boolean onLanding;
    int inAirCount;
    int timeOnAngle_count;
    boolean catSink;
    boolean catSinkTrigger;
    int aniCount;
    int gigago;
    Vector2 pullJointTarget;
    boolean bounced;
    public static MouseJoint pullJoint;
    boolean fallTrigger;
    boolean catSunk;
    boolean catFallReset;
    int tailAniCount;
    int blinkAniCount;
    int onGroundTrigger;

    void update(){



        if(trailDelay <= 0){
            trailDelay = 2;
            if(MainClass.playState)
                trails.add(new Trail(position.x, position.y));
        }
        trailDelay--;

        for(Trail trail: trails){
            trail.update();
            if(trail.remove){
                trailremove.add(trail);
            }
        }

        for(Trail trail: trailremove){
            trails.remove(trail);
        }

        trailremove.clear();

        if(MainClass.playState && floor != MainClass.floor){
            floor = MainClass.floor;
            inFloor = false;
        }


        pullBody.setTransform(position.x / 25f, 5 / 25f, 0);
        pullJointTarget.set(position.x / 25f, 0);
        if(pullJoint != null)
            pullJoint.setTarget(pullJointTarget);

        //pointBody.setTransform(position.x / 25f, position.y / 25f, 0);
        //System.out.println(pointBody.getPosition());
        //velocity.scl(dt);
        if(!(MainClass.takeAngleDown && !catSink)) {
            if (!(position.x + velocity.x > MainClass.NewWidth * 25 - cat_jump.getRegionWidth() / 2 || position.x + velocity.x < cat_jump.getRegionWidth() / 2))
                position.x += velocity.x * (Gdx.graphics.getDeltaTime() / step);
            else if (position.x + velocity.x > MainClass.NewWidth * 25 - cat_jump.getRegionWidth() / 2) {
                position.x = MainClass.NewWidth * 25 - cat_jump.getRegionWidth() / 2;
                if(!bounced) {
                    velocity.x *= -0.5f;
                    bounced = true;
                }
            }
            else if (position.x + velocity.x < cat_jump.getRegionWidth() / 2) {
                position.x = cat_jump.getRegionWidth() / 2;
                if(!bounced) {
                    velocity.x *= -0.5f;
                    bounced = true;
                }
            }
            position.y += velocity.y * (Gdx.graphics.getDeltaTime() / step);
        }

        if(position.y == floor && inFloor){
            onGroundTrigger++;
            onGround = true;
        }
        else
            onGround = false;


        if(!onGround)
            inAirCount++;

        if(onGround)
            inAirCount = 0;


        if(inFloor){
            onGroundCount++;
            if(!onGround) {
                tex = cat;
            }
        }

        if(onGround && tex == cat){
            tex = cat_head[0];
        }

        if(onGround){
            tailAniCount++;
            if(tailAniCount >= 60){
                tailAniCount = 0;
            }

            if(tailAniCount <= 20){
                tail = tailTex[0];
            }
            if(tailAniCount > 20) {
                tail = tailTex[(int) Math.floor((tailAniCount - 20) / 10)];
            }
        }

        blinkAniCount++;

        if(blinkAniCount >= 152)
            blinkAniCount = (int)Math.random() * 140;

        if(onGround){
            if(blinkAniCount <= 140)
                tex = cat_head[0];

            if(blinkAniCount > 140) {
                tex = cat_head[(int) Math.floor((blinkAniCount - 140) / 3)];
            }
        }

        if(!inFloor && !onGround && !MainClass.takeAngleDown){
            tex = cat_jump;
        }
        if(MainClass.takeAngleDown){
            tex = cat_holding;
            texExtra = cat_holding_extra;
        }
        if(onGroundCount == 1)
            soundFall.play();

        if(!inFloor && !onGround) {
            if (!MainClass.takeAngleDown) {
                velocity.add(0, GRAVITY * (Gdx.graphics.getDeltaTime() / step));
            }
            else
                velocity.set(0,0);
        }
        else
            velocity.set(0,0);


        //System.out.println(timeOnAngle_count);

        if(catSinkTrigger){
            onGroundTrigger = 0;
            catSunk = true;
            MouseJointDef mDef = new MouseJointDef();
            mDef.bodyA = MainClass.ropeSegments[0];
            mDef.bodyB = MainClass.fishBodies.get(MainClass.fishBodies.size() -1);
            mDef.maxForce = 1.0f;
            mDef.target.set(MainClass.fishBodies.get(MainClass.fishBodies.size() -1).getPosition().x, MainClass.fishBodies.get(MainClass.fishBodies.size() -1).getPosition().y - 0 / 25f);
            pullJoint = (MouseJoint) MainClass.world.createJoint(mDef);
            pullJointTarget.set(position.x / 25f, 0);
            //MainClass.world.setGravity(new Vector2(0, -50));

            for(int i = 0; i < MainClass.ropeSegments.length - 1; i++){
                MainClass.ropeSegments[i + 1].setLinearVelocity(MainClass.ropeSegments[i + 1].getLinearVelocity().x / 5f, MainClass.ropeSegments[i + 1].getLinearVelocity().x - 10);
            }
            /*
            float velY;
            int fishBodiesSize = MainClass.fishBodies.size();
            for(int i = 0; i < fishBodiesSize; i++){
                velY = - 25 - 35 * ((i + 1) / fishBodiesSize);
                MainClass.fishBodies.get(i).setLinearVelocity(MainClass.fishBodies.get(i).getLinearVelocity().x / 5f, velY);
            }
            */
            catSink = true;
            catSinkTrigger = false;
            /*
            MainClass.takeFishJoint = new RopeJoint[2];

            for(int i = 0; i < MainClass.takeFishJoint.length; i ++) {
                if (MainClass.takeFishJoint[i] == null) {
                    RopeJointDef takeFishJointDef = new RopeJointDef();
                    switch(i) {
                        case 0:
                            takeFishJointDef.localAnchorA.set(0, -(MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f) / 2f);
                            takeFishJointDef.localAnchorB.set(0, (MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f) / 2f);
                            takeFishJointDef.bodyA = MainClass.fishBodies.get(MainClass.fishBodies.size() - (1 + 1));
                            takeFishJointDef.maxLength = MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f;
                            break;
                        case 1:
                            takeFishJointDef.localAnchorA.set(0, -(MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y + MainClass.fishSize[MainClass.fishBodies.size() - (1 + 2)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f) / 2f);
                            takeFishJointDef.localAnchorB.set(0, (MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y + MainClass.fishSize[MainClass.fishBodies.size() - (1 + 2)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f) / 2f);
                            takeFishJointDef.bodyA = MainClass.fishBodies.get(MainClass.fishBodies.size() - (1 + 2));
                            takeFishJointDef.maxLength = MainClass.fishSize[MainClass.fishBodies.size() - (1 + 1)].y + MainClass.fishSize[MainClass.fishBodies.size() - (1 + 2)].y / 2f + MainClass.fishSize[MainClass.fishBodies.size() - (1)].y / 2f;
                            break;
                    }
                    takeFishJointDef.bodyB = Cat.pointBody;
                    MainClass.takeFishJoint[i] = (RopeJoint) MainClass.world.createJoint(takeFishJointDef);
                }
            }
*/
        }

        if(timeOnAngle_count > 20 && !catSunk && !inFloor){
            System.out.println("onAngle = 10");
            catSinkTrigger = true;
            velocity.set(0,0);
        }

        if(catSink){
            //velocity.set(0,0);
            //MainClass.fishBodies.get(MainClass.fishBodies.size() - 1).setType(BodyDef.BodyType.StaticBody);
            //MainClass.fishBodies.get(MainClass.fishBodies.size() - 1).setTransform(position.x / 25f, position.y / 25f, 0);
        }

        if(MainClass.takeAngleDown) {
            timeOnAngle_count++;
            //if(!catSink)
            //MainClass.fishBodies.get(MainClass.fishBodies.size() - 1).getFixtureList().get(0).setDensity(20);
            position.set(MainClass.fishBodies.get(MainClass.fishBodies.size() - 1).getPosition().x * 25, MainClass.fishBodies.get(MainClass.fishBodies.size() - 1).getPosition().y * 25 - 4);
        }

        if(letAngleGoTrigger == 1){
            timeOnAngle_count = 0;
            MainClass.world.destroyJoint(pullJoint);
            pullJoint = null;
            letAngleGoTrigger++;
            letAngleGo = true;
        }


        if(fallTrigger = true)
            fallTrigger = false;

        if(position.y + tex.getRegionHeight() / 2f < floor && !inFloor){
            catFallReset = true;
            if(MainClass.takeAngleDown) {
                fishTakeBite(1, 100, MainClass.fishType);
            }
            fallTrigger = true;
            onGroundCount = 0;
        }


        if(catFallReset){
            catFallReset = false;
            bounced = false;
            position.y = floor - (tex.getRegionHeight()) / 2;

            MainClass.catVelocityAfterFall.set(velocity);
            if(MainClass.takeAngleDown) {
                letAngleGoTrigger = 1;
            }
            velocity.set(0,0);
            inFloor = true;
            catSink = false;
            catSink = false;
            catSunk = false;
            //position.y = floor;
            //position.y += 1;
        }

        if(position.y < floor) {
            position.y += 1.5f;
            if(position.y + 1f > floor)
                position.y = floor;
        }

        /*
        if(!catSink)
            ball.setPosition(MainClass.ropeSegments[MainClass.ropeSegments.length - 1].getPosition().x - ball.getWidth()/ 2, MainClass.ropeSegments[MainClass.ropeSegments.length - 1].getPosition().y - ball.getHeight() / 2);
        else
            ball.setPosition(position.x / 25f - ball.getWidth() / 2, (position.y + 5) / 25f - ball.getHeight() / 2);

*/
        if(catReady)
            tex = cat_ready;

        //ball.setPosition(MainClass.ropeSegments[0].getPosition().x, MainClass.ropeSegments[0].getPosition().y);
        //ball.setRotation(MainClass.ropeSegments[MainClass.ropeSegments.length - 3].getAngle() * MathUtils.radiansToDegrees);


        for(int i = 0; i < fishPiece.size(); i++){
            fishPiece.get(i).setRotation(MainClass.fishBodies.get(i).getAngle() * MathUtils.radiansToDegrees);
            fishPiece.get(i).setPosition(MainClass.fishBodies.get(i).getPosition().x - fishPiece.get(i).getWidth() / 2, MainClass.fishBodies.get(i).getPosition().y - fishPiece.get(i).getHeight() / 2);
        }


    }

    void newFishTextures(int type){
    }

    int lastRecordedFishType;
    void fishTakeBite(int bites, int setBite, int fishType){
        System.out.println("fishTakeBite()");
        if(segmentsEaten < MainClass.fishBodies.size())
        segmentsEaten += bites;

        if(setBite != 100)
            segmentsEaten = setBite;


            fishPiece.clear();

        int count = 0;
            for (int i = 0; i < MainClass.fishBodies.size(); i++) {
                count++;
                System.out.println(count + " " + (fishType - 1) + " " + ((MainClass.fishBodies.size() - 1) - i));
                fishPiece.add(new Sprite(fishPieceFlesh[fishType - 1][(MainClass.fishBodies.size() - 1) - i]));
            }


        for(int i = 0; i < segmentsEaten; i++){
            fishPiece.get(fishPiece.size() - i - 1).setRegion(fishPieceSkeleton[fishType - 1][fishPiece.size() - i - 1]);
            fishPiece.get(fishPiece.size() - i - 1).setSize(fishPiece.get(fishPiece.size() - i - 1).getRegionWidth() / 25f, fishPiece.get(fishPiece.size() - i - 1).getRegionHeight() / 25f);
            fishPiece.get(fishPiece.size() - i - 1).setOriginCenter();
        }

        for(int i = 0; i < MainClass.fishBodies.size() - segmentsEaten; i++){
            fishPiece.get(i).setRegion(fishPieceFlesh[fishType - 1][i]);
            fishPiece.get(i).setSize(fishPiece.get(i).getRegionWidth() / 25f, fishPiece.get(i).getRegionHeight() / 25f);
            fishPiece.get(i).setOriginCenter();
        }
        lastRecordedFishType = fishType;
    }

    void jump(float velx, float vely){
        letAngleGo = false;
        if(onGround && catReady) {
            MainClass.showJumpExplosion = true;
            inFloor = false;
            velocity.add(velx, vely);
            //velocity.scl(Gdx.graphics.getDeltaTime() / step);
            soundJump.play(0.1f);
        }
        catReady = false;
    }
    void render(SpriteBatch batch){


        for(Trail trail: trails){
            trail.render(batch);
        }


        if(tex == cat) {
            batch.draw(tex, (position.x - tex.getRegionWidth() / 2 + 3) / 25f, (position.y - tex.getRegionWidth() / 2) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);
        }

        if(tex == cat_holding)
            batch.draw(tex, (position.x - tex.getRegionWidth() / 2) / 25f, (position.y - (tex.getRegionWidth() + 12) / 2) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);

        if(tex == cat_jump)
            batch.draw(tex, (position.x - tex.getRegionWidth() / 2) / 25f, (position.y - (tex.getRegionWidth() + 12) / 2) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);

        //ball.draw(batch);
        for(int i = 0; i < fishPiece.size(); i++)
            fishPiece.get(i).draw(batch);

        if(MainClass.takeAngleDown){
            batch.draw(texExtra, (position.x - tex.getRegionWidth() / 2) / 25f, (position.y - (tex.getRegionWidth() + 12) / 2) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);}

        batch.draw(floorTex, (-floorTex.getRegionWidth() / 2) / 25f + MainClass.NewWidth / 2, (floorPos - floorTex.getRegionHeight()) / 25f, floorTex.getRegionWidth() / 25f, floorTex.getRegionHeight() / 25f);


        if(MainClass.NewWidth * 25f > floorTex.getRegionWidth()){
            batch.draw(floorTex, (-floorTex.getRegionWidth() / 2 + floorTex.getRegionWidth()) / 25f + MainClass.NewWidth / 2, (floorPos - floorTex.getRegionHeight()) / 25f, floorTex.getRegionWidth() / 25f, floorTex.getRegionHeight() / 25f);
            batch.draw(floorTex, (-floorTex.getRegionWidth() / 2 - floorTex.getRegionWidth()) / 25f + MainClass.NewWidth / 2, (floorPos - floorTex.getRegionHeight()) / 25f, floorTex.getRegionWidth() / 25f, floorTex.getRegionHeight() / 25f);
        }



        int buttonOffset = 0;

        if(MainClass.buttonPressed == 1)
            buttonOffset = 1;

        batch.draw(MainClass.button[0], MainClass.buttonPos[0].x / 25f, (MainClass.buttonPos[0].y - buttonOffset) / 25f, MainClass.button[0].getRegionWidth() / 25f, MainClass.button[0].getRegionHeight() / 25f);

        if(tex == cat_ready)
            batch.draw(tex, (position.x - tex.getRegionWidth() / 2) / 25f, (position.y - ((tex.getRegionWidth()) / 2) + 10) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);

        if(onGround && tex != cat_ready) {
            batch.draw(tail, (position.x - tail.getRegionWidth() / 2) / 25f, (position.y - ((tail.getRegionWidth()) / 2) + 11) / 25f, tail.getRegionWidth() / 25f, tail.getRegionHeight() / 25f);
            batch.draw(tex, (position.x - tex.getRegionWidth() / 2) / 25f, (position.y - ((tex.getRegionWidth()) / 2) + 11) / 25f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);
        }
    }

    void dispose(){
        soundFall.dispose();
        soundJump.dispose();
    }
}
