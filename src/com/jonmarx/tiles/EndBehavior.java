/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import com.jonmarx.game.Main;
import static com.jonmarx.game.Main.pixelSize;
import com.jonmarx.game.TextBox;
import com.jonmarx.game.TextBoxManager;
import com.jonmarx.mobs.Entity;
import com.jonmarx.tiles.fonts.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author Jon
 */
public class EndBehavior extends CollisionBehavior {
    
    public EndBehavior() {
        super();
    }
    
    @Override
    public void collide(Entity e) {
        Tile[][] til = new Tile[4][16];
        til[0] = Font.convertToTiles(Main.font, "Level Complete,                 ");
        til[1] = Font.convertToTiles(Main.font, "Press Enter To Continue...      ");
        til[2] = Font.convertToTiles(Main.font, "                                ");
        til[3] = Font.convertToTiles(Main.font, "                                ");
        TextBox box = new TextBox(til) {
            @Override
            protected void onClose() {
                Main.loadLevel("/com/jonmarx/images/levels/Maze/Maze");
            }
        };
        TextBoxManager.openTextBox(box);
    }

    @Override
    public void render(int frame, Graphics g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
    }

    @Override
    public void update(int frame, int x, int y) {
                
    }
}
