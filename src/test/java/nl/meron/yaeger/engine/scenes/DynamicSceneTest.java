package nl.meron.yaeger.engine.scenes;

import com.google.inject.Injector;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import nl.meron.yaeger.engine.Timer;
import nl.meron.yaeger.engine.debug.Debugger;
import nl.meron.yaeger.engine.entities.EntityCollection;
import nl.meron.yaeger.engine.entities.EntitySupplier;
import nl.meron.yaeger.engine.Updatable;
import nl.meron.yaeger.engine.Updater;
import nl.meron.yaeger.engine.scenes.delegates.BackgroundDelegate;
import nl.meron.yaeger.engine.scenes.delegates.KeyListenerDelegate;
import nl.meron.yaeger.javafx.animationtimer.AnimationTimerFactory;
import nl.meron.yaeger.guice.factories.EntityCollectionFactory;
import nl.meron.yaeger.guice.factories.SceneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DynamicSceneTest {

    private DynamicSceneImpl sut;
    private SceneFactory sceneFactory;
    private Debugger debugger;
    private EntityCollectionFactory entityCollectionFactory;
    private AnimationTimer animationTimer;
    private AnimationTimerFactory animationTimerFactory;
    private Injector injector;

    private KeyListenerDelegate keyListenerDelegate;
    private BackgroundDelegate backgroundDelegate;

    private EntityCollection entityCollection;
    private EntitySupplier entitySupplier;
    private Group root;
    private Scene scene;
    private Updater updater;

    @BeforeEach
    void setup() {
        sut = new DynamicSceneImpl();

        root = mock(Group.class);
        backgroundDelegate = mock(BackgroundDelegate.class);
        keyListenerDelegate = mock(KeyListenerDelegate.class);
        entitySupplier = mock(EntitySupplier.class);
        sceneFactory = mock(SceneFactory.class);
        debugger = mock(Debugger.class);
        animationTimer = mock(AnimationTimer.class);
        entityCollectionFactory = mock(EntityCollectionFactory.class);
        animationTimerFactory = mock(AnimationTimerFactory.class);
        injector = mock(Injector.class);
        updater = mock(Updater.class);

        sut.setDebugger(debugger);
        sut.setSceneFactory(sceneFactory);
        sut.setEntityCollectionFactory(entityCollectionFactory);
        sut.setRoot(root);
        sut.setBackgroundDelegate(backgroundDelegate);
        sut.setKeyListenerDelegate(keyListenerDelegate);
        sut.setEntitySupplier(entitySupplier);
        sut.setAnimationTimerFactory(animationTimerFactory);
        sut.setUpdater(updater);

        scene = mock(Scene.class);
        entityCollection = mock(EntityCollection.class);

        when(sceneFactory.create(root)).thenReturn(scene);
        when(entityCollectionFactory.create(root)).thenReturn(entityCollection);
        when(animationTimerFactory.create(any())).thenReturn(animationTimer);

        sut.init(injector);
    }

    @Test
    void getTimersReturnsAnEmptyCollection() {
        // Arrange

        // Act
        List<Timer> timers = sut.getTimers();

        // Verify
        assertNotNull(timers);
        assertTrue(timers.isEmpty());
    }

    @Test
    void destroyClearsEntityCollection() {
        // Arrange
        var children = mock(ObservableList.class);
        when(root.getChildren()).thenReturn(children);
        sut.activate();

        // Act
        sut.destroy();

        // Verify
        verify(entityCollection).clear();
    }

    @Test
    void destroyClearsUpdaters() {
        // Arrange
        var children = mock(ObservableList.class);
        when(root.getChildren()).thenReturn(children);

        sut.activate();

        // Act
        sut.destroy();

        // Verify
        verify(updater).clear();
    }

    @Test
    void onInputChangeNotifiesEntityCollection() {
        // Arrange
        var input = new HashSet<KeyCode>();
        input.add(KeyCode.F1);
        sut.activate();

        // Act
        sut.onInputChanged(input);

        // Verify
        verify(entityCollection).notifyGameObjectsOfPressedKeys(input);
    }

    @Test
    void setEntityCollectionUpdatableReturnsUpdatable() {
        // Arrange
        sut.activate();

        // Act
        var updatable = sut.entityCollectionUpdatable();

        // Verify
        assertTrue(updatable instanceof Updatable);
    }

    @Test
    void updatingEntityCollectionUpdatesEntityCollection() {
        // Arrange
        sut.activate();
        var updatable = sut.entityCollectionUpdatable();

        // Act
        updatable.update(0l);

        // Verify
        verify(entityCollection).update(0l);
    }

    @Test
    void setUpdaterIsUsed() {
        // Arrange

        // Act
        var u = sut.getUpdater();

        // Verify
        assertEquals(updater, u);
    }

    private class DynamicSceneImpl extends DynamicScene {

        @Override
        public void setupScene() {
            // Not required here
        }

        @Override
        public void setupEntities() {
            // Not required here
        }
    }
}
