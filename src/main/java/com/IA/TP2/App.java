package com.IA.TP2;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


public class App {

    public static void main(String[] args) throws Exception{
        Configuration.reset();
        Configuration conf = new DefaultConfiguration();
        conf.setPreservFittestIndividual(true);
        conf.setKeepPopulationSizeConstant(false);

        FitnessFunction fitnessFunction = new IAFitnessFunction();

        int MAX_ALLOWED_EVOLUTIONS = 50;

        int lowerBounds = 1;
        int upperBounds = 5;

        int populationSize = 1000;

        conf.setFitnessFunction(fitnessFunction);

        Gene[] sampleGenes = new Gene[20];
        for (int i = 0; i < 20; i++)
            sampleGenes[i] = new IntegerGene(conf, lowerBounds, upperBounds);

        IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);

        conf.setPopulationSize(populationSize);

        Genotype population = Genotype.randomInitialGenotype(conf);

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            if (!uniqueChromosomes(population.getPopulation())) {
                throw new RuntimeException("Invalid state in generation " + i);
            }
            population.evolve();
        }

        IChromosome bestSolutionSoFar = population.getFittestChromosome();
        IAFitnessFunction function = new IAFitnessFunction();
        System.out.println("Population: " + populationSize);
        System.out.println("The best solution has a fitness value of " + bestSolutionSoFar.getFitnessValue());
        bestSolutionSoFar.setFitnessValueDirectly(-1);
        int successfulJournies = function.getSuccessJourneys(bestSolutionSoFar);
        System.out.println("Successful journeys: " + successfulJournies);
        System.out.println("Is solution? " + function.isSolution());
        System.out.println("Right buffalos: " + function.getRightBuffalos());
        System.out.println("Right lions: " + function.getRightLions());
        System.out.println("Left buffalos: " + function.getLeftBuffalos());
        System.out.println("Left lions: " + function.getLeftLions());
        System.out.println();
        System.out.println("Flow:\n");
        printArray(bestSolutionSoFar.getGenes(), successfulJournies);
    }

    private static void printArray(Gene[] genes, int successfulJournies){
        for (int i = 1; i<=successfulJournies; i++) {
            System.out.println("Trip: " + i);
            System.out.println("Boat: " + JourneyStatus.get((Integer) genes[i-1].getAllele()));
        }
    }

    private static boolean uniqueChromosomes(Population a_pop) {
        for(int i=0;i<a_pop.size()-1;i++) {
            IChromosome c = a_pop.getChromosome(i);
            for(int j=i+1;j<a_pop.size();j++) {
                IChromosome c2 =a_pop.getChromosome(j);
                if (c == c2) {
                    return false;
                }
            }
        }
        return true;
    }
}
