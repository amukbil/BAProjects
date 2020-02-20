package de.tu_clausthal.in.mec.bachelorproject.simulator.entities;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import de.tu_clausthal.in.mec.bachelorproject.simulator.common.EDirection;

import java.util.concurrent.Callable;

public interface IEntity extends Callable {

    void speed(double p_speed);

    double speed();

    void position(DoubleMatrix1D p_position);

    DoubleMatrix1D position();

    EDirection direction();
}
