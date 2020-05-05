package nl.meron.waterworld.entities.game;

import nl.meron.waterworld.scenes.levels.Level;
import nl.meron.yaeger.engine.entities.entity.collisions.Collider;
import nl.meron.yaeger.engine.entities.entity.Location;
import nl.meron.yaeger.engine.entities.entity.motion.Direction;
import nl.meron.yaeger.engine.Size;
import nl.meron.yaeger.engine.entities.entity.SceneBorderCrossingWatcher;
import nl.meron.yaeger.engine.media.audio.SoundClip;
import nl.meron.yaeger.engine.entities.entity.collisions.AABBCollided;
import nl.meron.yaeger.engine.entities.entity.sprite.DynamicSpriteEntity;
import nl.meron.yaeger.engine.scenes.SceneBorder;

public abstract class Bubble extends DynamicSpriteEntity implements AABBCollided, SceneBorderCrossingWatcher {

    private static final String AUDIO_POP_MP3 = "waterworld/audio/pop.mp3";
    private final Level level;

    Bubble(final Location location, final String resource, final double speed, final Level game) {
        super(resource, location, new Size(20, 20), 0);
        this.level = game;
        setMotionTo(speed, Direction.UP.getValue());
        setRotationSpeed(Math.random() * 5);
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if (collidingObject instanceof AnimatedShark) {
            handleSharkCollision();
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border.equals(SceneBorder.TOP)) {
            remove();
        }
    }

    void handleSharkCollision() {
        removeBubble();
    }

    void handlePlayerCollision() {
        level.increaseBubblesPopped();

        removeBubble();
    }

    private void removeBubble() {
        SoundClip popSound = new SoundClip(AUDIO_POP_MP3);
        popSound.play();
        remove();
    }

    protected Level getLevel() {
        return level;
    }
}
