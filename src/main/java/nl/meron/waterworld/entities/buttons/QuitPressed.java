package nl.meron.waterworld.entities.buttons;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.meron.waterworld.Waterworld;
import nl.meron.yaeger.engine.entities.entity.events.userinput.MouseEnterListener;
import nl.meron.yaeger.engine.entities.entity.events.userinput.MouseExitListener;
import nl.meron.yaeger.engine.entities.entity.events.userinput.MousePressedListener;
import nl.meron.yaeger.engine.entities.entity.Location;
import nl.meron.yaeger.engine.entities.entity.shape.text.TextEntity;

public class QuitPressed extends TextEntity implements MousePressedListener, MouseEnterListener, MouseExitListener {

    private static final Color TEXT_COLOR = Color.YELLOWGREEN;
    private static final Color TEXT_COLOR_HIGHLIGHT = Color.GREENYELLOW;

    public static final String EXIT_GAME = "Exit game";
    private Waterworld waterworld;

    public QuitPressed(Waterworld waterworld) {
        super(new Location(680, 400), EXIT_GAME);
        this.waterworld = waterworld;
        setFill(TEXT_COLOR);
        setFont(Font.font(Waterworld.FONT, FontWeight.BOLD, 30));
    }

    @Override
    public void onMousePressed(MouseButton button, Double xCoordinates, Double yCoordinates) {
        if (button.equals(MouseButton.PRIMARY)) {
            waterworld.quitGame();
        }
    }

    @Override
    public void onMouseEntered() {
        setFill(TEXT_COLOR_HIGHLIGHT);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited() {
        setFill(TEXT_COLOR);
        setCursor(Cursor.DEFAULT);
    }
}
