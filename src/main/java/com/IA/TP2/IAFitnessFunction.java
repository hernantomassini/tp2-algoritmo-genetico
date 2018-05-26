package com.IA.TP2;

import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class IAFitnessFunction extends FitnessFunction {

	@Override
	protected double evaluate(IChromosome arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static int amountOfChange(JourneyChromosome a_potentialSolution )
    {
		int successJourneys = a_potentialSolution.getSuccessJourneys();
		return successJourneys - a_potentialSolution.countAnimalOnRightSide();
    }
}
