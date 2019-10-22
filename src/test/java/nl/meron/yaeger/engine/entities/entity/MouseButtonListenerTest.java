package nl.meron.yaeger.engine.entities.entity;

import com.google.inject.Injector;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import nl.meron.yaeger.engine.userinput.MouseButtonListener;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MouseButtonListenerTest {

    @Test
    void attachMousePressedListenerAttachesMouseListener() {
        // Setup
        var node = mock(Node.class);
        var mouseListeningEntity = new MouseListeningEntity();
        mouseListeningEntity.setNode(node);

        // Test
        mouseListeningEntity.attachMousePressedListener();

        // Verify
        verify(node).setOnMousePressed(any());
    }

    private class MouseListeningEntity implements MouseButtonListener, Entity {

        private Node node;

        @Override
        public Position getPosition() {
            return null;
        }

        @Override
        public double getRightSideXCoordinate() {
            return 0;
        }

        @Override
        public double getLeftSideXCoordinate() {
            return 0;
        }

        @Override
        public double getBottomYCoordinate() {
            return 0;
        }

        @Override
        public double getTopYCoordinate() {
            return 0;
        }

        @Override
        public void init(Injector injector) {

        }

        @Override
        public void onMousePressed(MouseButton button) {

        }

        @Override
        public void remove() {

        }

        @Override
        public Node getGameNode() {
            return node;
        }

        void setNode(Node node) {
            this.node = node;
        }
    }
}
