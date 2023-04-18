package com.example.minesweeper;

import android.graphics.Bitmap;

    public class CellState implements Cell{
        private Cell.Value value;
        private Cell.State state;

        @Override
        public Cell.State getState() {
                return state;
            }

        @Override
        public Bitmap getImage() { return null; }

        public CellState() {
            clean();
        }

        public void clean() {
            value = Cell.Value.EMPTY;
            state = Cell.State.UNTOUCHED;
        }

        public void setValue(Cell.Value value) {
            this.value = value;
        }
        public Cell.Value getValue() {
            return value;
        }
        public void setState(Cell.State state) {
            this.state = state;
        }
    }

