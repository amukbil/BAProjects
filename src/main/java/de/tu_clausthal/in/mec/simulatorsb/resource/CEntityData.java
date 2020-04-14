package de.tu_clausthal.in.mec.simulatorsb.resource;

/**
 * entity data model to be sent to the client and converted into JSON message
 */
public class CEntityData
{
    private double speed;
    private double[] position;

    public CEntityData( double p_speed, double[] p_position )
    {
        position = p_position;
        speed = p_speed;
    }

    public double[] position()
    {
        return position;
    }

    public double speed()
    {
        return speed;
    }
}
