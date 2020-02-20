package de.tu_clausthal.in.mec.bachelorproject.simulator.environment;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import de.tu_clausthal.in.mec.bachelorproject.simulator.entities.IEntity;

import java.util.List;

public interface IEnvironment {

    void put(List<IEntity> p_entitis);

    void execute();

    void remove(IEntity p_entity);

    IEntity move(IEntity p_entity, DoubleMatrix1D p_position);

    List<IEntity> entities();

    boolean positionbyondgrid(DoubleMatrix1D p_position);
}
