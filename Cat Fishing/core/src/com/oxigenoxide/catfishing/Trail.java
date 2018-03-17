package com.oxigenoxide.catfishing;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Frederik on 5/16/2017.
 */
public class Trail {
    boolean remove;
    TextureRegion tex;
    TextureRegion[] trail;
    int initTime = 20;
    int time = initTime;
    int x,y;
    public Trail(float x, float y){
        trail = new TextureRegion[6];
        trail[0] = MainClass.atlas.findRegion("trail", 8);
        trail[1] = MainClass.atlas.findRegion("trail", 7);
        trail[2] = MainClass.atlas.findRegion("trail", 6);
        trail[3] = MainClass.atlas.findRegion("trail", 5);
        trail[4] = MainClass.atlas.findRegion("trail", 4);
        trail[5] = MainClass.atlas.findRegion("trail", 3);
        //trail[6] = MainClass.atlas.findRegion("trail", 2);
        //trail[7] = MainClass.atlas.findRegion("trail", 1);
        this.x = (int)x; this.y = (int)y;
    }

    public void update(){
        time--;
        tex = trail[(int)Math.floor((float)time / ((float)initTime / (float)(trail.length - 1)))];
        if(time <= 0){
            remove = true;
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(tex, x / 25f - tex.getRegionWidth() / 50f, y / 25f - tex.getRegionHeight() / 50f, tex.getRegionWidth() / 25f, tex.getRegionHeight() / 25f);
    }
}
