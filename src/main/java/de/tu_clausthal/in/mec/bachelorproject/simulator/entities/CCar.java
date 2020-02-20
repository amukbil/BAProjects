package de.tu_clausthal.in.mec.bachelorproject.simulator.entities;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import de.tu_clausthal.in.mec.bachelorproject.simulator.common.EDirection;
import de.tu_clausthal.in.mec.bachelorproject.simulator.environment.IEnvironment;

public class CCar extends IBaseEntity
{

    private double m_speed;
    private DoubleMatrix1D m_position;
    private EDirection m_direction;
    private IEnvironment m_environment;

    public CCar( double p_speed, DoubleMatrix1D p_position, IEnvironment p_environment )
    {
        m_speed = p_speed;
        m_position = p_position;
        m_direction = EDirection.random();
        m_environment = p_environment;
    }

    public Object call() throws Exception
    {
        DoubleMatrix1D l_nextposition = m_direction.nextposition( m_position, m_speed );

        if ( m_environment.positionbyondgrid( l_nextposition ) )
            return this;

        m_environment.move( this, l_nextposition );
        System.out.println( "position: " + m_position );
        return this;
    }

    public void speed( double p_speed )
    {
        m_speed = p_speed;
    }

    public double speed()
    {
        return m_speed;
    }

    public void position( DoubleMatrix1D p_position )
    {
        m_position.setQuick( 0, p_position.getQuick( 0 ) );
        m_position.setQuick( 1, p_position.getQuick( 1 ) );
    }

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