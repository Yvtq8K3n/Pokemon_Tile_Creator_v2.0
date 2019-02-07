package creator.tile.pokemon.modelo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Image {

    int invisibleColor;
    private ArrayList<Block> blocks;
    private Integer maxLevel;
    private HashMap<Integer, List<ColorPattern>> levels;

    //Recieves BufferedImage
    public Image() {

    }

    public Image(int invisible, ArrayList<Block> blocks, Integer maxLevel, HashMap<Integer, List<ColorPattern>> levels){
        this.invisibleColor = invisible;
        this.blocks = blocks;
        this.maxLevel = maxLevel;
        this.levels = levels;
    }

    public int getInvisibleColor() {
        return invisibleColor;
    }

    public void setInvisibleColor(int invisibleColor) {
        this.invisibleColor = invisibleColor;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }


    //Creates an Image whit a normal list of Blocks and a Hashmap whit the least amount of colors
    public static Image convertBufferedImageIntoImage(int invisible, BufferedImage img){
        ArrayList<Block> blocks = new ArrayList<>();
        HashMap<Integer, List<ColorPattern>> levels = new HashMap<>();

        int maxLevel = -1;
        for(int i = 0; i<img.getHeight(); i+=8){
            for(int j = 0; j<img.getHeight(); j+=8){
                try{
                    Block newBlock = Block.createBlock(invisible ,img.getSubimage(j,i,16,16));

                    int level = newBlock.getPattern().getColors().size();
                    maxLevel = maxLevel<level ? level : maxLevel;

                    if (!levels.containsKey(level)) levels.put(level, new ArrayList<>());
                    List<ColorPattern> blocksOnLevel  = levels.get(level);

                    blocks.add(newBlock);
                    if(!blocksOnLevel.contains(newBlock.getPattern())) blocksOnLevel.add(newBlock.getPattern());
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        return new Image(invisible, blocks, maxLevel, levels);
    }
}

