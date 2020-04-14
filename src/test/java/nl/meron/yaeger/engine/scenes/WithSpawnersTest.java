package nl.meron.yaeger.engine.scenes;

import com.google.inject.Injector;
import nl.meron.yaeger.engine.Timer;
import nl.meron.yaeger.engine.Updatable;
import nl.meron.yaeger.engine.entities.EntityCollection;
import nl.meron.yaeger.engine.entities.EntitySpawner;
import nl.meron.yaeger.engine.exceptions.YaegerEngineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class WithSpawnersTest {

    public static final long TIMESTAMP = 0l;
    private WithSpawnersImpl sut;
    private List<EntitySpawner> spawners;
    private Injector injector;

    @BeforeEach
    void setup() {
        sut = new WithSpawnersImpl();
        spawners = mock(ArrayList.class);
        injector = mock(Injector.class);
        sut.setSpawners(spawners);
        sut.setInjector(injector);
    }

    @Test
    void initFirstClearsListOfSpawners() {
        // Arrange

        // Act
        sut.initSpawners();

        // Assert
        verify(spawners).clear();
    }

    @Test
    void initCallsSetupSpawners() {
        // Arrange

        // Act
        sut.initSpawners();

        // Assert
        assertTrue(sut.isSetupSpawnersCalled());
    }

    @Test
    void addSpawnerAddTheSpawnerToTheSpawners() {
        // Arrange
        sut.setSpawners(new ArrayList<>());
        var spawner = mock(EntitySpawner.class);

        // Act
        sut.addEntitySpawner(spawner);

        // Assert
        assertEquals(spawner, sut.getSpawners().get(0));
    }

    @Test
    void callSpawnersDoesNotBreakIfSpawnersIsNull() {
        // Arrange

        // Act
        var updatable = sut.callEntitySpawners();

        // Assert
        assertTrue(updatable instanceof Updatable);
    }

    @Test
    void addSpawnerThrowsExceptionIfGetSpawnersReturnNull() {
        // Arrange
        var spawner1 = mock(EntitySpawner.class);
        sut.setSpawners(null);

        // Act

        // Assert
        assertThrows(YaegerEngineException.class, () -> sut.addEntitySpawner(spawner1));
    }

    @Test
    void invokingTheUpdatableCallsHandleOnEachSpawner() {
        // Arrange
        sut.setSpawners(new ArrayList<>());
        var spawner1 = mock(EntitySpawner.class);
        var spawner2 = mock(EntitySpawner.class);
        sut.addEntitySpawner(spawner1);
        sut.addEntitySpawner(spawner2);
        var updatable = sut.callEntitySpawners();

        // Act
        updatable.update(TIMESTAMP);

        // Assert
        verify(spawner1).handle(TIMESTAMP);
        verify(spawner2).handle(TIMESTAMP);
    }

    private class WithSpawnersImpl implements WithSpawners {

        private List<EntitySpawner> spawners;
        private boolean setupSpawnersCalled = false;
        private Injector injector;


        @Override
        public void setupEntitySpawners() {
            setupSpawnersCalled = true;
        }

        @Override
        public Injector getInjector() {
            return injector;
        }

        @Override
        public EntityCollection getEntityCollection() {
            return null;
        }

        @Override
        public List<EntitySpawner> getSpawners() {
            return spawners;
        }

        public void setSpawners(List<EntitySpawner> spawners) {
            this.spawners = spawners;
        }

        public boolean isSetupSpawnersCalled() {
            return setupSpawnersCalled;
        }

        public void setInjector(Injector injector) {
            this.injector = injector;
        }
    }
}
