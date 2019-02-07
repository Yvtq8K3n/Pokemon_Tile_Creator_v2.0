package creator.tile.pokemon.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class ColorPattern {

    private final ArrayList<Integer> colors;

    public ColorPattern(ArrayList<Integer> colors) {
        Collections.sort(colors);
        this.colors = colors;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }
}
