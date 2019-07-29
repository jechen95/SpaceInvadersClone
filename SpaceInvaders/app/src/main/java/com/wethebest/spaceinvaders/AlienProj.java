package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

class AlienProj implements GameObject{
    SpaceInvadersApp app;

    public HitBox mHitBox;
    private Paint mPaint = new Paint();
    private boolean isActive = true;
    private float yVel;
    protected Point mScreenSize;
    private final PointF SIZE;
    private final int SPRITE_ID = R.drawable.alien_laser;

    AlienProj(SpaceInvadersApp app) {
        this.app = app;
        mScreenSize = app.mScreenSize;
        SIZE = new PointF(mScreenSize.x /160, mScreenSize.x / 40);
        yVel = mScreenSize.y/3;

        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).withVelocity(yVel).build();

    }

    @Override
    public void update(long fps){
        mHitBox.moveVertically(yVel/fps);
    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }

    public void playAudio(){
        //Creation and removal of projectile is handled in cannon and invader classes
    }

    public void reset(){
        //Probably will override supermethod in GameObject class
    }

    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive() {
        return isActive;
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof SimpleCannon || gameObject instanceof BarrierBlock)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}





    //OLD STUFF
//    AlienProj(SpaceInvadersApp app) {
//        super(app);
//        this.mBitmap = BitmapFactory.decodeResource(app.context.getResources(), R.drawable.alien_laser);
//        yVel = mScreenSize.x/3; //Projectile shoots down
//    }
//
//    @Override
//    public void collide (GameObject gameObject) {
//        if(!(gameObject instanceof Alien)) {
//            isActive = false; //AlienProj can't shoot other Aliens
//        }
//    }
//
//    public void checkBounds() {
//        if(mRect.top >= mScreenSize.y) {
//            isActive = false;
//        }
//    }
//}
