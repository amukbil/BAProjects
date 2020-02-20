package de.tu_clausthal.in.mec.bachelorproject.simulator;

import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import de.tu_clausthal.in.mec.bachelorproject.simulator.common.CConfiguration;
import de.tu_clausthal.in.mec.bachelorproject.simulator.core.CSimulator;
import de.tu_clausthal.in.mec.bachelorproject.simulator.core.ISimulator;
import de.tu_clausthal.in.mec.bachelorproject.simulator.core.Simulatable;
import de.tu_clausthal.in.mec.bachelorproject.simulator.entities.CCar;
import de.tu_clausthal.in.mec.bachelorproject.simulator.entities.IEntity;
import de.tu_clausthal.in.mec.bachelorproject.simulator.environment.CEnvironment;
import de.tu_clausthal.in.mec.bachelorproject.simulator.environment.IEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Main
{
    public static void main( String[] args )
    {
        //load configuration
        CConfiguration.INSTANCE.load( "config.yaml" );
        int l_rows = ( int ) CConfiguration.INSTANCE.get( "grid", "rows" );
        int l_columns = ( int ) CConfiguration.INSTANCE.get( "grid", "columns" );
        long l_timestep = ( int ) CConfiguration.INSTANCE.get( "simulation", "timestep" );

        //create environment and load entities
        IEnvironment l_env = new CEnvironment( l_rows, l_columns );
        List< IEntity > l_entities = new ArrayList<>();
        l_entities.add( new CCar( 1, new DenseDoubleMatrix1D( new double[]{ ( l_rows / 2 ), l_columns / 2 } ), l_env ) );
        l_env.put( l_entities );
        System.out.println(
            "entity 1 start position: " + l_env.entities().get( 0 ).position() + ", direction: " + l_env.entities().get( 0 ).direction() );

        //instantiate simulating task and simulator, and start the simulator in a new thread
        Simulatable l_task = () -> l_env.execute();
        ISimulator l_simulator = new CSimulator( l_task, l_timestep );
        new Thread( () -> l_simulator.simulate() ).start();

        //stop simulation after 1s
        try
        {
            Thread.sleep( 1000 );
            if ( l_simulator.active() )
                l_simulator.deactivate();
            System.out.println( "simulation deactivated" );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }

    /**
     * start simulation method, another way of simulation loop
     *
     * @param p_env environment instance
     */
    private static void startsimulation( IEnvironment p_env )
    {
        new Thread( () -> IntStream.range( 0, 100 ).forEach( n -> {
            try
            {
                Thread.sleep( ( int ) CConfiguration.INSTANCE.get( "runtime", "sleep" ) );
                p_env.execute();
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
        } ) ).start();
    }
}