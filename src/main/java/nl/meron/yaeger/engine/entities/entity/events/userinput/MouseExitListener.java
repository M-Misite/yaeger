package nl.meron.yaeger.engine.entities.entity.events.userinput;

import nl.meron.yaeger.engine.entities.entity.Entity;
import nl.meron.yaeger.engine.annotations.Initializer;
import nl.meron.yaeger.engine.entities.entity.NodeProvider;

/**
 * Being a {@link MouseExitListener} enables the {@link Entity} to be notified if the Mouse Cursor has
 * exited the area defined by the {@link javafx.geometry.BoundingBox} of an {@link Entity}.
 */
public interface MouseExitListener extends NodeProvider {

    /**
     * Called when the corresponding {@link javafx.scene.Node} receives a mouse exited event.
     */
    void onMouseExited();

    /**
     * Attach a mouseExitListener to this entity.
     */
    @Initializer
    default void attachMouseExitListener() {
        getGameNode().get().setOnMouseExited(mouseEvent -> onMouseExited());
    }
}
