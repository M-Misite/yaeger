package nl.meron.showcase.scenes.shapeentities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.meron.showcase.YaegerShowCase;
import nl.meron.showcase.buttons.Back;
import nl.meron.showcase.scenes.ShowCaseScene;
import nl.meron.showcase.scenes.shapeentities.entities.DynamicRectangle;
import nl.meron.showcase.scenes.shapeentities.entities.StaticCircle;
import nl.meron.showcase.scenes.shapeentities.entities.StaticRectangle;
import nl.meron.showcase.scenes.shapeentities.entities.TimedDynamicRectangle;
import nl.meron.yaeger.engine.entities.EntityCollection;
import nl.meron.yaeger.engine.entities.EntitySupplier;
import nl.meron.yaeger.engine.entities.entity.Location;
import nl.meron.yaeger.engine.entities.entity.YaegerEntity;
import nl.meron.yaeger.engine.entities.entity.shape.text.TextEntity;

import java.util.HashMap;

public class ShapeEntitiesScene extends ShowCaseScene {

    private YaegerShowCase showCase;

    public ShapeEntitiesScene(YaegerShowCase showCase) {
        this.showCase = showCase;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("showcase/backgrounds/milky-way.jpg");
    }

    @Override
    public void setupEntities() {
        var backButton = new Back(showCase, new Location(40, getHeight() - 80));
        addEntity(backButton);

        var rect = new StaticRectangle(new Location(40, 60));
        addEntity(rect);

        var dynamicRectangle = new DynamicRectangle(new Location(40, 160));
        addEntity(dynamicRectangle);

        var timedDynamicRectangle = new TimedDynamicRectangle(new Location(40, 260));
        addEntity(timedDynamicRectangle);

        var circle = new StaticCircle(new Location(150, 560));
        addEntity(circle);
        EntitySupplier entitySupplier = this.getEntitySupplier();

        HashMap<YaegerEntity, Double> circleDistances = circle.getDistanceToEntities(entitySupplier);

        var distanceText = new TextEntity(new Location(300, 60), "Circle Distance to Rectangle:  " + circleDistances.get(rect));
        distanceText.setFont(new Font("Serif", 18));
        distanceText.setFill(Color.SNOW);
        addEntity(distanceText);
    }

    @Override
    public YaegerShowCase getShowCase() {
        return showCase;
    }
}
