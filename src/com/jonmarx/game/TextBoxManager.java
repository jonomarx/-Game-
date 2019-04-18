/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.tiles.Tile;

/**
 *
 * @author Jon
 */
public class TextBoxManager {
    private static TextBox box = new TextBox(new Tile[0][0]);
    
    public static void openTextBox(Tile[][] tiles) {
        box = new TextBox(tiles);
        openTextBox();
    }
    public static void openTextBox(TextBox textbox) {
        box = textbox;
        openTextBox();
    }
    public static void openTextBox() {
        box.open();
    }
    
    public static void closeTextBox() {
        box.close();
    }
    
    public static boolean isOpen() {
        return box.isOpen();
    }
    
    public static Tile[][] getBox() {
        return box.getTiles();
    }
}
