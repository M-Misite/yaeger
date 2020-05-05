package nl.meron.yaeger.engine.entities.entity;

import javafx.scene.Node;

import java.util.Optional;

/**
 * Being a {@link NodeProvider} guarantees that a {@link Node}, if set, is accessible.
 */
public interface NodeProvider {

    /**
     * Return an {@link Optional} of the {@link Node} that is related to this {@link Entity}.
     *
     * @return an {@link Optional} of the {@link Node} that is related to this {@link Entity}
     */
    Optional<Node> getGameNode();

    /**
     * Bring the {@link Node} to the front of the stack of nodes.
     */
    default void toFront() {
        getGameNode().ifPresent(Node::toFront);
    }

    /**
     * Bring the {@link Node} to the back of the stack of nodes.
     */
    default void toBack() {
        getGameNode().ifPresent(Node::toBack);
    }
}
