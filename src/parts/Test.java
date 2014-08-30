package parts;

import info.Setting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

import structure.BipartiteGraph;
import structure.CooccFkt;
import util.MyBitSet;

public class Test {

	public static void writeCoocc(String inputFile, String outputFile) {
		BipartiteGraph bG = new BipartiteGraph(inputFile);

		MyBitSet[] adjMSec = bG.toSecBS();

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		CooccFkt.readCooccSecAddTopRight(adjMSec, coocc);

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write("#<computer_MovieId1> <computer_MovieId2> <co-occurrence>"
					+ System.lineSeparator());
			for (int i = 0; i < coocc.length; i++) {
				for (int j = i + 1; j < coocc.length; j++) {
					if (coocc[i][j] > 0) {

						bw.write(i + " " + j + " "+coocc[i][j]+System.lineSeparator());

					}

				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	


	public static void main(String[] args) {

//		String inputFile = Setting.outputRoot + File.separator
//				+ "Netflix_Dataset_Good_1k_converted";
//
//		String outputFile = inputFile.replace("_converted", "_coocc");
//
//		writeCoocc(inputFile, outputFile);
		
		
	}

}
