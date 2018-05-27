package com.IA.TP2;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class IAFitnessFunction extends FitnessFunction {

	private int rightLions;
	private int rightBuffalos;
	private int leftLions;
	private int leftBuffalos;

	private int solutionValue;

	@Override
	protected double evaluate(IChromosome chromosome) {
		try {
			int incrementalSolutionValue = 10;

			int successJourneys = this.getSuccessJourneys(chromosome);
			int leftValue = (this.leftLions + this.leftBuffalos) * incrementalSolutionValue;
			int rightValue = (this.rightLions + this.rightBuffalos);

			return Math.max(leftValue - rightValue - successJourneys + solutionValue, 0);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

    public int getSuccessJourneys(IChromosome a_potentialSolution) throws Exception{
		int successTravels = 0;

		rightLions = 3;
		rightBuffalos = 3;
		leftLions = 0;
		leftBuffalos = 0;

		solutionValue = 0;

		while(successTravels<20){
			Integer geneStatus = (Integer) a_potentialSolution.getGene(successTravels).getAllele();

			JourneyStatus journeyStatus = JourneyStatus.get(geneStatus);
			journeyStatus.evaluate(isEven(successTravels), this);

			if (validState())
				successTravels++;
			else
				break;

			if (isSolution()){
				this.solutionValue = 500;
				break;
			}
		}

		return successTravels;
	}

	private boolean isEven(int i){
    	return i % 2 == 0;
	}

	public boolean isSolution(){
		return this.rightBuffalos == 0 && this.rightLions == 0 && this.leftLions == 3 && this.leftBuffalos == 3;
	}

	private boolean validState(){
		return (this.leftBuffalos == 0 || this.leftLions <= this.leftBuffalos)
				&& (this.rightLions <= this.rightBuffalos || this.rightBuffalos == 0)
				&& this.validAmount(this.rightBuffalos) && this.validAmount(this.leftBuffalos)
				&& this.validAmount(this.leftLions) && this.validAmount(this.rightLions);
	}

	private boolean validAmount(int quantity){
		return quantity >= 0 && quantity <= 3;
	}

	public void moveBuffalosFromRight(int quantity){
		this.rightBuffalos = this.rightBuffalos - quantity;
		this.leftBuffalos = this.leftBuffalos + quantity;
	}

	public void moveLionsFromRight(int quantity){
		this.rightLions = this.rightLions - quantity;
		this.leftLions = this.leftLions + quantity;
	}

	public void moveBuffalosFromLeft(int quantity){
		this.rightBuffalos = this.rightBuffalos + quantity;
		this.leftBuffalos = this.leftBuffalos - quantity;
	}

	public void moveLionsFromLeft(int quantity){
		this.rightLions = this.rightLions + quantity;
		this.leftLions = this.leftLions - quantity;
	}

	public int getRightLions() {
		return rightLions;
	}

	public int getRightBuffalos() {
		return rightBuffalos;
	}

	public int getLeftLions() {
		return leftLions;
	}

	public int getLeftBuffalos() {
		return leftBuffalos;
	}
}