package com.IA.TP2;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;

import java.util.List;

public class JourneyChromosome extends Chromosome {
	List<Journey> Gens;
	
	public JourneyChromosome() throws InvalidConfigurationException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSuccessJourneys() {
		int successTravels = 0;
		int bueysRight = 3;
		int lionsRight = 3;
		int bueysLeft = 0;
		int lionsLeft = 0;
		
		Gens.forEach(gen ->{
			switch(gen.GoBoat) {
			case 
			}
		});
	}

	public int countAnimalOnRightSide() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
