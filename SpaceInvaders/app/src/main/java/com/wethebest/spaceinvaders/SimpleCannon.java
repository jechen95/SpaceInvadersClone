package com.wethebest.spaceinvaders;

import android.graphics.PointF;

/*@SimpleCannon
* This game object shoots projectiles and is controlled by the player.
* Movement is horizontally left or right and is determined by the device's accelerometer sensor.
* When the player shoots, a PlayerProj object at the position of the cannon.
* The cannon starts with MAX_LIVES number of lives and has a short interval of invincibility when hit.
*/
public class SimpleCannon extends GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.player;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.player_invincible;

    private final int INVINCIBLE_SECONDS = 2; //how long cannon is invincible
    private final int FIRING_RATE = 1; //fire every second
    public final int MAX_LIVES = 3;
    public int lives = MAX_LIVES;

    private boolean playShoot = false;

    private Counter waitToShoot = new Counter(FIRING_RATE);
    private Counter invincible = new Counter(INVINCIBLE_SECONDS);

    SimpleCannon(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }

    public void update(long fps) {
        if(((SpaceInvaders)app.context).yAcceleration >= .08f //tilt thresholds for cannon to stay still
                || ((SpaceInvaders)app.context).yAcceleration <= -.08f)
        {
            //change this multiplying constant to change movement speed
            mHitBox.moveHorizontally(((SpaceInvaders)app.context).yAcceleration * 10);
            mHitBox.horizontalStayInBounds();
        }

        if(invincible.on && !invincible.isCountingDown) {
            invincible.setFPS(fps);
        }
        else if(invincible.on && invincible.isCountingDown) {
            if (invincible.finished()) {
                mHitBox.setBitmap(SPRITE_ID);
            }
        }

        if(waitToShoot.on && !waitToShoot.isCountingDown) {
            waitToShoot.setFPS(fps);
        }
        else if(waitToShoot.on && waitToShoot.isCountingDown) {
            waitToShoot.finished();
        }

    }

    public void playAudio(){
        if(playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
    }

    public void collide(GameObject gameObject) {
        if(gameObject instanceof AlienProj) {
            if(!invincible.on) {
                lives -= 1;
                reset();
                invincible.on = true;
                mHitBox.setBitmap(INVINCIBLE_SPRITE_ID);
            }
        }
    }

    public GameObject shoot() {
            GameObject mProj = GameObjectFactory.getGameObject("PlayerProj");
            mProj.setPosition(mHitBox.centerTop());
            playShoot = true;
            return mProj;
    }

    public boolean canShoot() {
        if(waitToShoot.on) {
            return false; //if player cant shoot yet, return false
        }

        waitToShoot.on = true; //player must wait after this shot
        return true; //shoot a projectile
    }

}