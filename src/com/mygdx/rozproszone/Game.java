package com.mygdx.rozproszone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;


public class Game extends ApplicationAdapter {
    
    
    GameStateManager gsm;
    SpriteBatch batch;

    boolean velocityFlag = false;
    boolean keyPressedFlag;
    
    @Override
    public void create () {
      
        Gdx.gl.glClearColor(0, 0, 0, 1);
        gsm = new GameStateManager();
        gsm.push(new PlayState(gsm));
        
        batch = new SpriteBatch();
    }
    
    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
        
       
    }
    
}
