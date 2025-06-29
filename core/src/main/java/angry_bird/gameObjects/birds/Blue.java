package angry_bird.gameObjects.birds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Blue extends Bird{
    private boolean splitDone;

    private Blue blue1, blue2, blue3;

    public Blue(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        splitDone = false;
        this.setImage("game_objects/birds/blue.png");
    }

    @Override
//    public void ability() {
//        World world = this.getBody().getWorld();
//
//        float splitOffset = 0.5f;
//        float splitSpeed = 5.0f;
//
//        Vector2 position = body.getWorldCenter();
//        Vector2 velocity = body.getLinearVelocity();
//
//        blue1 = new Blue(world, position.x,position.y,this.radius/2);
//
//        blue1.getBody().setLinearVelocity(velocity.cpy().add(-splitSpeed, 0));
//
//        blue2 = new Blue(world, position.x,position.y-splitOffset,this.radius/2);
//        blue2.getBody().setLinearVelocity(velocity.cpy().add(-splitSpeed, 0));
//
//        blue3 = new Blue(world, position.x,position.y+splitOffset,this.radius/2);
//        blue3.getBody().setLinearVelocity(velocity.cpy().add(splitSpeed, 0));
//
//        this.dispose();
//
//        splitDone = true;
//    }
    public void ability() {
        World world = this.getBody().getWorld();

        float splitOffset = 0.5f;
        float baseSplitSpeed = 5.0f;
        float randomSpeedFactor = 0.3f;
        float randomOffsetFactor = 0.2f;

        Vector2 position = body.getWorldCenter();
        Vector2 velocity = body.getLinearVelocity();

        // Adding slight randomness to the velocities for a more natural feel
        float randomSplitSpeed1 = baseSplitSpeed + (float) (Math.random() * randomSpeedFactor - randomSpeedFactor / 2);
        float randomSplitSpeed2 = baseSplitSpeed + (float) (Math.random() * randomSpeedFactor - randomSpeedFactor / 2);
        float randomSplitSpeed3 = baseSplitSpeed + (float) (Math.random() * randomSpeedFactor - randomSpeedFactor / 2);

        blue1 = new Blue(world, position.x + (float) (Math.random() * randomOffsetFactor - randomOffsetFactor / 2), position.y, this.radius / 2);
        blue1.getBody().setLinearVelocity(velocity.cpy().add(-randomSplitSpeed1, (float) (Math.random() * 2 - 1)));
        blue1.getBody().setAngularVelocity((float) (Math.random() * 5 - 2.5)); // Adding spin

        blue2 = new Blue(world, position.x, position.y - splitOffset + (float) (Math.random() * randomOffsetFactor - randomOffsetFactor / 2), this.radius / 2);
        blue2.getBody().setLinearVelocity(velocity.cpy().add(-randomSplitSpeed2, (float) (Math.random() * 2 - 1)));
        blue2.getBody().setAngularVelocity((float) (Math.random() * 5 - 2.5));

        blue3 = new Blue(world, position.x, position.y + splitOffset + (float) (Math.random() * randomOffsetFactor - randomOffsetFactor / 2), this.radius / 2);
        blue3.getBody().setLinearVelocity(velocity.cpy().add(randomSplitSpeed3, (float) (Math.random() * 2 - 1)));
        blue3.getBody().setAngularVelocity((float) (Math.random() * 5 - 2.5));

        this.dispose();
        splitDone = true;
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        if (splitDone){
            blue1.render(spriteBatch);
            blue2.render(spriteBatch);
            blue3.render(spriteBatch);
        }
        else{
            super.render(spriteBatch);
        }
    }
}
