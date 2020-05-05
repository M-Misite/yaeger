package nl.meron.yaeger.engine.entities.entity.shape.circle;

import com.google.inject.Injector;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import nl.meron.yaeger.engine.entities.entity.Location;
import nl.meron.yaeger.engine.Updater;
import nl.meron.yaeger.engine.entities.entity.motion.DefaultMotionApplier;
import nl.meron.yaeger.engine.entities.entity.motion.EntityMotionInitBuffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DynamicCircleEntityTest {

    private final static int X_POSITION = 37;
    private final static int Y_POSITION = 37;
    private final static Location DEFAULT_LOCATION = new Location(X_POSITION, Y_POSITION);
    public static final int ROTATION_SPEED = 37;
    public static final double SPEED = 37d;
    public static final double DIRECTION = 42d;

    private DynamicCircleEntityImpl sut;
    private Injector injector;
    private Circle circle;

    @BeforeEach
    void setup() {
        sut = new DynamicCircleEntityImpl(DEFAULT_LOCATION);
        circle = mock(Circle.class);
        sut.setCircle(circle);
        injector = mock(Injector.class);
    }

    @Test
    void bufferIsSetInConstructor() {
        // Arrange

        // Act
        Optional<EntityMotionInitBuffer> buffer = sut.getBuffer();

        // Assert
        Assertions.assertTrue(buffer.isPresent());
    }

    @Test
    void bufferIsEmptiedAfterInitIsCalled() {
        // Arrange
        var motionApplier = mock(DefaultMotionApplier.class);
        sut.setMotionApplier(motionApplier);

        // Act
        sut.init(injector);

        // Assert
        Assertions.assertFalse(sut.getBuffer().isPresent());
    }

    @Test
    void bufferTransfersMotionOnInit(){
        // Arrange
        var motionApplier = mock(DefaultMotionApplier.class);
        sut.setMotionApplier(motionApplier);
        sut.setMotionTo(SPEED, DIRECTION);

        // Act
        sut.init(injector);

        // Assert
        verify(motionApplier).setMotionTo(SPEED, DIRECTION);
    }

    @Test
    void initSetsMotionToDesiredSpeed() {
        // Arrange
        sut.setSpeedTo(SPEED);
        var motionApplier = mock(DefaultMotionApplier.class);
        sut.setMotionApplier(motionApplier);

        // Act
        sut.init(injector);

        // Assert
        verify(motionApplier).setMotionTo(SPEED, 0d);
    }

    @Test
    void setMotionApplierIsUsed() {
        // Arrange
        var motionApplier = mock(DefaultMotionApplier.class);
        sut.setMotionApplier(motionApplier);

        // Act
        var mA = sut.getMotionApplier();

        // Assert
        Assertions.assertEquals(motionApplier, mA);
    }

    @Test
    void setUpdaterIsUsed() {
        // Arrange
        var updater = mock(Updater.class);
        sut.setUpdater(updater);

        // Act
        var u = sut.getUpdater();

        // Assert
        Assertions.assertEquals(updater, u);
    }

    @Test
    void setRotationSpeedIsUsed() {
        // Arrange
        sut.setRotationSpeed(ROTATION_SPEED);

        // Act
        var rS = sut.getRotationSpeed();

        // Assert
        Assertions.assertEquals(ROTATION_SPEED, rS);
    }


    private class DynamicCircleEntityImpl extends DynamicCircleEntity {

        /**
         * Create a new {@link DynamicCircleEntity} on the given {@code initialPosition}.
         *
         * @param initialPosition The initial position at which this {@link DynamicCircleEntity} should be placed
         */
        public DynamicCircleEntityImpl(Location initialPosition) {
            super(initialPosition);
        }
    }
}
