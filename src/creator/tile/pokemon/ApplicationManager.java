package creator.tile.pokemon;

import creator.tile.pokemon.modelo.Block;
import creator.tile.pokemon.modelo.Image;
import org.zzl.minegaming.GBAUtils.DataStore;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public enum ApplicationManager {
    INSTANCE;

    private BufferedImage bufferedImage;
    private Image image;

    public DataStore dataStore;

    ApplicationManager(){

    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
