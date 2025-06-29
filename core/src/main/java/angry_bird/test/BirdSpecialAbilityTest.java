//package angry_bird.test;
//
//import angry_bird.gameObjects.birds.Black;
//import angry_bird.gameObjects.birds.Blue;
//import angry_bird.gameObjects.birds.Yellow;
//import angry_bird.gameObjects.blocks.Block;
//import angry_bird.gameObjects.blocks.Stone;
//import angry_bird.gameObjects.pigs.MinionPig;
//import angry_bird.gameObjects.pigs.Pig;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//
//public class BirdSpecialAbilityTest {
//    @Test
//    public void testYellowBird(){
//        World world = new World(new Vector2(0, -9.8f), true);
//        Yellow yellow = new Yellow(world, 0,0,0.5f);
//        float initialSpeed = yellow.getBody().getLinearVelocity().len();
//        yellow.ability();
//        float finalSpeed = yellow.getBody().getLinearVelocity().len();
//        Assertions.assertTrue(finalSpeed>initialSpeed);
//    }
//
//    @Test
//    public void testBlackBird(){
//        World world = new World(new Vector2(0,-9.8f),true);
//        Black black = new Black(world,0,0,0.5f);
//
//        MinionPig pig = new MinionPig(world,0,5,0.5f);
//        float initialPigHealth = pig.getHealth();
//        Stone block = new Stone(world,5,0,2,4);
//        float initialBlockHealth = block.getHealth();
//
//        black.ability();
//
//        float finalPigHealth = pig.getHealth();
//        float finalBlockHealth = block.getHealth();
//
//        Assertions.assertTrue((finalPigHealth<initialPigHealth)&&(finalBlockHealth<initialBlockHealth));
//    }
//
//    @Test
//    public void testBlueBird(){
//        World world = new World(new Vector2(0,-9.8f),true);
//        Blue blue = new Blue(world,0,0,0.5f);
//        int initialBodyCount = world.getBodyCount();
//        blue.ability();
//        int finalBodyCount = world.getBodyCount();
//        Assertions.assertEquals(3,finalBodyCount);
//    }
//}
