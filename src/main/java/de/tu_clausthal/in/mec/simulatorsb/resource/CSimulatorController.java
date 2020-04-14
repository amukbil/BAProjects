package de.tu_clausthal.in.mec.simulatorsb.resource;

import de.tu_clausthal.in.mec.simulatorsb.service.CSimulationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CSimulatorController
{
    private final CSimulationService m_simulation;

    public CSimulatorController( CSimulationService p_simulation )
    {
        m_simulation = p_simulation;
    }

    @MessageMapping( "/start" )
    @SendTo( "/control/start" )
    public String start()
    {
        m_simulation.start();
        return "simulation started";
    }

    @MessageMapping( "/stop" )
    @SendTo( "/control/stop" )
    public String stop()
    {
        m_simulation.stop();
        return "simulation stopped";
    }

    @MessageMapping( "/resume" )
    @SendTo( "/control/resume" )
    public String resume()
    {
        m_simulation.resume();
        return "simulation resumed";
    }
}
