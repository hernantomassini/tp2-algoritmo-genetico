package com.IA.TP2;

import org.jgap.FitnessFunction;
import org.jgap.Chromosome;
import org.jgap.IChromosome;

public class IAFitnessFunction extends FitnessFunction {

	@Override
	protected double evaluate(IChromosome arg) {
		int amountOfJournies = this.amountOfJournies(arg);
		return ;
	}
	
	private int amountOfJournies(JourneyChromosome potentialSolution ) {
		int successJourneys = potentialSolution.getSuccessJourneys();
		return successJourneys - potentialSolution.countAnimalOnRightSide();
    }
}
