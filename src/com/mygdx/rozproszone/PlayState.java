/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.rozproszone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 *
 * @author Admin
 */
public class PlayState extends GameState {

    private Player player1;
    private Level level;
    
    private Float angle =360.0f;
    private Float angleDelta = 5.0f;
    private Float oldX;
    private Float oldY;
    private Float newX;
    private Float newY;
    boolean velocityFlag = false;
    boolean keyPressedFlag;
    
    public PlayState(GameStateManager gsm) {
        super(gsm);
        player1= new Player(80,100,"player1.png");
        level=new Level("plansza.jpg");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            velocityFlag = true;
            newX = new Float (Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt);
            newY = new Float (Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt);
            checkCollision(newX,newY);
            checkSlowDust(newX,newY);
            
            player1.changePosition(newX,newY );
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                player1.getCarImage().rotate(-angleDelta);
                angle += angleDelta;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                player1.getCarImage().rotate(angleDelta);
                angle -= angleDelta;
            }
            if(player1.getVelocity() < 300) player1.changeVelocity(10);
            
            if(player1.getVelocity()> 150)angleDelta = new Float (600/player1.getVelocity());
        }
        
        
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            velocityFlag = false;
            newX = new Float (-Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt);
            newY = new Float (-Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt);
            checkCollision(newX,newY);
            checkSlowDust(newX,newY);
            
            player1.changePosition(newX, newY);
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                player1.getCarImage().rotate(-angleDelta);
                angle += angleDelta;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                player1.getCarImage().rotate(angleDelta);
                angle -= angleDelta;
            }
            
            if(player1.getVelocity() < 100) player1.changeVelocity(5);
            
            if(player1.getVelocity() > 50)angleDelta = new Float (600/player1.getVelocity());
            
            
        }
        else if(player1.getVelocity() > 0)
        {
            if (player1.getAbleToMove()) {
                
                if (velocityFlag) {
                    newX = new Float (Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt);
                    newY = new Float (Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt);
                    player1.changeVelocity(-10);
                    checkCollision(newX,newY);
                    
                    checkSlowDust(newX,newY);
                    
                    player1.changePosition(newX, newY);
                    //player1.setPositionX((Float) (player1.getPositionX()+Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt));
                    // player1.setPositionY((Float) (player1.getPositionY()+Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt));
                    
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    {
                        player1.getCarImage().rotate(-1);
                        angle += 1;
                    }
                    else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    {
                        player1.getCarImage().rotate(1);
                        angle -= 1;
                    }
                    
                    
                }
                else{
                    player1.changeVelocity(-5);
                    newX = new Float((-Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt));
                    newY = new Float((-Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt));
                    checkCollision(newX,newY);
                    checkSlowDust(newX,newY);
                    
                    player1.changePosition(newX, newY);
                    // player1.setPositionX((Float) (player1.getPositionX()-Math.sin(angle*Math.PI/180)*player1.getVelocity()*dt));
                    //player1.setPositionY((Float) (player1.getPositionY()-Math.cos(angle*Math.PI/180)*player1.getVelocity()*dt));
                    
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    {
                        player1.getCarImage().rotate(-1);
                        angle += 1;
                    }
                    else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    {
                        player1.getCarImage().rotate(1);
                        angle -= 1;
                    }
                    
                }
                
                
            }
            else{
                player1.setVelocity(0);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        level.startView();
        level.draw(player1);
        level.updateView();
    }

    @Override
    public void handleInput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void checkCollision(Float x, Float y){
        for (int i = 0; i < level.getCollisionObjects().getCount(); i++) {
            RectangleMapObject obj = (RectangleMapObject) level.getCollisionObjects().get(i);
            Rectangle rect = obj.getRectangle();
            Rectangle recPlayer = new Rectangle();
            recPlayer.set(player1.getPositionX()+x,player1.getPositionY()+y,player1.getCarImage().getWidth()-6,player1.getCarImage().getHeight()-20);
            
            
            if (recPlayer.overlaps(rect)) {
                newX = 0.0f;
                newY = 0.0f;
                break;
            }
            
        }
    }
    
    void checkSlowDust(Float x, Float y){
        for (int i = 0; i < level.getSlowDustObjects().getCount(); i++) {
            RectangleMapObject obj = (RectangleMapObject) level.getSlowDustObjects().get(i);
            Rectangle rect = obj.getRectangle();
            Rectangle recPlayer = new Rectangle();
            recPlayer.set(player1.getPositionX()+x,player1.getPositionY()+y,player1.getCarImage().getWidth(),player1.getCarImage().getHeight());
            
            
            if (recPlayer.overlaps(rect)) {
                newX /=2;
                newY /=2;
                break;
            }
        }
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}