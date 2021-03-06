package hw8;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class doesn't represent ADT so we don't need to comment on AF and RI
 * This class is used to get direction of two buildings in Campus and some other options,
 * it gets option from client and print specified output, option includes print all buildings,
 * get path from two buildings, print menu, and quit the program
 *
 */
public class CampusMap {
	public static UWMap uwMap;

	public static void main(String[] args)  {
		try{
		uwMap = new UWMap();
		}catch(Exception e){
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		printMenu();
		while (true) {
			System.out.println();
			System.out.print("Enter an option ('m' to see the menu): ");
			String input = sc.nextLine();
			while (input.startsWith("#")||input.isEmpty()) {
				System.out.println(input);
				input = sc.nextLine();
			}
			if (input.equals("b")) {
				Map<String, String> allBuildings = uwMap.allBuildings();
				listBuildings(allBuildings);
			} else if (input.equals("r")) {
				System.out.print("Abbreviated name of starting building: ");
				String startBuilding = sc.nextLine();
				System.out.print("Abbreviated name of ending building: ");
				String endBuilding = sc.nextLine();
				findPath(startBuilding, endBuilding);
			} else if (input.equals("q")) {
				sc.close();
				return;
			} else if (input.equals("m")) {
				printMenu();
			} else {
				System.out.println("Unknown option");
			}
		}
		
	}

	/**
	 * Helper method used print all buildings in the campus map includes
	 * building's short name and full name in specific format
	 * @param allBuildings a map contains information of all buildings in
	 * 		  campus.
	 */
	private static void listBuildings(Map<String, String> allBuildings) {
		System.out.println("Buildings");
		for (String shortName : allBuildings.keySet()) {
			System.out.println("\t" + shortName + ": "
					+ allBuildings.get(shortName));
		}
	}

	/**
	 * Helper method used to find least cost path from given start node
	 * to given end node, prints out path from given start node to given 
	 * end node and the total cost of this path. If given start and end
	 * points are not in the campus print, print out it's an unknown building.
	 * @param start start point of found path
	 * @param end end point of found path
	 */
	private static void findPath(String start, String end) {
		if(!uwMap.nameToPos.containsKey(start)||!uwMap.nameToPos.containsKey(end)){
			if(!uwMap.nameToPos.containsKey(start)){
				System.out.println("Unknown building: "+start);
			}
			if(!uwMap.nameToPos.containsKey(end)){
				System.out.println("Unknown building: "+end);
			}
			return;
		}
		List<Node> path = uwMap.findPath(start, end);
		if (path != null) {
			double totalDistance = 0;
			System.out.println("Path from " + uwMap.buildingsName.get(start)
					+ " to " + uwMap.buildingsName.get(end) + ":");
			for (int i = 1; i < path.size(); i++) {
				double length = uwMap.graph
						.getAllLabels(path.get(i), path.get(i - 1)).iterator()
						.next();
				totalDistance += length;
				String direction = uwMap.getDirection(path.get(i - 1),
						path.get(i));
				System.out.println("\tWalk " + (int) Math.round(length)
						+ " feet " + direction + " to ("
						+ (int) Math.round(path.get(i).x) + ", "
						+ (int) Math.round(path.get(i).y) + ")");
			}
			System.out.println("Total distance: "
					+ (int) Math.round(totalDistance) + " feet ");
		}
	}
	/**
	 * Helper method used to print menu of option can be processed and their
	 * functions
	 */
	private static void printMenu() {
		System.out.println("Menu:");
		System.out.println("	r to find a route");
		System.out.println("	b to see a list of all buildings");
		System.out.println("	q to quit");
	}
}
