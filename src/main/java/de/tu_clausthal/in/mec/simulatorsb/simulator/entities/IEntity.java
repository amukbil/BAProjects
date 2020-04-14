package de.tu_clausthal.in.mec.simulatorsb.simulator.entities;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import de.tu_clausthal.in.mec.simulatorsb.simulator.common.EDirection;

import java.util.concurrent.Callable;

/**
 * entity interface
 */
public interface IEntity extends Callable
{
    /**
     * set a speed
     *
     * @param p_speed speed
     */
    void speed( double p_speed );

    /**
     * return speed value of the entity
     *
     * @return speed
     */
    double speed();

    /**
     * set a new position
     *
     * @param p_position position instance
     */
    void position( DoubleMatrix1D p_position );

    /**
     * returns current position of the entity
     *
     * @return position instance
     */
    DoubleMatrix1D position();

    /**
     * return current movement direction
     *
     * @return direction instance
     */
    EDirection direction();
}
