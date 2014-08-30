package randomBG;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.hash.TIntHashSet;

import java.util.Collections;
import java.util.Random;

import structure.BipartiteGraph;
import util.MyBitSet;

public class CreateBG {

	public static void create(String inputFile) {

		Random rnd = new Random(3266);

		BipartiteGraph bG = new BipartiteGraph(inputFile);
		MyBitSet[] adjM_User = bG.toSecBS();
		int[] user_Degree = bG.get_degree(adjM_User);
		MyBitSet[] adjM_Movie = bG.toPrimBS();

		// the first column store the original cardinality, the second column
		// store the real time cardinality.
		int[][] movie_Degree_all = new int[adjM_Movie.length][2];

		get_Degree_inCol(adjM_Movie, movie_Degree_all, 0);

		randomGraph_after_Degree(adjM_User, user_Degree, bG.numberOfPrimaryIds,
				rnd);

		adjM_Movie = turn_adjM(adjM_User, bG.numberOfPrimaryIds);

		for (int i = 0; i < adjM_Movie.length; i++) {
			System.out.println(i + ": " + adjM_Movie[i].mytoString(","));
		}

		degreeExtrading(adjM_Movie, movie_Degree_all, rnd);

	}

	public static void degreeExtrading(MyBitSet[] adjM_Movie,
			int[][] movie_Degree_all, Random rnd) {
		get_Degree_inCol(adjM_Movie, movie_Degree_all, 1);

		TIntHashSet hs_deficit = new TIntHashSet();
		TIntHashSet hs_surplus = new TIntHashSet();
		TIntHashSet hs_equal = new TIntHashSet();

		for (int i = 0; i < movie_Degree_all.length; i++) {
			System.out.println(movie_Degree_all[i][0] + ","
					+ movie_Degree_all[i][1]);
		}

		divide3Groups(movie_Degree_all, hs_deficit, hs_surplus, hs_equal);

		move_Surplus_to_Defizit(hs_deficit, hs_surplus, hs_equal,
				movie_Degree_all, adjM_Movie, rnd);

	}

	public static void move_Surplus_to_Defizit(TIntHashSet hs_deficit,
			TIntHashSet hs_surplus, TIntHashSet hs_equal,
			int[][] movie_Degree_all, MyBitSet[] adjM_Movie, Random rnd) {
	
		TIntArrayList tal_surplus = new TIntArrayList(hs_surplus.toArray());
		TIntArrayList tal_deficit = new TIntArrayList(hs_deficit.toArray());
		TIntArrayList tal_equal = new TIntArrayList(hs_equal.toArray());
		
		tal_surplus.shuffle(rnd);
		tal_deficit.shuffle(rnd);
		tal_equal.shuffle(rnd);

		
		
		

//		TIntIterator it_surplus = hs_surplus.iterator();
//
//		while (it_surplus.hasNext()) {
//			int surplus_Movie = it_surplus.next();
//
//			int surplus_Number = movie_Degree_all[surplus_Movie][1]
//					- movie_Degree_all[surplus_Movie][0];
//
//			int[] surplus_Movie_Users = adjM_Movie[surplus_Movie].toArray();
//
//			TIntHashSet surps = new TIntHashSet();
//
//			while (surps.size() < surplus_Number) {
//				surps.add(surplus_Movie_Users[rnd
//						.nextInt(surplus_Movie_Users.length)]);
//			}
//
//			TIntIterator it_surps = surps.iterator();
//
//			while (it_surps.hasNext()) {
//
//			}
//
//		}

	}

	public static int randomGetMybitSet(MyBitSet mbs, Random rnd) {
		int order = rnd.nextInt(mbs.cardinality());
		int randomResult = 0;
		for (int i = 0; i < order + 1; i++) {
			randomResult = mbs.nextSetBit(randomResult + 1);

		}

		return randomResult;
	}

	public static void divide3Groups(int[][] movie_Degree_all,
			TIntHashSet hs_deficit, TIntHashSet hs_surplus, TIntHashSet hs_equal) {
		for (int i = 0; i < movie_Degree_all.length; i++) {
			if (movie_Degree_all[i][0] > movie_Degree_all[i][1]) {
				hs_deficit.add(i);
			} else if (movie_Degree_all[i][0] < movie_Degree_all[i][1]) {
				hs_surplus.add(i);
			} else {
				hs_equal.add(i);
			}
		}

	}

	public static MyBitSet[] turn_adjM(MyBitSet[] adjM, int numberOfRows) {
		MyBitSet[] adjM_2 = new MyBitSet[numberOfRows];
		for (int i = 0; i < numberOfRows; i++) {
			adjM_2[i] = new MyBitSet();

		}

		for (int i = 0; i < adjM.length; i++) {

			for (int j = adjM[i].nextSetBit(0); j >= 0; j = adjM[i]
					.nextSetBit(j + 1)) {
				adjM_2[j].set(i);
			}

		}

		return adjM_2;
	}

	/**
	 * create a random bipartite graph after user degree without reguard movie
	 * degree!
	 * 
	 * @param adjM_User
	 * @param user_Degree
	 * @param range
	 * @param rnd
	 */
	public static void randomGraph_after_Degree(MyBitSet[] adjM_User,
			int[] user_Degree, int range, Random rnd) {

		for (int i = 0; i < user_Degree.length; i++) {
			adjM_User[i] = getRandomMBS(user_Degree[i], range, rnd);

		}

	}

	/**
	 * randomly create for a user a list of movies with original degree.
	 * 
	 * @param degree
	 *            (netflix is the user_Degree)
	 * @param range
	 *            number of Movies (netflix is 17770)
	 * @param rnd
	 * @return
	 */
	public static MyBitSet getRandomMBS(int degree, int range, Random rnd) {

		MyBitSet movies = new MyBitSet();

		for (int i = 0; i < degree; i++) {
			movies.set(rnd.nextInt(range));

		}

		while (movies.cardinality() < degree) {
			movies.set(rnd.nextInt(range));
		}

		return movies;
	}

	/**
	 * clear the adjascence Matrix
	 * 
	 * @param adjM
	 */
	public static void adjM_clear(MyBitSet[] adjM) {
		for (int i = 0; i < adjM.length; i++) {
			adjM[i].clear();
		}

	}

	/**
	 * 
	 * @param adjM
	 * @param degrees_Info
	 * @param col
	 *            start with 0
	 */
	public static void get_Degree_inCol(MyBitSet[] adjM, int[][] degrees_Info,
			int col) {
		if (adjM.length != degrees_Info.length) {
			System.err.println("degrees_info.length != adjM.length");
			System.exit(-1);
		}

		if (col > degrees_Info[0].length) {
			System.err
					.println("the number of Colum: \"col\" should smaller than "
							+ degrees_Info[0].length);
			System.exit(-1);

		}

		for (int i = 0; i < adjM.length; i++) {
			degrees_Info[i][col] = adjM[i].cardinality();

		}

	}

	public static void fixDeg_randomGraph(int[] degree, int range, Random rnd) {

	}

	public static void main(String[] args) {

		String inputFile = "Example/model1data.txt";
		create(inputFile);

	}

}
