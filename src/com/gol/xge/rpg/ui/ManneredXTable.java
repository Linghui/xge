package com.gol.xge.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.tablelayout.Cell;

public class ManneredXTable extends XTable {

    private String TAG = "ManneredXTable";
    
    private int row = 0;
    private int col = 0;
    
    private int rowNumber = 0;
    private int colNumber = 0;

    
    public ManneredXTable(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public Cell add(Actor actor){
        Cell cell = super.add(actor);
        
        colNumber++;
        if( colNumber >= col){
            colNumber = 0;
            this.row();
            rowNumber++;
            if( rowNumber >= row ){
                Gdx.app.log(TAG, "No more!!!");
            }
        }
        
        return cell;
    }

    public Actor getActor(int rowIndex, int colIndex){
        
        int index = rowIndex*col + colIndex;

        Array<Actor> children = this.getChildren();
        if( index < children.size ){
            return children.get(index);
        }
        return null;
    }
}
