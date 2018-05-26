package com.IA.TP2;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception{
        Configuration conf = new DefaultConfiguration();
        conf.setPreservFittestIndividual(true);
        conf.setKeepPopulationSizeConstant(false);

        FitnessFunction fitnessFunction = new IAFitnessFunction();

        int MAX_ALLOWED_EVOLUTIONS = 50;

        int lowerBounds = 1;
        int upperBounds = 5;

        int populationSize = 10000;

        conf.setFitnessFunction(fitnessFunction);

        /*
        * 1. 1 leon
        * 2. 1 bufalo
        * 3. 2 leones
        * 4. 2 bufalos
        * 5. 1 leon y 1 bufalo
         */
        Gene[] sampleGenes = new Gene[20];
        for (int i = 0; i<20; i++)
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

        System.out.println("The best solution has a fitness value of " + bestSolutionSoFar.getFitnessValue());
        bestSolutionSoFar.setFitnessValueDirectly(-1);
        IAFitnessFunction function = new IAFitnessFunction();
        System.out.println("Successful journeys: " + function.getSuccessJourneys(bestSolutionSoFar));
        System.out.println("Is solution? " + function.isSolution());
        System.out.println("Right buffalos: " + function.getBuffalosRight());
        System.out.println("Right lions: " + function.getLionsRight());
        System.out.println("Left buffalos: " + function.getBuffalosLeft());
        System.out.println("Left lions: " + function.getLionsLeft());
    }

    public static boolean uniqueChromosomes(Population a_pop) {
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
