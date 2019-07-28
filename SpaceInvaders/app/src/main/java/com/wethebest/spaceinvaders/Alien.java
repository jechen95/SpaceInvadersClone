package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

class Alien implements GameObject {
    //Needed for Context and ScreenSize
    private SpaceInvadersApp app;

    public AlienHitBox mHitBox;

    //All aliens have the same size and velocity
    public static PointF alienSize; //TODO set in AlienRow

    //Shoots projectiles randomly
    private AlienProj mProj;
    private static Random rand = new Random();
    private static Point shootInterval = new Point(5, 20); // shoots every 5-20 seconds
    private long framesUntilShoot;
    public boolean shootNow;
    private boolean waitingToShoot;

    private SoundEngine soundEngine;
    private Context context;

    Alien(SpaceInvadersApp app) {
        this.app = app;
        context = app.context;
        soundEngine = new SoundEngine(context);

        mHitBox = new AlienHitBox(app);
        alienSize = new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10);

        shootNow = false;
        waitingToShoot = false;
        framesUntilShoot = 0;

    }

    public void update(long fps) {
        mHitBox.update(fps);
        timeToShoot(fps);
        checkAlienWin();

    }

    public void display(Canvas canvas) {
        mHitBox.display(canvas);
    }

    public void playAudio() {
        if (shootNow) {
            //soundEngine.alienShoot();
        }
//        if(hit){
//            soundEngine.alientHit();
//        }
    }

    public RectF getHitBox() {
        return mHitBox.getHitBox();
    }

    public boolean outOfBounds() {
        return mHitBox.horizontalOutOfBounds();
    }

    public void reverseXVelocity() {
        mHitBox.reverseXVelocity();
    }


    public void setPos(PointF position) {
        mHitBox.setPosition(position);
    }

    public void reset(Point location) {
    }



    public void collide(GameObject gameObject) {
        mHitBox.collide(gameObject);
    }

    public static void setAlienSize(PointF size) {
        alienSize = size;
        AlienHitBox.alienSize = alienSize;
        //TODO change to setHitBoxSize and hitBoxSize?
    }

    public boolean isActive() {
        return mHitBox.isActive();
    }

    public AlienProj shoot() {
            mProj = new AlienProj(app);
            RectF tempRect = mHitBox.getHitBox();
            mProj.setPos((tempRect.right + tempRect.left) / 2, tempRect.bottom);
            return mProj;
    }

    //calculates when to shoot shooting by counting the number of frames
    private void timeToShoot(long fps) {
        if(!waitingToShoot) {
            int seconds = rand.nextInt(shootInterval.y - shootInterval.x) + shootInterval.x ; //random int in shooting interval
            framesUntilShoot = fps * seconds;
            waitingToShoot = true;
        }
        else if(waitingToShoot) {
            framesUntilShoot--;
            if (framesUntilShoot <= 0) {
                shootNow = true;
                waitingToShoot = false;
            }
        }

    }

    private void checkAlienWin() {
        if(mHitBox.bottomOutOfBounds()) {
            SimpleCannon.lives = 0; //game over when aliens reach bottom of screen
        }
    }
}