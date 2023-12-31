/* Advent of Code, Day 2: Cube Conundrum
 * Adrien Abbey, Dec. 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class day2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Load the input file:
        File inputFile = new File("input.example");
        Scanner inputScanner = new Scanner(inputFile);

        // Setup variables:
        int maxRedCubes = 12;
        int maxGreenCubes = 13;
        int maxBlueCubes = 14;
        int totalSum = 0;

        // Start parsing the input:
        while (inputScanner.hasNextLine()) {
            // Grab the next line:
            String inputLine = inputScanner.nextLine();

            // Parse the game number:
            int gameNumber = gameNumParser(inputLine);

            // Start splitting the input into usable parts.
            // First, split at ':'. We only want what's after that:
            String[] firstSplit = inputLine.split(":");
            // Second, we need to split the different games apart:
            String[] roundResults = firstSplit[1].split(";");

            // For each game, we need to track the maximum number of each cube:
            int highestRedCount = 0;
            int highestGreenCount = 0;
            int highestBlueCount = 0;

            // For each game result:
            for (String roundString : roundResults) {
                // Split the game string into individual rolls:
                String[] rollResults = roundString.split(",");

                // For each roll count:
                for (String rollString : rollResults) {
                    // Split the rolls into parsable bits:
                    String[] cubeCounts = rollString.split(" ");

                    // We have a number and a color.
                    // Start updating the highest counts.

                    // If the cube is red:
                    if (cubeCounts[2].contains("red")) {
                        // If the count is higher than the previous max:
                        if (Integer.parseInt(cubeCounts[1]) > highestRedCount) {
                            // Update the new max:
                            highestRedCount = Integer.parseInt(cubeCounts[1]);
                        }
                    }
                    if (cubeCounts[2].contains("green")) {
                        // If the count is higher than the previous max:
                        if (Integer.parseInt(cubeCounts[1]) > highestGreenCount) {
                            // Update the new max:
                            highestGreenCount = Integer.parseInt(cubeCounts[1]);
                        }
                    }
                    if (cubeCounts[2].contains("blue")) {
                        // If the count is higher than the previous max:
                        if (Integer.parseInt(cubeCounts[1]) > highestBlueCount) {
                            // Update the new max:
                            highestBlueCount = Integer.parseInt(cubeCounts[1]);
                        }
                    }
                }
            }

            // FIXME:
            System.out.println(" The input string is: " + inputLine);
            System.out.println(" The highest counts are: " + highestRedCount + " red, " + highestGreenCount + " green, "
                    + highestBlueCount + " blue.");

            // If the highest count for each color is less than the max
            // permitted of each:
            if (highestRedCount <= maxRedCubes && highestGreenCount <= maxGreenCubes
                    && highestBlueCount <= maxBlueCubes) {
                // Then add this game number to the total sum:
                totalSum += gameNumber;
            }
        }

        // Print the result:
        System.out.println("The result for the given input is: " + totalSum);

        // Close the Scanner before exiting:
        inputScanner.close();
    }

    public static int gameNumParser(String inputString) {
        // Parses the given string, returning the game number.
        // Expects the input to be properly formatted.
        // Parse the string from the start, grabbing any integers, stopping once
        // a ':' is found.

        // Track each found integer:
        ArrayList<Integer> foundIntegers = new ArrayList<Integer>();

        // Start parsing characters in the string from the start:
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == ':') {
                break;
            }
            if (Character.isDigit(inputString.charAt(i))) {
                // Add the integer to the list:
                foundIntegers.add(Integer.valueOf(inputString.charAt(i)));
            }
        }

        // Create a string to hold the integers:
        String integerString = new String();

        // Append each integer to the integer string:
        for (int i = 0; i < foundIntegers.size(); i++) {
            integerString += foundIntegers.get(i) - 48;
        }

        // Convert the integer string into a proper integer:
        int gameNumber = Integer.valueOf(integerString);

        // FIXME:
        // System.out.println(" The input string is: " + inputString);
        // System.out.println(" The game number is: " + gameNumber);

        // Return that integer:
        return gameNumber;
    }
}