package nl.meron.yaeger.engine.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class YaegerApplicationExceptionTest {

    @Test
    void testMessageStaysAvailable() {
        // Arrange
        var message = "testmessage";

        // Act
        var sut = new YaegerEngineException(message);

        // Assert
        assertEquals(message, sut.getMessage());
    }

    @Test
    void testCausingExceptionStaysAvailable() {
        // Arrange
        var exception = Mockito.mock(Exception.class);

        // Act
        var sut = new YaegerEngineException(exception);

        // Assert
        assertEquals(exception, sut.getCause());
    }
}
