package angry_bird.gameObjects.birds;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Red extends Bird{
    public Red(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        setImage("game_objects/birds/red.png");
    }

    public void ability() {

    }

}
