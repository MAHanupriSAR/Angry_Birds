package angry_bird.utils;

import angry_bird.Levels.Level;
import angry_bird.gameObjects.GameObject;
import angry_bird.gameObjects.blocks.Block;
import angry_bird.gameObjects.pigs.Pig;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

public class GameContactListener implements ContactListener {
    private final float damageScale;
    private final List<GameObject> objectsToDestroy;
    private Level level;

    public GameContactListener(Level level, float damageScale){
        this.damageScale = damageScale;
        this.objectsToDestroy = new ArrayList<>();
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();

        if (userDataA instanceof Pig) {
            applyDamageToPig((Pig) userDataA, impulse, contact.getFixtureB());
        } else if (userDataB instanceof Pig) {
            applyDamageToPig((Pig) userDataB, impulse, contact.getFixtureA());
        }
        else if(userDataA instanceof Block){
            applyDamageToBlocks((Block) userDataA, impulse);
        }
        else if(userDataB instanceof Block){
            applyDamageToBlocks((Block) userDataB, impulse);
        }
    }

    private void applyDamageToPig(Pig pig, ContactImpulse impulse, Fixture objectFixture) {
        float totalImpulse = 0;
        for (float normalImpulse : impulse.getNormalImpulses()) {
            totalImpulse += normalImpulse;
        }
        float damage = totalImpulse * damageScale;
        pig.reduceHealth(damage);
//        System.out.println("Pig Health: "+pig.getHealth());
        if(pig.getHealth()<=0){
            objectsToDestroy.add(pig);

        }
    }

    private void applyDamageToBlocks(Block block, ContactImpulse impulse){
        float totalImpulse = 0;
        for (float normalImpulse : impulse.getNormalImpulses()) {
            totalImpulse += normalImpulse;
        }
        float damage = totalImpulse * damageScale;
        block.reduceHealth(damage);
//        System.out.println("Block Health: "+block.getHealth());
        if(block.getHealth()<=0){
            objectsToDestroy.add(block);
        }
    }


    public void update(){
        if(!level.getWorld().isLocked()) {
            for(GameObject gameObject : objectsToDestroy){
                gameObject.dispose();
                if (gameObject instanceof Pig){
                    level.getPigs().remove(gameObject);
                }
                if (gameObject instanceof Block){
                    level.getBlocks().remove(gameObject);
                }
            }
            objectsToDestroy.clear();
        }
    }
}
