package de.tu_clausthal.in.mec.simulatorsb.service;

import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import com.google.gson.Gson;
import de.tu_clausthal.in.mec.simulatorsb.resource.CEntityData;
import de.tu_clausthal.in.mec.simulatorsb.simulator.common.CConfiguration;
import de.tu_clausthal.in.mec.simulatorsb.simulator.core.CSimulator;
import de.tu_clausthal.in.mec.simulatorsb.simulator.core.ISimulator;
import de.tu_clausthal.in.mec.simulatorsb.simulator.entities.CCar;
import de.tu_clausthal.in.mec.simulatorsb.simulator.entities.IEntity;
import de.tu_clausthal.in.mec.simulatorsb.simulator.environment.CEnvironment;
import de.tu_clausthal.in.mec.simulatorsb.simulator.environment.IEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CSimulationService
{
    private final AtomicReference<ISimulator> m_simulator;
    private final IEnvironment m_env;
    private final List< IEntity > m_entities;
    private final SimpMessagingTemplate m_template;
    private final Gson gson = new Gson();

    @Autowired
    public CSimulationService( SimpMessagingTemplate p_template )
    {
        m_template = p_template;

        //load configuration
        CConfiguration.INSTANCE.load( "config.yaml" );
        int l_rows = ( int ) CConfiguration.INSTANCE.get( "grid", "rows" );
        int l_columns = ( int ) CConfiguration.INSTANCE.get( "grid", "columns" );
        double l_speed = ( double ) CConfiguration.INSTANCE.get( "car", "speed" );
        long l_timestep = ( int ) CConfiguration.INSTANCE.get( "simulation", "timestep" );

        //create environment and load entities
        m_env = new CEnvironment( l_rows, l_columns );
        m_entities = new ArrayList<>();
        m_entities.add( new CCar( l_speed, new DenseDoubleMatrix1D( new double[]{ 185, 100 } ), m_env ) );
        m_env.put( m_entities );
        System.out.println(
            "entity 1 start position: " + m_env.entities().get( 0 ).position() + ", direction: " + m_env.entities().get( 0 ).direction() );

        //instantiate simulating task and simulator, and start the simulator in a new thread
        m_simulator = new AtomicReference<>( new CSimulator( () -> {
            m_env.execute();
            senddata();
        }, l_timestep ) );
    }

    public void start()
    {
        if ( m_simulator.get().active() )
        {
            System.out.println( "simulation started" );
             m_simulator.get().simulate();
        }
    }

    public void stop()
    {
        if ( m_simulator.get().active() )
        {
            m_simulator.get().deactivate();
            System.out.println( "simulation stopped" );
        }
    }

    public void resume()
    {
        if ( !m_simulator.get().active() )
        {
            m_simulator.get().activate();
            m_simulator.get().simulate();
            System.out.println( "simulation resumed" );
        }
    }

    public void senddata()
    {
        CEntityData l_data = new CEntityData( m_entities.get( 0 ).speed(),
                                              new double[]{ m_entities.get( 0 ).position().get( 0 ), m_entities.get( 0 ).position().get( 1 ) } );
        System.out.println( "data sent, speed: " + l_data.speed() + ", position: " + Arrays.toString( l_data.position() ) );
        m_template.convertAndSend( "/car/data", gson.toJson( l_data ) );
    }
}
