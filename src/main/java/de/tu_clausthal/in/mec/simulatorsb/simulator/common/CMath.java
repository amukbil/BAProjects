package de.tu_clausthal.in.mec.simulatorsb.simulator.common;

import cern.colt.matrix.AbstractFormatter;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tdouble.algo.DoubleFormatter;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;

public class CMath {

    public static final DenseDoubleAlgebra ALGEBRA = DenseDoubleAlgebra.DEFAULT;

    public static final RandomGenerator RANDOM = new SynchronizedRandomGenerator(new JDKRandomGenerator());

    public static final AbstractFormatter FORMATTER = new DoubleFormatter();

    private CMath(){

    }

    static
    {
        FORMATTER.setRowSeparator( "; " );
        FORMATTER.setColumnSeparator( "," );
        FORMATTER.setPrintShape( false );
    }

}
