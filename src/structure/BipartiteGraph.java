package structure;

import gnu.trove.set.hash.TIntHashSet;
import info.Setting;
import info.dataReadWrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

import util.MyBitSet;
import util.MyFastBitSet;

public class BipartiteGraph {

	public String inputTXT = Setting.inputFile;
	// this numberOfSmaples represent the number of secondaryIds
	public int numberOfSamples = 20000;
	
	public int numberOfSamplesAll = 20000;

	public int numberOfPrimaryIds = 17770;

	public int numberOfEdges = 2347218;

	public BipartiteGraph() {

		try {

			BufferedReader br = new BufferedReader(
					new FileReader(this.inputTXT));

			String line = br.readLine();

			HashMap<String, String> hm = util.Text.readLineInfos(line);

			this.numberOfSamplesAll = Integer.parseInt(hm.get("numberOfSamplesAll"));
			
			this.numberOfSamples = Integer.parseInt(hm.get("numberOfSamples"));
			

			this.numberOfPrimaryIds = Integer.parseInt(hm
					.get("numberOfPrimaryIds"));

			this.numberOfEdges = Integer.parseInt(hm.get("sumOfCardinarity"));

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BipartiteGraph(String inputTXT) {

		this.inputTXT = inputTXT;

		try {

			BufferedReader br = new BufferedReader(
					new FileReader(this.inputTXT));

			String line = br.readLine();

			HashMap<String, String> hm = util.Text.readLineInfos(line);

			this.numberOfPrimaryIds = Integer.parseInt(hm
					.get("numberOfPrimaryIds"));

			this.numberOfSamples = Integer.parseInt(hm.get("numberOfSamples"));

			this.numberOfEdges = Integer.parseInt(hm.get("sumOfCardinarity"));

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public MyBitSet[] toSecBS() {

		File file = new File(this.inputTXT);

		MyBitSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			adjM = new MyBitSet[this.numberOfSamples];

			br.readLine();

			String line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new MyBitSet(this.numberOfPrimaryIds);

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].set(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public MyFastBitSet[] toSecFBS() {

		File file = new File(this.inputTXT);

		MyFastBitSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			adjM = new MyFastBitSet[this.numberOfSamples];

			String line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new MyFastBitSet();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].set(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public TreeSet<Integer>[] toSecTS() {
		File file = new File(this.inputTXT);

		TreeSet<Integer>[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			adjM = (TreeSet<Integer>[]) Array.newInstance(TreeSet.class,
					this.numberOfSamples);

			br.readLine();

			String line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new TreeSet<Integer>();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].add(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public TIntHashSet[] toSecTHS() {

		File file = new File(this.inputTXT);

		TIntHashSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			adjM = new TIntHashSet[this.numberOfSamples];

			br.readLine();

			String line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new TIntHashSet();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].add(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	// conny: moment deactivate und I will delete it lately
	// public MyBitSet[] toPrimBS() {
	//
	// HashMap<String, String> infos = util.Text
	// .readInfoTXT(dataReadWrite.infoTXT);
	//
	// File file = new File(this.inputTXT);
	//
	// int numberOfPrimaryIds = Integer.parseInt(infos
	// .get("numberOfPrimaryIds"));
	//
	// MyBitSet[] adjM = new MyBitSet[numberOfPrimaryIds];
	//
	// for (int i = 0; i < numberOfPrimaryIds; i++) {
	//
	// adjM[i] = new MyBitSet();
	// }
	//
	// try {
	//
	// BufferedReader br = new BufferedReader(new FileReader(file));
	// br.readLine();
	//
	// br.readLine();
	//
	// String line = br.readLine();
	//
	// // System.out.println(line);
	//
	// while (line != null) {
	// String[] lineInfos = line.split(":");
	// // System.out.println(lineInfos.length);
	// int cardinality = Integer.parseInt(lineInfos[2]);
	//
	// if (cardinality != 0) {
	//
	// int secondaryId = Integer.parseInt(lineInfos[0]);
	//
	// String[] primaryIds = lineInfos[3].split(",");
	// for (int i = 0; i < cardinality; i++) {
	//
	// adjM[Integer.parseInt(primaryIds[i])].set(secondaryId);
	//
	// }
	// }
	//
	// line = br.readLine();
	// }
	//
	// br.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return adjM;
	//
	// }

	public MyBitSet[] toPrimBS() {
		// HashMap<String, String> infos = util.Text
		// .readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(this.inputTXT);

		// int numberOfPrimaryIds = Integer.parseInt(infos
		// .get("numberOfPrimaryIds"));

		int numberOfPrimaryIds = this.numberOfPrimaryIds;

		MyBitSet[] adjM = new MyBitSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new MyBitSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			// System.out.println(line);

			while (line != null) {
				String[] lineInfos = line.split(":");
				// System.out.println(lineInfos.length);
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].set(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public MyFastBitSet[] toPrimFBS() {

		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(this.inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		MyFastBitSet[] adjM = new MyFastBitSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new MyFastBitSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].set(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;
	}

	public TreeSet<Integer>[] toPrimTS() {

		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(this.inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		// MyBitSet[] adjM = new MyBitSet[numberOfPrimaryIds];

		TreeSet<Integer>[] adjM = (TreeSet<Integer>[]) Array.newInstance(
				TreeSet.class, numberOfPrimaryIds);

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new TreeSet<Integer>();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].add(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public TIntHashSet[] toPrimTHS() {
		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(this.inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		TIntHashSet[] adjM = new TIntHashSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new TIntHashSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].add(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	/**
	 * create edges with an array, this array has two dimension, something like
	 * a table. The rows present the number of the edges, the columns present
	 * the two vertices of an edge. Format: rows means the number of the Edges
	 * 
	 * @return int[numberOfEdges][2] edges column[0]:the secondaryId, column[1]
	 *         the primaryId
	 */
	public int[][] generateEdges() {

		int[][] edges = null;

		try {

			BufferedReader br = new BufferedReader(
					new FileReader(this.inputTXT));

			String line = br.readLine();

			edges = new int[this.numberOfEdges][2];

			br.readLine();

			int cnt = 0;

			line = br.readLine();

			while (line != null) {

				String[] lineInfos = line.split(":");

				if (lineInfos.length != 4) {
					line = br.readLine();
					continue;
				}

				int cardi = Integer.parseInt(lineInfos[2]);
				StringTokenizer st = new StringTokenizer(lineInfos[3], ",");
				for (int i = 0; i < cardi; i++) {

					edges[cnt][0] = Integer.parseInt(lineInfos[0]);
					edges[cnt][1] = Integer.parseInt(st.nextToken());

					cnt++;

				}

				line = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return edges;

	}

	/**
	 * create edges with an array, this array has two dimension, something like
	 * a table. The rows present the number of the edges, the columns present
	 * the two vertices of an edge. Format: rows means the number of the Edges
	 * 
	 * @return int[numberOfEdges][2] edges column[0]:the primaryId, column[1]
	 *         the secondaryId
	 */
	public int[][] generateEdgesPrimarySecondary() {

		int[][] edges = null;

		try {

			BufferedReader br = new BufferedReader(
					new FileReader(this.inputTXT));

			String line = br.readLine();

			edges = new int[this.numberOfEdges][2];

			br.readLine();

			int cnt = 0;

			line = br.readLine();

			while (line != null) {

				String[] lineInfos = line.split(":");

				if (lineInfos.length != 4) {
					line = br.readLine();
					continue;
				}

				int cardi = Integer.parseInt(lineInfos[2]);
				StringTokenizer st = new StringTokenizer(lineInfos[3], ",");
				for (int i = 0; i < cardi; i++) {
					edges[cnt][0] = Integer.parseInt(st.nextToken());

					edges[cnt][1] = Integer.parseInt(lineInfos[0]);

					cnt++;

				}

				line = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return edges;

	}

	public static int[] readDegree(MyBitSet[] adjMPrimary) {
		int[] degrees = new int[adjMPrimary.length];
		int length = degrees.length;
		for (int i = 0; i < length; i++) {
			degrees[i] = adjMPrimary[i].cardinality();
		}

		return degrees;
	}

	public int[] readPrimaryDegree() {
		// BipartiteGraph bg = new BipartiteGraph();
		int[] degrees = new int[numberOfPrimaryIds];

		MyBitSet[] adjM = toPrimBS();
		int length = adjM.length;
		for (int i = 0; i < length; i++) {
			degrees[i] = adjM[i].cardinality();

		}

		return degrees;

	}

	public int[] readSecondaryDegree() {

		int[] degrees = new int[numberOfSamples];

		MyBitSet[] adjM = toSecBS();

		for (int i = 0; i < numberOfSamples; i++) {
			degrees[i] = adjM[i].cardinality();

		}

		return degrees;

	}
	
	public static int[] get_degree(MyBitSet[] adjM){
		int[] degree = new int[adjM.length];
		for(int i=0; i<adjM.length; i++){
			degree[i] = adjM[i].cardinality();
		}
		
		
		return degree;
	}

	public static void main(String[] args) {

		BipartiteGraph bp = new BipartiteGraph();

		// long t1 = System.currentTimeMillis();
		//
		// BipartiteGraph bp = new BipartiteGraph();
		// MyBitSet[] bipGraph = bp.bipartiteGraphSecBS();
		//
		// long t2 = System.currentTimeMillis();
		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// System.out.println(bipGraph.length);

		//
		// t1 = System.currentTimeMillis();
		// MyFastBitSet[] bipGraph1 =
		// bipartiteGraphSecFBS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// t2 = System.currentTimeMillis();
		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// t1 = System.currentTimeMillis();
		// TreeSet<Integer>[] bipGraph2 =
		// bipartiteGraphSecTS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// t2 = System.currentTimeMillis();
		//
		// System.out.println(bipGraph2[1].toString());

		// long t1 = System.currentTimeMillis();
		// TIntHashSet[] bipGraph4 =
		// bipartiteGraphSecTHS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// long t2 = System.currentTimeMillis();
		// System.out.println("Time = " + (t2 - t1));
		//
		// System.out.println(bipGraph4[1].toString());

		// long t1 = System.currentTimeMillis();
		// TIntHashSet[] bipGraph5 =
		// bipartiteGraphPrimTHS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// long t2 = System.currentTimeMillis();
		// System.out.println("Time = " + (t2 - t1));
		// System.out.println(bipGraph5.length);
		// int number = 1;
		// System.out.println("cardinarity = " + bipGraph5[number].size() +
		// " : "
		// + bipGraph5[number].toString());

		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// int lineNumber = 1;
		// System.out.println("line = " + lineNumber + ": " + "Cardinality = "
		// + bipGraph2[lineNumber].size());
		// System.out.println(bipGraph[lineNumber]);

	}

}
