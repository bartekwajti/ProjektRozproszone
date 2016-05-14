/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.rozproszone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Admin
 */
public abstract class GameState {
    
    protected GameStateManager gsm;
    
    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }
    
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
