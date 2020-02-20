package de.tu_clausthal.in.mec.bachelorproject.simulator.common;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.jet.math.tdouble.DoubleFunctions;

import java.util.Random;

public enum EDirection {

    RIGHT(1,0),
    RIGHTUP(1,1),
    UP(0,1),
    LEFTUP(-1,1),
    LEFT(-1,0),
    LEFTDOWN(-1,-1),
    DOWN(0,-1),
    RIGHTDOWN(1,-1);

    private final DoubleMatrix1D m_directionmatrix;

    EDirection(int x, int y)
    {
        m_directionmatrix = new DenseDoubleMatrix1D(new double[]{x,y});
    }

    public DoubleMatrix1D nextposition(DoubleMatrix1D p_position, double p_speed) {
        return new DenseDoubleMatrix1D(p_position.toArray())
                .assign(
                        new DenseDoubleMatrix1D(m_directionmatrix.toArray()).assign(DoubleFunctions.mult(p_speed)),
                        DoubleFunctions.plus
                );
    }

    public static EDirection random()
    {
        return values()[new Random().nextInt(values().length)];
    }
}
