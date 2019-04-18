/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import com.jonmarx.game.Main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jon
 */
public class ImageTileBehavior extends TileBehavior {
    BufferedImage image;
    public ImageTileBehavior(BufferedImage b) {
        super();
        image = b;
    }

    @Override
    public void render(int frame, Graphics g, int x, int y) {
        g.drawImage(image, x * Main.pixelSize, y * Main.pixelSize, null);
    }

    @Override
    public void update(int frame, int x, int y) {
        
    }
}
