package com.example.minesweeper;

import android.graphics.Bitmap;

    public interface Cell {
        enum Value {  EMPTY(0), ONE(1), TWO(2),
            THREE(3), FOUR(4), FIVE(5), SIX(6),
            SEVEN(7), EIGHT(8), MINE(9);
            private final int val;
            Value(int value) {
                this.val = value;
            }
            public int getValue() {
                return val;
            }
            public static Value  getEnum(int value){
                for (Value e:Value.values()) {
                    if(e.getValue() == value)
                        return e;
                }
                return Value.EMPTY;
            }
        }

        enum State { UNTOUCHED, TOUCHED, FLAG, REVEAL }

        Cell.State getState();
        Bitmap getImage();
    }

