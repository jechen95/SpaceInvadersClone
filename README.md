For this project, we are implementing the classic arcade game, Space Invaders, as an Android application. In Space Invaders, the player controls a cannon that shoots laser beams at approaching waves of aliens. The player must shoot all the aliens to continue onto the next wave. To survive, the player must dodge enemy projectiles and strategically hide behind barriers. If the aliens reach the player or if the player loses all their lives, the player loses.  We expect to complete this project over the course of three one-week sprints.

## Team Members
* Idan Shemy
* James Lemkin
* Jeffrey Chen
* Muga Kim
* Sach Samala

## Design Document
* The game starts with Intro, displaying a main menu with buttons, one of which transitions to the SpaceInvaders activity.
* SpaceInvaders sets up the canvas, the display, and the accelerometer. It sets the view to SpaceInvadersApp.
* SpaceInvadersApp is a SurfaceView. It runs the game, handling game states such as WaveState. It instantiates GameObjectManager.
   * WaveState is where most of the game runs, using GameObjectManager methods.
   * GameObjectManager holds all of the GameObjects, updating, drawing, and removing them.  
* GameObject is an abstract class. Methods are used in GameObjectManager. It has a HitBox instance variable.
   * HitBox controls movement and drawing. It is instantiated using a builder. Parameters include size, sprite, position, and velocity.
* GameObjects are constructed with default values using the GameObjectFactory.
* GameObjectManager instantiates SimpleCannon, and GameObject containers AlienArmy, Barriers, and UFOSpawner.
   * Aliens are observers to AlienArmy.
   * Barriers construct Barrier objects, which set the BarrierBlocks.
   * AlienProj and PlayerProj are instantiated by their respective classes

## **Classes**   
## Activities
* **Intro** - main activity, main menu
    * **Settings** 
    * **Story** - explains background story
* **SpaceInvaders** - creates game
    * **BackgroundSoundService** - plays music
    * **GameOver**

## Views
* **SpaceInvadersApp** - game view, handles threading and states

## States
* **State** - enum: WAVE, PAUSE, GAMEOVER
* **GameState** - interface
    * **WaveState** - spawns enemies
    * **PauseState**
    * **GameOverState**

## Game Object
* **GameObject** - abstract class
    * **HitBox** - instance variable: controls movement and drawing
* **GameObjectFactory** - creates GameObjects with default values
* **GameObjectManager** - updates all GameObjects

### Game Objects
* **SimpleCannon** - the player
* **Alien**
* **AlienProj** - alien projectile
* **PlayerProj** - player projectile
* **BarrierBlock** - single block in a Barrier
* **UFO**

### Game Object Containers
* **AlienArmy** - controls Aliens
* **Barrier** - sets BarrierBlocks
* **Barriers** - sets multiple Barrier objects
* **UFOSpawner**

## Misc
* **SoundEngine** - plays sound effects
* **GameUI** - draws score, lives, ammo
* **Counter** - counts seconds by decrementing frames
    * **AutomaticCounter** - counts without being triggered
---
## Sprint 1
[Sprint Storyboard](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint1/Sprint_Storyboard.png)  
[CRC Cards](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint1/CRC_Cards.png)   
[Acceptance Criteria](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint1/Acceptance_Criteria.md)  
[Screenshots](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint1/Screenshots.md)  

## Sprint 2
[Sprint Storyboard](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint2/Sprint_Storyboard.png)  
[CRC Cards](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint2/CRC_Cards.png)  
[Acceptance Criteria](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint2/Acceptance_Criteria.md)  
[Screenshots](https://github.com/ecs160ss12019/Wethebest/blob/master/Sprint2/Screenshots.md)  

### Design Updates
* Sprites are drawn for all game objects
* Multiple aliens are spawned using observer pattern
* Multiple barriers are spawned
* Aliens shoot projectiles at random intervals
* GameObjects are created using GameObjectFactory
* GameObjects contain HitBox classes
