package de.tu_clausthal.in.mec.bachelorproject.simulator.core;

/**
 * simulator interface
 */
public interface ISimulator extends Simulatable
{
    /**
     * activate the current simulation
     *
     * @return simulator instance
     */
    ISimulator activate();

    /**
     * deactivate the current simulation
     *
     * @return simulator instance
     */
    ISimulator deactivate();

    /**
     * check if current simulation is active
     *
     * @return ture if current simulation is active; false otherwise
     */
    boolean active();
}