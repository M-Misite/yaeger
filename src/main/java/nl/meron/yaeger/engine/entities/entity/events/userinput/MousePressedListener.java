package nl.meron.yaeger.engine.entities.entity.events.userinput;

import javafx.scene.input.MouseButton;
import nl.meron.yaeger.engine.entities.entity.Entity;
import nl.meron.yaeger.engine.annotations.Initializer;
import nl.meron.yaeger.engine.entities.entity.NodeProvider;

/**
 * Being a {@link MousePressedListener} enables the {@link Entity} to be notified if a {@link MouseButton} has been clicked
 * while the mouse pointer is on the {@link Entity}.
 */
public interface MousePressedListener extends NodeProvider {

    /**
     * Called when the corresponding {@link javafx.scene.Node} receives a mouse pressed event.
     *
     * @param button the {@link MouseButton} being pressed.
     */
    void onMousePressed(MouseButton button, Double xCoordinates, Double yCoordinates);

    /**
     * Attach a mousePressedListener to this entity.
     */
    @Initializer
    default void attachMousePressedListener() { ;
        getGameNode().get().setOnMousePressed(event -> onMousePressed(event.getButton(), event.getX(), event.getY()));
    }
}
