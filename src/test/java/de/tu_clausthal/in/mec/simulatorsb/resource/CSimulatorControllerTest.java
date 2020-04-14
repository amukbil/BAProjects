package de.tu_clausthal.in.mec.simulatorsb.resource;

import de.tu_clausthal.in.mec.simulatorsb.service.CSimulationService;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CSimulatorControllerTest
{

    @Test
    void start() throws InterruptedException
    {
        String con = new CSimulatorController( new CSimulationService( new SimpMessagingTemplate( null )) ).start();
        Thread.sleep( 1000 );
    }
}