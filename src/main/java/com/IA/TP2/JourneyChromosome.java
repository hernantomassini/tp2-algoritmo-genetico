package com.IA.TP2;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;

import java.util.List;

public class JourneyChromosome extends Chromosome {
	List<Journey> Gens;

	private int successTravels = 0;
	private int bueysRight = 3;
	private int lionsRight = 3;
	private int bueysLeft = 0;
	private int lionsLeft = 0;

	public JourneyChromosome() throws InvalidConfigurationException {
		super();
	}

	public int getSuccessJourneys() {
		this.successTravels = 0;
		for (Journey gen : Gens) {
			switch(gen.GoBoat) {
				case LIONANDBUEY:
					this.moveBueysFromRight(1);
					this.moveLionsFromRight(1);
					break;
				case ONEBUEY:
					this.moveBueysFromRight(1);
					break;
				case TWOBUEYS:
					this.moveBueysFromRight(2);
					break;
				case ONELION:
					this.moveLionsFromRight(1);
					break;
				case TWOLIONS:
					this.moveLionsFromRight(2);
					break;
			}

			if (validState()){
				this.successTravels++;
			}else{
				break;
			}

			if (isSolution())
				break;

			switch(gen.ReturnBoat) {
				case LIONANDBUEY:
					this.moveBueysFromLeft(1);
					this.moveLionsFromLeft(1);
					break;
				case ONEBUEY:
					this.moveBueysFromLeft(1);
					break;
				case TWOBUEYS:
					this.moveBueysFromLeft(2);
					break;
				case ONELION:
					this.moveLionsFromLeft(1);
					break;
				case TWOLIONS:
					this.moveLionsFromLeft(2);
					break;
			}

			if (validState()){
				this.successTravels++;
			}else{
				break;
			}

		}
		return successTravels;
	}

	private boolean isSolution(){
		return this.bueysRight == 0 && this.lionsRight == 0 && this.lionsLeft == 3 && this.bueysLeft == 3;
	}

	private void moveBueysFromRight(int quantity){
		this.bueysRight = this.bueysRight - quantity;
		this.bueysLeft = this.bueysLeft + quantity;
	}

	private void moveLionsFromRight(int quantity){
		this.lionsRight = this.lionsRight - quantity;
		this.lionsLeft = this.lionsLeft + quantity;
	}

	private void moveBueysFromLeft(int quantity){
		this.bueysRight = this.bueysRight + quantity;
		this.bueysLeft = this.bueysLeft - quantity;
	}

	private void moveLionsFromLeft(int quantity){
		this.lionsRight = this.lionsRight + quantity;
		this.lionsLeft = this.lionsLeft - quantity;
	}

	private boolean validState(){
		return this.lionsLeft <= this.bueysLeft && this.lionsRight <= this.bueysRight
				&& this.validAmount(this.bueysRight) && this.validAmount(this.bueysLeft)
				&& this.validAmount(this.lionsLeft) && this.validAmount(this.lionsRight);
	}

	private boolean validAmount(int quantity){
		return quantity >= 0 && quantity <= 3;
	}

	public int countAnimalOnRightSide() {
		return this.lionsRight + this.bueysRight;
	}
}
