package nl.meron.yaeger.engine;

import nl.meron.yaeger.engine.exceptions.YaegerEngineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WithTimersTest {

    public static final long TIMESTAMP = 0l;
    private WithTimersImpl sut;
    private List<Timer> timers;

    @BeforeEach
    void setup() {
        sut = new WithTimersImpl();
        timers = Mockito.mock(ArrayList.class);
        sut.setTimers(timers);
    }

    @Test
    void initFirstClearsListOfTimers() {
        // Arrange

        // Act
        sut.initTimers();

        // Assert
        verify(timers).clear();
    }

    @Test
    void initCallsSetupTimers() {
        // Arrange

        // Act
        sut.initTimers();

        // Assert
        assertTrue(sut.isSetupTimersCalled());
    }

    @Test
    void addTimerAddTheTimerToTheTimers() {
        // Arrange
        sut.setTimers(new ArrayList<>());
        var timer = mock(Timer.class);

        // Act
        sut.addTimer(timer);

        // Assert
        assertEquals(timer, sut.getTimers().get(0));
    }

    @Test
    void callTimersDoesNotBreakIfTimersIsNull() {
        // Arrange

        // Act
        var updatable = sut.callTimers();

        // Assert
        assertTrue(updatable instanceof Updatable);
    }

    @Test
    void addTimerThrowsExceptionIfGetTimersReturnNull() {
        // Arrange
        var timer1 = mock(Timer.class);
        sut.setTimers(null);

        // Act

        // Assert
        assertThrows(YaegerEngineException.class, () -> sut.addTimer(timer1));
    }

    @Test
    void invokingTheUpdatableCallsHandleOnEachTimer() {
        // Arrange
        sut.setTimers(new ArrayList<>());
        var timer1 = mock(Timer.class);
        var timer2 = mock(Timer.class);
        sut.addTimer(timer1);
        sut.addTimer(timer2);
        var updatable = sut.callTimers();

        // Act
        updatable.update(TIMESTAMP);

        // Assert
        verify(timer1).handle(TIMESTAMP);
        verify(timer2).handle(TIMESTAMP);
    }

    private class WithTimersImpl implements WithTimers {

        private List<Timer> timers;
        private boolean setupTimersCalled = false;

        @Override
        public void setupTimers() {
            setupTimersCalled = true;
        }

        @Override
        public List<Timer> getTimers() {
            return timers;
        }

        public boolean isSetupTimersCalled() {
            return setupTimersCalled;
        }

        public void setTimers(List<Timer> timers) {
            this.timers = timers;
        }
    }
}
