package nl.meron.yaeger.engine.entities.entity;

public interface Anchorable {
    /**
     * Set the {@link AnchorPoint} of this {@link Placeable}. The {@link AnchorPoint} will be used
     * to set the given x, y-coordinate. By default a {@link Placeable} will use the top-left as
     * its anchor-point.
     *
     * @param anchorPoint The {@link AnchorPoint} of this {@link Placeable}.
     */
    void setAnchorPoint(AnchorPoint anchorPoint);

    /**
     * Return the {@link AnchorPoint} of this {@link Placeable}.
     *
     * @return The {@link AnchorPoint} of this {@link Placeable}.
     */
    AnchorPoint getAnchorPoint();
}
