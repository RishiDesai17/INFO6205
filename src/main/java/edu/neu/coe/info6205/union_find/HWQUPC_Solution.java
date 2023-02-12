package edu.neu.coe.info6205.union_find;

public class HWQUPC_Solution {
	static int pairsGenerated = 0;
	
	public static void main(String[] args) {
		int currSites = 1000;
		while (currSites <= 512000) {
			int totalNumConnections = 0;
			int totalPairsGenerated = 0;
			
			for (int i = 0; i < 20; i++) {
				int numConnections = count(currSites);
				totalNumConnections += numConnections;
				totalPairsGenerated += pairsGenerated;
				pairsGenerated = 0;
			}
			
			int avgNumConnections = totalNumConnections / 20;
			int avgPairsGenerated = totalPairsGenerated / 20;
			
			System.out.println("Number of sites: " + currSites);
			System.out.println("Average number of connections: " + avgNumConnections);
			System.out.println("Average number of pairs generated: " + avgPairsGenerated);
			System.out.println();
			
			currSites *= 2;
		}
	}
	
	static int count(int currSites) {
		int numConnections = 0;
		UF_HWQUPC uf = new UF_HWQUPC(currSites, true);
		
		while (uf.components() > 1) {
			int n1 = (int)(Math.random() * currSites);
			int n2 = (int)(Math.random() * currSites);
			if (!uf.isConnected(n1, n2)) {
				uf.union(n1, n2);
				numConnections += 1;
			}
			pairsGenerated += 1;
		}
		
		return numConnections;
	}
}
