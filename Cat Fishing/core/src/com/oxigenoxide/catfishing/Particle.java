package com.oxigenoxide.catfishing;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Frederik on 4/14/2017.
 */
public class Particle {


    private static final float GRAVITY = -9.8f / 25;
    private Vector2 position;
    public Vector2 velocity;
    public boolean remove;
    private TextureRegion[] randomTex;
    private TextureRegion tex;
    int type;
    int disposeTime;
    boolean doFloorCollision = true;
    float sizeX;
    float sizeY;
    boolean disable;

    public Particle(float x, float y, float vx, float vy, int type, boolean doFloorCollision){

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        velocity.add(vx,vy);
        disposeTime = 60 + (int)(Math.random() * 20);
        this.type = type;


        switch(type) {
            case 0:
                randomTex = new TextureRegion[3];
                randomTex[0] = MainClass.atlas.findRegion("floorParticle", 0);
                randomTex[1] = MainClass.atlas.findRegion("floorParticle", 1);
                randomTex[2] = MainClass.atlas.findRegion("floorParticle", 2);
                break;
            case 1:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 0);
                break;
            case 2:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 1);
                break;
            case 3:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 2);
                break;
            case 4:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 3);
                break;
            case 5:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 4);
                break;
            case 6:
                tex = MainClass.atlas.findRegion("lockBreakParticle", 5);
                break;
            case 7:
                tex = MainClass.atlas.findRegion("Coin");
                break;
        }

        if(randomTex != null)
            tex = randomTex[MathUtils.random(0, randomTex.length - 1)];

        sizeX = tex.getRegionWidth() / 25f;
        sizeY = tex.getRegionHeight() / 25f;

        this.doFloorCollision = doFloorCollision;
    }

    public void update() {

        if(type == 7){
            tex = MainClass.coinTex;
        }

        disposeTime--;
        if(disposeTime <= 0 || position.y <= 0 - tex.getRegionHeight() / 2)
            remove = true;

        if(!disable) {
            velocity.add(0, GRAVITY);
            if (doFloorCollision) {
                if (position.x >= MainClass.NewWidth * 25 - tex.getRegionWidth() / 2 - 1 || position.x <= 0 + tex.getRegionWidth() / 2)
                    velocity.x = 0;
                if (position.y + velocity.y <= MainClass.floor + tex.getRegionHeight() / 2 - 2 && doFloorCollision) {
                    velocity.y = 0;
                    velocity.x = 0;
                    position.y = MainClass.floor + tex.getRegionHeight() / 2  - 2;
                    disable = true;
                }
            }
            //if(position.y + velocity.y > MainClass.floor - tex.getHeight())
            position.add(velocity.x, velocity.y);
        }
    }

    public void render(SpriteBatch sb){
        sb.draw(tex, (int)(position.x - tex.getRegionWidth() / 2) / 25f, (int)(position.y - tex.getRegionHeight() / 2) / 25f, sizeX, sizeY);
    }
}
