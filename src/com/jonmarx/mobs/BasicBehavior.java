/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.mobs;

import com.jonmarx.game.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jon
 */
public class BasicBehavior extends EntityBehavior {

    @Override
    public void render(Graphics g, String fileName, int x, int y) {
        BufferedImage b = null;
        try {
            b = ImageIO.read(getClass().getResource("/com/jonmarx/images/entities/Image.png"));
        } catch (IOException ex) {
            Logger.getLogger(BasicBehavior.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(b, x * Main.pixelSize, y * Main.pixelSize, Main.pixelSize, Main.pixelSize, null);
    }

    @Override
    public void update(int frame) {
        
    }
    
}
