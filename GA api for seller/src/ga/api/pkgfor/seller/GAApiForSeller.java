/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.api.pkgfor.seller;

import data.Cities;
import data.City;
import Constant.Condition;
import Constant.Const;
import Constant.Range;
import genetics.Chromosome;
import genetics.Population;

/**
 *
 * @author Mahmud
 */
public class GAApiForSeller {

    /**
     * @param args the command line arguments
     */
    static Cities cities = new Cities(6);

    public static void main(String[] args) {
        cities.append(new City("A", new double[]{Double.MAX_VALUE, 23, 40, 60, 40, 7}, 0));

        cities.append(new City("B", new double[]{23, Double.MAX_VALUE, 12, 30, 60, 8}, 1));

        cities.append(new City("C", new double[]{40, 12, Double.MAX_VALUE, 14, 20, 20}, 2));

        cities.append(new City("D", new double[]{60, 30, 14, Double.MAX_VALUE, 13, 23}, 3));

        cities.append(new City("E", new double[]{40, 60, 20, 13, Double.MAX_VALUE, 5}, 4));

        cities.append(new City("F", new double[]{7, 8, 20, 23, 5, Double.MAX_VALUE}, 5));
        
//        cities.append(new City("A", new double[]{Double.MAX_VALUE, 35, Double.MAX_VALUE, Double.MAX_VALUE, 40, 7}, 0));
//
//        cities.append(new City("B", new double[]{35, Double.MAX_VALUE, 12, Double.MAX_VALUE, Double.MAX_VALUE, 8}, 1));
//
//        cities.append(new City("C", new double[]{Double.MAX_VALUE, 12, Double.MAX_VALUE, 14, Double.MAX_VALUE, 20}, 2));
//
//        cities.append(new City("D", new double[]{Double.MAX_VALUE, Double.MAX_VALUE, 14, Double.MAX_VALUE, 13, 23}, 3));
//
//        cities.append(new City("E", new double[]{40, Double.MAX_VALUE, Double.MAX_VALUE, 13, Double.MAX_VALUE, 5}, 4));
//
//        cities.append(new City("F", new double[]{7, 8, 20, 23, 5, Double.MAX_VALUE}, 5));


        int directionn[] = new int[]{0, 5, 4, 3, 2, 1};
        System.out.println(cities.compute(directionn));

        Const.setFunction((Double... x) -> {
            var direction = new int[x.length];
            for (int i = 0; i < direction.length; i++) {
                direction[i] = (int) x[i].doubleValue();
            }
            return cities.compute(direction);
        });

        Const.setConditions(new Condition<Double>() {
            @Override
            public Boolean compute(Double... x) {
                for (int i = 0; i < x.length; i++) {
                    for (int j = 0; j < x.length; j++) {
                        if (i != j && x[j].equals(x[i])) {
                           return false;
                        }

                    }
                }
                return true;
            }
        });

        Const.setSearch(Const.MIN);
        Const.setTYPE_DATA(Const.INT_DATA);
        Const.setTYPE_PR(Const.UNIQUE_PR);
        Const.setRanges(new Range[]{
            new Range(0, 5)
        });
        Const.setVar_count(6);
        Population p = new Population();
        int generation = 0;

        long time = System.currentTimeMillis();
        while (generation < 400_000) {
            if (2 * generation % 3 == 0) {
                //     System.out.println("-------------mutation----------------------mutation-------------");
                p.mutate();
            }
            p.computeAllChromosomeValue();
            p.crossOver();

            p.createNewGeneration();
            p.computeAllChromosomeValue();
            p.crossOver();
            p.createNewGeneration();
            // p.printValues();
            generation++;
//            p.printOptimalChromosome();
            Chromosome chromosome = p.chromosomes[p.getMaxChromosome()];
//            chromosome.printElements();
            //            Double[] d = p.getOptimalChromosome();
            if (generation % 10 == 0) {
                System.out.printf("%d optimal chromosome  = %.20f \n", generation - 1, chromosome.getOverAll());
            }
            if (System.currentTimeMillis() - time >= 40 * 1000) {
                System.err.println(generation + "-------break " + (System.currentTimeMillis() - time));
                break;
            }
        }
        System.out.println("\n-------------");
        p.printOptimalChromosome();

    }

}










