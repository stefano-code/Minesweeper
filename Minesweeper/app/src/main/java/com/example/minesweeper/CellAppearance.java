package com.example.minesweeper;

import android.graphics.Bitmap;

    public class CellAppearance implements Cell{
        private final CellState cell;
        private final Bitmap image;

        public CellAppearance(CellState cell, Bitmap image) {
            this.image = image;
            this.cell = cell;
        }

        @Override
        public Cell.State getState() { return cell.getState(); }

        @Override
        public Bitmap getImage() { return image; }
    }

