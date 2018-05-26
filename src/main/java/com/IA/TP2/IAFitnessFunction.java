package com.IA.TP2;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class IAFitnessFunction extends FitnessFunction {

	private int a_targetChangeAmount;

	private int lionsRight = 3;
	private int buffalosRight = 3;
	private int lionsLeft = 0;
	private int buffalosLeft = 0;

	private int solutionValue = 0;

	@Override
	protected double evaluate(IChromosome chromosome) {
		int magicNumber = 43;

		int successJourneys = this.getSuccessJourneys(chromosome);
		int leftValue = (this.lionsLeft + this.buffalosLeft) * magicNumber;
		int rightValue = (this.lionsRight + this.buffalosRight) * magicNumber;

		return Math.abs(leftValue - rightValue - successJourneys + solutionValue);
	}

    public int getSuccessJourneys(IChromosome a_potentialSolution){
		int successTravels = 0;

		for (int i = 0; i<20; i++) {
			Integer geneStatus = (Integer) a_potentialSolution.getGene(i).getAllele();
			JourneyStatus.get(geneStatus).evaluate(isEven(i), this);

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
		return this.buffalosRight == 0 && this.lionsRight == 0 && this.lionsLeft == 3 && this.buffalosLeft == 3;
	}

	private boolean validState(){
		return this.lionsLeft <= this.buffalosLeft && this.lionsRight <= this.buffalosRight
				&& this.validAmount(this.buffalosRight) && this.validAmount(this.buffalosLeft)
				&& this.validAmount(this.lionsLeft) && this.validAmount(this.lionsRight);
	}

	private boolean validAmount(int quantity){
		return quantity >= 0 && quantity <= 3;
	}

	public void moveBuffalosFromRight(int quantity){
		this.buffalosRight = this.buffalosRight - quantity;
		this.buffalosLeft = this.buffalosLeft + quantity;
	}

	public void moveLionsFromRight(int quantity){
		this.lionsRight = this.lionsRight - quantity;
		this.lionsLeft = this.lionsLeft + quantity;
	}

	public void moveBuffalosFromLeft(int quantity){
		this.buffalosRight = this.buffalosRight + quantity;
		this.buffalosLeft = this.buffalosLeft - quantity;
	}

	public void moveLionsFromLeft(int quantity){
		this.lionsRight = this.lionsRight + quantity;
		this.lionsLeft = this.lionsLeft - quantity;
	}

	public int getLionsRight() {
		return lionsRight;
	}

	public int getBuffalosRight() {
		return buffalosRight;
	}

	public int getLionsLeft() {
		return lionsLeft;
	}

	public int getBuffalosLeft() {
		return buffalosLeft;
	}
}
