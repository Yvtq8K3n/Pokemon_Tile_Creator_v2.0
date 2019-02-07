package creator.tile.pokemon.modelo;

import javafx.scene.image.PixelReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InvalidObjectException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IllegalFormatException;

public class Block {
    //8x8 image
    private BufferedImage img;
    private final ColorPattern pattern;


    public Block(BufferedImage img, ColorPattern pattern) {
       this.img = img;
       this.pattern = pattern;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public ColorPattern getPattern() {
        return pattern;
    }

    //Create an array whit the colors present in a Block
    public static Block createBlock(int invisible, BufferedImage img) throws InvalidObjectException {
        if (img.getWidth()> 8 || img.getHeight() > 8) throw new InvalidObjectException("Block is bigger then 8x8");
        ArrayList<Integer> colors = new ArrayList<>();

        for(int i = 0; i<img.getHeight(); i++){
            for(int j = 0; j<img.getHeight(); j++){
                int hexColor = img.getRGB(j, i);

                if (!colors.contains(hexColor) && hexColor != invisible) colors.add(hexColor);
            }
        }

        return new Block(img, new ColorPattern(colors));
    }


    //May have to change
    @Override
    public boolean equals(Object obj) {
        return this.pattern.equals(((Block)obj).pattern);
    }


}
