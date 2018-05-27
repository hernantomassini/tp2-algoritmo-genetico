package com.IA.TP2;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

import java.util.Random;

public class IAFitnessFunction extends FitnessFunction {

	private int a_targetChangeAmount;

	private int lionsRight = 3;
	private int buffalosRight = 3;
	private int lionsLeft = 0;
	private int buffalosLeft = 0;

	private int solutionValue = 0;

	@Override
	protected double evaluate(IChromosome chromosome) {
		try {
			int magicNumber = 10;

			int successJourneys = this.getSuccessJourneys(chromosome);
			int leftValue = (this.lionsLeft + this.buffalosLeft) * magicNumber;
			int rightValue = (this.lionsRight + this.buffalosRight);

			return Math.max(leftValue - rightValue - successJourneys + solutionValue, 0);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

    public int getSuccessJourneys(IChromosome a_potentialSolution) throws Exception{
		int successTravels = 0;

		Gene[] genes = a_potentialSolution.getGenes();

		lionsRight = 3;
		buffalosRight = 3;
		lionsLeft = 0;
		buffalosLeft = 0;

		solutionValue = 0;

		int retries = 200;

		while(successTravels<20){
			Integer geneStatus = (Integer) genes[successTravels].getAllele();

			boolean isEven = isEven(successTravels);

			JourneyStatus journeyStatus = JourneyStatus.get(geneStatus);
			journeyStatus.evaluate(isEven, this);

			if (validState()) {
				successTravels++;
				retries = 5;
			}else{
				journeyStatus.rollBack(isEven, this);
				if (retries > 0) {
					genes[successTravels].setAllele(new Random().nextInt(4) + 1);
					retries--;
				}else
					break;
			}

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
		return (this.buffalosLeft == 0 || this.lionsLeft <= this.buffalosLeft)
				&& (this.lionsRight <= this.buffalosRight || this.buffalosRight == 0)
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