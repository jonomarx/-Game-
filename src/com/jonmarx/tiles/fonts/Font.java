/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles.fonts;

import com.jonmarx.tiles.ImageTileBehavior;
import com.jonmarx.tiles.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jon
 */
public class Font {
    public static HashMap<Character, Tile> loadFont(String fileName) {
        try {
            BufferedImage image = ImageIO.read(Font.class.getResource(fileName + ".png"));
            BufferedImage[] characters = new BufferedImage[(image.getHeight() / 16) * (image.getWidth() / 16)];
            int k = 0;
            for(int i = 0; i < image.getHeight() / 16; i++) {
                for(int j = 0; j < image.getWidth() / 16; j++) {
                    characters[k] = image.getSubimage(j * 16, i * 16, 16, 16);
                    k++;
                }
            }
            return convertImagesToMap(characters, fileName);
        } catch (IOException ex) {
            Logger.getLogger(Font.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static HashMap<Character, Tile> convertImagesToMap(BufferedImage[] images, String filename) {
        Scanner s = new Scanner(Font.class.getResourceAsStream(filename + ".dat"));
        String chars = s.nextLine();
        HashMap<Character, Tile> output = new HashMap<>();
        int num = 0;
        for(char c : chars.toCharArray()) {
            output.put(c, new Tile(new ImageTileBehavior(images[num]), false));
            num++;
        }
        return output;
    }
    
    public static Tile[] convertToTiles(HashMap<Character, Tile> map, String string) {
        Tile[] output = new Tile[string.length()];
        int num = 0;
        for(char c : string.toCharArray()) {
            output[num] = map.get(c);
            num++;
        }
        return output;
    }
}
