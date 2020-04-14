package de.tu_clausthal.in.mec.simulatorsb.simulator.entities;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import de.tu_clausthal.in.mec.simulatorsb.simulator.common.EDirection;
import de.tu_clausthal.in.mec.simulatorsb.simulator.environment.IEnvironment;

/**
 * car class
 */
public class CCar extends IBaseEntity
{
    /**
     * car speed in m/s
     */
    private double m_speed;
    /**
     * current position
     */
    private DoubleMatrix1D m_position;
    /**
     * current direction
     */
    private EDirection m_direction;
    /**
     * environment
     */
    private IEnvironment m_environment;

    /**
     * constructor
     *
     * @param p_speed speed
     * @param p_position starting position
     * @param p_environment environment instance
     */
    public CCar( double p_speed, DoubleMatrix1D p_position, IEnvironment p_environment )
    {
        m_speed = p_speed;
        m_position = p_position;
        m_direction = EDirection.RIGHT;
        m_environment = p_environment;
    }

    @Override
    public Object call() throws Exception
    {
        DoubleMatrix1D l_nextposition = m_direction.nextposition( m_position, m_speed );

        if ( m_environment.positionbyondgrid( l_nextposition ) )
            return this;

        m_environment.move( this, l_nextposition );
        System.out.println( "position: " + m_position + ", speed: " + m_speed );
        return this;
    }

    @Override
    public void speed( double p_speed )
    {
        m_speed = p_speed;
    }

    @Override
    public double speed()
    {
        return m_speed;
    }

    @Override
    public void position( DoubleMatrix1D p_position )
    {
        m_position.setQuick( 0, p_position.getQuick( 0 ) );
        m_position.setQuick( 1, p_position.getQuick( 1 ) );
    }

    @Override
    public DoubleMatrix1D position()
    {
        return m_position;
    }

    @Override
    public EDirection direction()
    {
        return m_direction;
    }
}