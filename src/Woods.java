/*
Woods.java
Andrew Schutzbach
schutz391@gmail.com
02/03/20
Two people are lost in the woods trying to find each other.
 */

import java.util.Scanner;
import java.util.Random;
public class Woods {
    public static void main(String[] arg) {
        //Variables
        int patX = 0;
        int patY = 0;
        int chrisX = 0;
        int chrisY = 0;
        int numberOfMoves = 0;
        boolean testA = false;
        boolean testB = false;
        String welcomeText = "Hi! In this simulation two people Pat and Chris are lost in the woods. \nYou will enter" +
                " how large the forest is! Then we will see if they can find each other!\n";
        String askA = "How tall would you like the forest? \nLet's keep the number between 2 and 50!\n";
        String askB = "How wide would you like the forest? \nLet's keep the number between 2 and 50!\n";
        String wrongAmount = "Please enter a number between 2 and 50!\n";
        int userA;
        int userB;
        int count = 0;


        //Welcomes the player and asks them how big of a forest they want to make
        Scanner input = new Scanner(System.in);
        System.out.print(welcomeText);

        //Test to see if the area is a size between 2 and 50 for height
        System.out.print(askA);
        do {
            userA = input.nextInt();
            System.out.println();
            if (userA >= 2 && userA <= 50) {
                testA = true;
            } else {
                System.out.print(wrongAmount);
            }

        } while (testA == false);


        //Test to see if the area is a size between 2 and 50 for width
        System.out.print(askB);
        do {
            userB = input.nextInt();
            System.out.println();
            if (userB >= 2 && userB <= 50) {
                testB = true;
            } else {
                System.out.print(wrongAmount);
            }

        } while (testB == false);

//       do {

        //Creates new map
        char[][] newMap = createMap(userA, userB);

        //Sets Pat and Chris' positions at the beginning of the simulation
        setPat(newMap, userA, userB);
        setChris(newMap, userA, userB);

        System.out.println();

        //Gets Pat's exact position
        patY = getPatY(newMap, userA, userB);
        patX = getPatX(newMap, userA, userB);

        //Gets Chris' exact position
        chrisY = getChrisY(newMap, userA, userB);
        chrisX = getChrisX(newMap, userA, userB);

        displayMap(newMap);
        while (count < 1000000) {
            System.out.println();
            //Moves both Pat and Chris
            newMap = move(newMap, patX, patY, chrisX, chrisY, userA, userB);
            count = count + 1;
            //Gets Pat's new position
            patY = getPatY(newMap, userA, userB);
            patX = getPatX(newMap, userA, userB);
            //Gets Chris' new position
            chrisY = getChrisY(newMap, userA, userB);
            chrisX = getChrisX(newMap, userA, userB);
            //Checks to see if the two have met
            if (newMap[0][0] == '-') {
                numberOfMoves = count;
                count = 1000000;
                System.out.println();
                System.out.print("It took " + numberOfMoves + " moves for Pat and Chris to find each other!");
            } else if (count == 1000000) {
                System.out.print("Pat and Chris never found each other... ");
            }
        }

//           count = count + 1;
//       } while (count != 20);


    }

    //Creates the map of the forest
    private static char[][] createMap(int a, int b) {
        char[][] newForest = new char[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                newForest[i][j] = '#';
            }
        }
        return newForest;
    }

    //Sets Pat on the map
    private static char[][] setPat(char[][] map, int a, int b) {
        map[a-1][0] = '>';
        return map;
    }

    //Sets Chris on the map
    private static char[][] setChris(char[][] map, int a, int b) {
        map[0][b-1] = '<';
        return map;
    }

    //Displays map
    private static void displayMap(char[][] map) {
        for (int i = map.length - 1; i >= 0; i--) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //Moves both Chris and Pat
    private static char[][] move(char[][] map, int patPosX, int patPosY, int chrisPosX, int chrisPosY, int a, int b) {
        int randomOne = (int)(7 * Math.random());
        int randomTwo = (int)(7 * Math.random());
        int newPatX, newPatY, newChrisX, newChrisY;
        newPatX = patPosX;
        newPatY = patPosY;
        newChrisX = 0;
        newChrisY = 0;
        a = a - 1;
        b = b - 1;

        boolean reroll = false;

        //Makes sure Pat moves
        do {
            //Moves Pat
            switch (randomOne) {
                //North
                case 0:
                    if (patPosX != a) {
                        reroll = false;
                        newPatX = patPosX + 1;
                        map[newPatX][patPosY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //South
                case 1:
                    if (patPosX != 0) {
                        reroll = false;
                        newPatX = patPosX - 1;
                        map[newPatX][patPosY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //East
                case 2:
                    if (patPosY != b) {
                        reroll = false;
                        newPatY = patPosY + 1;
                        map[patPosX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //West
                case 3:
                    if (patPosY != 0) {
                        reroll = false;
                        newPatY = patPosY - 1;
                        map[patPosX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //Northeast
                case 4:
                    if (patPosX != a && patPosY != b) {
                        reroll = false;
                        newPatX = patPosX + 1;
                        newPatY = patPosY + 1;
                        map[newPatX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //Northwest
                case 5:
                    if (patPosX != a && patPosY != 0) {
                        reroll = false;
                        newPatX = patPosX + 1;
                        newPatY = patPosY - 1;
                        map[newPatX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //Southeast
                case 6:
                    if (patPosX != 0 && patPosY != b) {
                        reroll = false;
                        newPatX = patPosX - 1;
                        newPatY = patPosY + 1;
                        map[newPatX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                //Southwest
                case 7:
                    if (patPosX != 0 && patPosY != 0) {
                        reroll = false;
                        newPatX = patPosX - 1;
                        newPatY = patPosY - 1;
                        map[newPatX][newPatY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                default:
            }
            //Rolls the random number again in case Pat doesn't move
            randomOne = (int)(7 * Math.random());
        } while (reroll == true);

        //Changes Chris' old space to forest
        map[patPosX][patPosY] = '#';

        //Finds Pat's new position
        newPatX = getPatX(map, a + 1, b + 1);
        newPatY = getPatY(map, a + 1, b + 1);


        if (newPatX == chrisPosX && newPatY == chrisPosY) {
            System.out.print("They found each other!\n");
            map[0][0] = '-';
        }


        //Makes sure Chris moves
        do {
            //Moves Chris
            switch (randomTwo) {
                //North
                case 0:
                    if (chrisPosX != a) {
                        reroll = false;
                        newChrisX = chrisPosX + 1;
                        map[newChrisX][chrisPosY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //South
                case 1:
                    if (chrisPosX != 0) {
                        reroll = false;
                        newChrisX = chrisPosX - 1;
                        map[newChrisX][chrisPosY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //East
                case 2:
                    if (chrisPosY != b) {
                        reroll = false;
                        newChrisY = chrisPosY + 1;
                        map[chrisPosX][newChrisY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //West
                case 3:
                    if (chrisPosY != 0) {
                        reroll = false;
                        newChrisY = chrisPosY - 1;
                        map[chrisPosX][newChrisY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //Northeast
                case 4:
                    if (chrisPosX != a && chrisPosY != b) {
                        reroll = false;
                        newChrisX = chrisPosX + 1;
                        newChrisY = chrisPosY + 1;
                        map[newChrisX][newChrisY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //Northwest
                case 5:
                    if (chrisPosX != a && chrisPosY != 0) {
                        reroll = false;
                        newChrisX = chrisPosX + 1;
                        newChrisY = chrisPosY - 1;
                        map[newChrisX][newChrisY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //Southeast
                case 6:
                    if (chrisPosX != 0 && chrisPosY != b) {
                        reroll = false;
                        newChrisX = chrisPosX - 1;
                        newChrisY = chrisPosY + 1;
                        map[newChrisX][newChrisY] = '<';
                    } else {
                        reroll = true;
                    }
                    break;
                //Southwest
                case 7:
                    if (chrisPosX != 0 && chrisPosY != 0) {
                        reroll = false;
                        newChrisX = chrisPosX - 1;
                        newChrisY = chrisPosY - 1;
                        map[newChrisX][newChrisY] = '>';
                    } else {
                        reroll = true;
                    }
                    break;
                default:
            }
            //Rolls the random number again in case Chris doesn't move
            randomTwo = (int)(7 * Math.random());
        } while (reroll == true);

        //Changes Chris' old space to forest
        map[chrisPosX][chrisPosY] = '#';

        //Finds Chris' new position
        newChrisX = getChrisX(map, a + 1, b + 1);
        newChrisY = getChrisY(map, a + 1, b + 1);




        //Checks to see if Pat and Chris have found each other
        if (newChrisX == newPatX && newChrisY == newPatY) {
            System.out.print("They found each other!\n");
            map[0][0] = '-';
        }
        displayMap(map);

        return map;
    }

    //Gets Chris' Y position
    private static int getChrisY(char[][] map, int a, int b) {
        int chrisPosY = 0;
        for (int x = 0; x < a; x++) {
            for (int y = 0; y < b; y++) {
                if (map[x][y] == '<') {
                    chrisPosY = y;
                }
            }
        }
        return chrisPosY;
    }

    //Gets Chris' X position
    private static int getChrisX(char[][] map, int a, int b) {
        int chrisPosX = 0;
        for (int x = 0; x < a; x++) {
            for (int y = 0; y < b; y++) {
                if (map[x][y] == '<') {
                    chrisPosX = x;
                }
            }
        }
        return chrisPosX;
    }

    //Gets Pat's Y position
    private static int getPatY(char[][] map, int a, int b) {
        int patPosY = 0;
        for (int x = 0; x < a; x++) {
            for (int y = 0; y < b; y++) {
                if (map[x][y] == '>') {
                    patPosY = y;
                }
            }
        }
        return patPosY;
    }

    //Gets Pat's X position
    private static int getPatX(char[][] map, int a, int b) {
        int patPosX = 0;
        for (int x = 0; x < a; x++) {
            for (int y = 0; y < b; y++) {
                if (map[x][y] == '>') {
                    patPosX = x;
                }
            }
        }
        return patPosX;
    }
}
