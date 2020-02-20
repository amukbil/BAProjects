package de.tu_clausthal.in.mec.bachelorproject.simulator.common;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import org.junit.jupiter.api.Test;

class EDirectionTest {

    @Test
    void nextposition() {
        DoubleMatrix1D l_position = new DenseDoubleMatrix1D(new double[]{10, 10});
        double l_speed = 3;
        System.out.println("initial position: " + l_position + ", and speed: " + l_speed);

        int i = 0;
        while (i < 5)
        {
            l_position = EDirection.RIGHTUP.nextposition(l_position, l_speed);
            System.out.println("P#" + i + ": " + l_position);
            i++;
        }
    }

    @Test
    void random() {
        System.out.println("New random direction: " + EDirection.random());
    }
}