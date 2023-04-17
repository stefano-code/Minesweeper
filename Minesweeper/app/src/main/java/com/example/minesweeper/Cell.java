package com.example.minesweeper;

    public class Cell {
        enum Value {
            EMPTY(0),
            ONE(1),
            TWO(2),
            THREE(3),
            FOUR(4),
            FIVE(5),
            SIX(6),
            SEVEN(7),
            EIGHT(8),
            MINE(9);
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

        enum State {
            UNTOUCHED,
            TOUCHED,
            FLAG,
            REVEAL
        }

        private Value value;
        private State state;

        public Cell() {
            clean();
        }

        public void clean() {
            value = Value.EMPTY;
            state = State.UNTOUCHED;
        }

        public void setValue(Value value) {
            this.value = value;
        }
        public Value getValue() {
            return value;
        }

        public void setState(State state) {
            this.state = state;
        }
        public State getState() {
            return state;
        }

        public boolean IsUnTouched() {
            return state == State.UNTOUCHED;
        }

        public boolean IsTouched() {
            return state == State.TOUCHED;
        }

        public boolean IsFlag() {
            return state == State.FLAG;
        }
    }
