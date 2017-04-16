package com.TP.AG;

/**
 * 
 * @author Nouriddin BEN ZEKRI
 * Implementation de AG du problème d'attirissage
 *  des avions sur une piste d'Airoport
 *
 */

public class AlgorithmeGenetique {

	private static int[] heureDebut = { 129, 195, 89, 96, 110, 120, 124, 126, 135, 160 };
	private static int[] heureFin = { 559, 744, 510, 521, 555, 576, 577, 573, 591, 657 };
	private static int[] heureNominale = { 155, 258, 98, 106, 123, 135, 138, 140, 150, 180 };
	private static int[] pinalite = { 10, 10, 30, 30, 30, 30, 30, 30, 30, 30 };
	private static int[][] TabSec = new int[10][10];

	// Remplir le tableau de intervale de securité
	public static int[][] insert_data(int[][] TabSec) {
		TabSec = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 15, 15, 0, 0, 0, 0, 0, 0, 0, 0 }, { 15, 15, 8, 0, 0, 0, 0, 0, 0, 0 },
				{ 15, 15, 8, 8, 0, 0, 0, 0, 0, 0 }, { 15, 15, 8, 8, 8, 0, 0, 0, 0, 0 },
				{ 15, 15, 8, 8, 8, 8, 0, 0, 0, 0 }, { 15, 15, 8, 8, 8, 8, 8, 0, 0, 0 },
				{ 15, 15, 8, 8, 8, 8, 8, 8, 0, 0 }, { 15, 15, 8, 8, 8, 8, 8, 8, 8, 0 } };
		for (int i = 0; i < TabSec.length; i++)
			for (int j = 0; j < TabSec.length; j++)
				if (j > i)
					TabSec[i][j] = TabSec[j][i];
		return TabSec;
	}

	// generer un individu alteatoirement
	public static int[] generate_individu(int[] heureDebut, int[] heureFin, int[][] TabSec) {
		int[] ind = new int[10];
		int i = 1, j;

		ind[0] = heureDebut[0] + (int) (Math.random() * (heureFin[0] - heureDebut[0] + 1));
		// System.out.println("0 : " + ind[0]);

		while (i < ind.length) {
			ind[i] = heureDebut[i] + (int) (Math.random() * (heureFin[i] - heureDebut[i] + 1));
			j = i - 1;
			while (j >= 0) {
				// System.out.println("ind[" + i + "] - ind[" + j + "] = " +
				// Math.abs(ind[i] - ind[j]) + " \t | tabsec: "
				// + TabSec[i][j]);
				if (Math.abs(ind[i] - ind[j]) < TabSec[i][j]) {
					i--;
					j = -1;
				}
				j--;
			}
			i++;
		}
		return ind;
	}

	// generer un liste qui contient nbr_ind individus pour contriure la
	// population initiale
	public static int[][] generate_population(int nbr_ind, int[][] TabSecur) {
		int[][] population = new int[nbr_ind][10];
		int[] ind = new int[10];

		for (int i = 0; i < population.length; i++) {
			ind = generate_individu(heureDebut, heureFin, TabSecur);
			for (int j = 0; j < 10; j++) {
				population[i][j] = ind[j];
			}
		}

		return population;
	}

	// Methode qui affiche une tableau unidimentionnel
	public static void show(int[] ind) {
		// show Table
		System.out.println("\n Un individu Aleatoire:");
		for (int i = 0; i < ind.length; i++)
			System.out.print(ind[i] + "|");

	}

	// Methode qui affiche une tableau bidimentionnel avec le titre de tableau à
	// afficher
	public static void show(int[][] TabSec, String title) {
		// show Table
		System.out.println(title);
		for (int i = 0; i < TabSec.length; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < TabSec[i].length; j++)
				System.out.print(TabSec[i][j] + "\t");
			System.out.println("");
		}

	}

	// Main
	public static void main(String[] args) {
		int popTaille = 10; // talle de la population
		TabSec = insert_data(TabSec);
		show(TabSec, "Le tableau de Securité:");
		int[][] population = generate_population(popTaille, TabSec);
		show(population, "Population de " + popTaille + " individus:");
		
		

	}

}
