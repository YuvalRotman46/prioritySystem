package bots;

import elf_kingdom.Game;


public class PortalManager {

    public enum side{
        LEFT, RIGHT, UP, DOWN
    }

    private boolean isImportant;
    private side enemyCastlePlacementX;
    private side enemyCastlePlacementY;
    private side myCastlePlacementX;
    private side myCastlePlacementY;

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }

    public side getEnemyCastlePlacementX() {
        return enemyCastlePlacementX;
    }

    public void setEnemyCastlePlacementX(side enemyCastlePlacementX) {
        this.enemyCastlePlacementX = enemyCastlePlacementX;
    }

    public side getEnemyCastlePlacementY() {
        return enemyCastlePlacementY;
    }

    public void setEnemyCastlePlacementY(side enemyCastlePlacementY) {
        this.enemyCastlePlacementY = enemyCastlePlacementY;
    }

    public side getMyCastlePlacementX() {
        return myCastlePlacementX;
    }

    public void setMyCastlePlacementX(side myCastlePlacementX) {
        this.myCastlePlacementX = myCastlePlacementX;
    }

    public side getMyCastlePlacementY() {
        return myCastlePlacementY;
    }

    public void setMyCastlePlacementY(side myCastlePlacementY) {
        this.myCastlePlacementY = myCastlePlacementY;
    }


    public PortalManager(Game game, boolean isImportant) {
        this.isImportant = isImportant;
        int enemyX = game.getEnemyCastle().location.col;
        int enemyY = game.getEnemyCastle().location.row;
        int myX = game.getEnemyCastle().location.col;
        int myY = game.getMyCastle().location.row;

        if(enemyX >= game.cols/2) {
            this.enemyCastlePlacementX = side.RIGHT;
        }
else {
            this.enemyCastlePlacementX = side.LEFT;
        }

        if(enemyY >= game.rows/2) {
            this.enemyCastlePlacementY = side.UP;
        }
        else {
            this.enemyCastlePlacementY = side.DOWN;
        }

        if(myX >= game.cols/2) {
            this.myCastlePlacementX = side.RIGHT;
        }
        else {
            this.myCastlePlacementX = side.LEFT;
        }

        if(myY >= game.rows/2) {
            this.myCastlePlacementY = side.UP;
        }
        else {
            this.myCastlePlacementY = side.DOWN;
        }
    }
}