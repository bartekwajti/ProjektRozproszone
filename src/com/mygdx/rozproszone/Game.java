package com.mygdx.rozproszone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;


public class Game extends ApplicationAdapter {
    
    
    
    
    private Player player1;
    private Level level;
    
    private float angle =360.0f;
    private float angleDelta = 5;
    private float oldX;
    private float oldY;
    private float newX;
    private float newY;
    boolean velocityFlag = false;
    boolean keyPressedFlag;
    
    @Override
    public void create () {
        player1= new Player(80,100,"assets//player1.png");
        level=new Level("assets//plansza.jpg");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
    }
    
    @Override
    public void render () {
        
        level.startView();
        /*
        keyPressedFlag=false;
        if(Gdx.input.isKeyPressed(Keys.UP)){
        player1.speedUp(Gdx.graphics.getDeltaTime());
        keyPressedFlag=true;
        
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
        player1.slowDown(Gdx.graphics.getDeltaTime());
        keyPressedFlag=true;
        
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
        player1.toBreak(Gdx.graphics.getDeltaTime());
        keyPressedFlag=true;
        
        }
        if(!keyPressedFlag){
        player1.noAccelerateAction(Gdx.graphics.getDeltaTime());
        }
        /*/
        if(Gdx.input.isKeyPressed(Keys.UP))
        {
            velocityFlag = true;
            newX = (float) (Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
            newY = (float) (Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
            checkCollision(newX,newY);
            if (player1.getAbleToMove()) {
                checkSlowDust(newX,newY);
                if (player1.getSlowedDust()) {
                    newX/=2;
                    newY/=2;
                }
                player1.changePosition(newX,newY );
                if(Gdx.input.isKeyPressed(Keys.RIGHT))
                {
                    player1.getCarImage().rotate(-angleDelta);
                    angle += angleDelta;
                }
                else if(Gdx.input.isKeyPressed(Keys.LEFT))
                {
                    player1.getCarImage().rotate(angleDelta);
                    angle -= angleDelta;
                }
                if(player1.getVelocity() < 300) player1.changeVelocity(10);
                
                if(player1.getVelocity()> 150)angleDelta = (float) (600/player1.getVelocity());
            }
            
        }
        else if (Gdx.input.isKeyPressed(Keys.DOWN))
        {
            velocityFlag = false;
            newX = (float) (-Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
            newY = (float) (-Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
            checkCollision(newX,newY);
            if (player1.getAbleToMove()) {
                checkSlowDust(newX,newY);
                if (player1.getSlowedDust()) {
                    newX/=2;
                    newY/=2;
                }
                player1.changePosition(newX, newY);
                if(Gdx.input.isKeyPressed(Keys.RIGHT))
                {
                    player1.getCarImage().rotate(-angleDelta);
                    angle += angleDelta;
                }
                else if(Gdx.input.isKeyPressed(Keys.LEFT))
                {
                    player1.getCarImage().rotate(angleDelta);
                    angle -= angleDelta;
                }
                
                if(player1.getVelocity() < 100) player1.changeVelocity(5);
                
                if(player1.getVelocity() > 50)angleDelta = (float) (600/player1.getVelocity());
            }
            
        }
        else if(player1.getVelocity() > 0)
        {
            if (player1.getAbleToMove()) {
                
                if (velocityFlag) {
                    newX = (float) (Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
                    newY = (float) (Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
                    player1.changeVelocity(-10);
                    checkCollision(newX,newY);
                    
                    if (player1.getAbleToMove()) {
                        checkSlowDust(newX,newY);
                        if (player1.getSlowedDust()) {
                            newX/=2;
                            newY/=2;
                        }
                        player1.changePosition(newX, newY);
                        //player1.setPositionX((float) (player1.getPositionX()+Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime()));
                        // player1.setPositionY((float) (player1.getPositionY()+Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime()));
                        
                        if(Gdx.input.isKeyPressed(Keys.RIGHT))
                        {
                            player1.getCarImage().rotate(-1);
                            angle += 1;
                        }
                        else if(Gdx.input.isKeyPressed(Keys.LEFT))
                        {
                            player1.getCarImage().rotate(1);
                            angle -= 1;
                        }
                    }
                    
                }
                else{
                    player1.changeVelocity(-5);
                    newX = (float) (-Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
                    newY = (float) (-Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime());
                    checkCollision(newX,newY);
                    if (player1.getAbleToMove()) {
                        checkSlowDust(newX,newY);
                        if (player1.getSlowedDust()) {
                            newX/=2;
                            newY/=2;
                        }
                        player1.changePosition(newX, newY);
                        // player1.setPositionX((float) (player1.getPositionX()-Math.sin(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime()));
                        //player1.setPositionY((float) (player1.getPositionY()-Math.cos(angle*Math.PI/180)*player1.getVelocity()*Gdx.graphics.getDeltaTime()));
                        
                        if(Gdx.input.isKeyPressed(Keys.RIGHT))
                        {
                            player1.getCarImage().rotate(-1);
                            angle += 1;
                        }
                        else if(Gdx.input.isKeyPressed(Keys.LEFT))
                        {
                            player1.getCarImage().rotate(1);
                            angle -= 1;
                        }
                    }
                }
                
                
            }
            else{
                player1.setVelocity(0);
            }
            
        }
        //        /*/
        level.draw(player1);
        level.updateView();
    }
    
    void checkCollision(float x, float y){
        for (int i = 0; i < level.getCollisionObjects().getCount(); i++) {
            RectangleMapObject obj = (RectangleMapObject) level.getCollisionObjects().get(i);
            Rectangle rect = obj.getRectangle();
            Rectangle recPlayer = new Rectangle();
            recPlayer.set(player1.getPositionX()+x,player1.getPositionY()+y,player1.getCarImage().getWidth(),player1.getCarImage().getHeight());
            
            
            if (recPlayer.overlaps(rect)) {
                player1.setAbleToMove(false);
                break;
            }
            else {
                player1.setAbleToMove(true);
            }
            
            
        }
    }
    
    void checkSlowDust(float x, float y){
        for (int i = 0; i < level.getSlowDustObjects().getCount(); i++) {
            RectangleMapObject obj = (RectangleMapObject) level.getSlowDustObjects().get(i);
            Rectangle rect = obj.getRectangle();
            Rectangle recPlayer = new Rectangle();
            recPlayer.set(player1.getPositionX()+x,player1.getPositionY()+y,player1.getCarImage().getWidth(),player1.getCarImage().getHeight());
            
            
            if (recPlayer.overlaps(rect)) {
                player1.setSlowedDust(true);
                break;
            }
            else {
                player1.setSlowedDust(false);
            }
            
            
        }
    }
}
