package com.IA.TP2;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tsandler
 */
public enum JourneyStatus {

    ONE_LION(1) {
        @Override
        public void evaluate(boolean isEven, IAFitnessFunction function) {
            if (isEven)
                function.moveLionsFromRight(1);
            else
                function.moveLionsFromLeft(1);
        }
    },
    ONE_BUFFALO(2) {
        @Override
        public void evaluate(boolean isEven, IAFitnessFunction function) {
            if (isEven)
                function.moveBuffalosFromRight(1);
            else
                function.moveBuffalosFromLeft(1);
        }
    },
    TWO_LIONS(3) {
        @Override
        public void evaluate(boolean isEven, IAFitnessFunction function) {
            if (isEven)
                function.moveLionsFromRight(2);
            else
                function.moveLionsFromLeft(2);
        }
    },
    TWO_BUFFALOS(4) {
        @Override
        public void evaluate(boolean isEven, IAFitnessFunction function) {
            if (isEven)
                function.moveBuffalosFromRight(2);
            else
                function.moveBuffalosFromLeft(2);
        }
    },
    ONE_LION_ONE_BUFFALO(5) {
        @Override
        public void evaluate(boolean isEven, IAFitnessFunction function) {
            if (isEven) {
                function.moveLionsFromRight(1);
                function.moveBuffalosFromRight(1);
            }else {
                function.moveLionsFromLeft(1);
                function.moveBuffalosFromLeft(1);
            }
        }
    };

    public abstract void evaluate(boolean isEven, IAFitnessFunction function);

    private int value;

    private static final Map<Integer, JourneyStatus> lookup = new HashMap<>();

    static {
        for (JourneyStatus type : EnumSet.allOf(JourneyStatus.class))
            lookup.put(type.value, type);
    }

    public static JourneyStatus get(Integer name) {
        return lookup.get(name);
    }

    JourneyStatus(int value){
        this.value = value;
    }
}
