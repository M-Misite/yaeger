package nl.meron.yaeger.engine.exceptions;

import nl.meron.yaeger.engine.scenes.SceneType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class YaegerSceneNotAvailableExceptionTest {

    @Test
    void correctMessageIsContructed() {
        // Setup

        // Test
        var sut = new YaegerSceneNotAvailableException(SceneType.INTRO);

        // Verify
        var message = sut.getMessage();

        Assertions.assertEquals("Scene INTRO is not available. Ensure the scene is added to the game.", message);
    }
}
