//package angry_bird.test;
//
//import angry_bird.gameObjects.SlingShot;
//import angry_bird.gameObjects.birds.Bird;
//import angry_bird.gameObjects.birds.Red;
//import angry_bird.gameObjects.birds.Yellow;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SlingShotTest {
//
//    private final World world;
//    private final ArrayList<Bird> birds;
//    private final SlingShot slingShot;
//
//    public SlingShotTest(){
//        this.world = new World(new Vector2(0, -9.8f), true);
//        this.birds = new ArrayList<>();
//        this.slingShot = new SlingShot(birds);
//    }
//
//    @Test
//    public void testLaunchBird() {
//        Red red = new Red(world,0,0,0.5f); birds.add(red);
//        Yellow yellow = new Yellow(world, 0,0, 0.5f); birds.add(yellow);
//        slingShot.loadNextBird();
//        Assertions.assertEquals(red,slingShot.getBird());
//        slingShot.loadNextBird();
//        Assertions.assertEquals(yellow, slingShot.getBird());
//    }
//}
