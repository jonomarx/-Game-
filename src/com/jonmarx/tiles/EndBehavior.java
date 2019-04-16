/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import com.jonmarx.game.Main;
import static com.jonmarx.game.Main.pixelSize;
import com.jonmarx.mobs.Entity;
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
        JOptionPane.showMessageDialog(null, "Congrats, you won!");
        Main.loadLevel("/com/jonmarx/images/levels/Maze/Maze");
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
