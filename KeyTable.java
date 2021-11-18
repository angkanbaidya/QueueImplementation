//ANGKAN BAIDYA
//112309655
//R01

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the key to a Playfair Cipher
 *
 */
public class KeyTable {

    /**
     * Define the key of the cipher in a matrix format and initializes it
     */
    private char[][] key = new char[5][5];

    /**
     * Define the list of the alphabet characters without the letter J
     */
    private List<Character> alphabet = new LinkedList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

    /**
     * Constructor of the Class
     */
    public KeyTable() {
        super();
    }

    /**
     * Builds a new KeyTable object from the provided key and returns it
     * returns new KeyTable with the key corresponding to the string in input
     * Throws IllegalArgumentException if keyphrase is null
     */
    public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException {
        if (keyphrase == null) {

            throw new IllegalArgumentException("The key phrase is null");
        } else {

            KeyTable keyTable = new KeyTable();

            List<Character> keyAlphabetList = new ArrayList<Character>();


            keyphrase = keyphrase.toUpperCase();


            for (Character c : keyphrase.toCharArray()) {

                c = (c != 'J') ? c : 'I';


                if (keyTable.alphabet.contains(c) && !keyAlphabetList.contains(c)) {


                    keyAlphabetList.add(c);


                    keyTable.alphabet.remove(c);
                }
            }


            int listSize = keyAlphabetList.size();


            if (!keyAlphabetList.isEmpty()) {

                Character[] alphabetArray = new Character[listSize];
                keyAlphabetList.toArray(alphabetArray);


                for (int i = 0; i < listSize; i++) {

                    keyTable.key[i / 5][i % 5] = alphabetArray[i];
                }
            }


            for (int j = 0; j < 25 - listSize; j++) {

                keyTable.key[(j + listSize) / 5][(j + listSize) % 5] = keyTable.alphabet.get(j);
            }


            return keyTable;
        }

    }

    /**
     * This method returns the current key matrix.
     **/
    public char[][] getKeyTable() {
        return this.key;
    }

    /**
     * This method returns the current throw that the character c is in. If it is not found then it throws an
     * illegal argument exception.
     **
     */
    public int findRow(char c) throws IllegalArgumentException {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                if (key[i][j] == c) {

                    return i;
                }
            }
        }



        throw new IllegalArgumentException("The character '" + c + "' is not a valid letter in the key matrix");
    }

    /**
     * Returns the column number of a given character. If it is not found then it throws an illegal argument exception.
     *
     *
     */
    public int findCol(char c) throws IllegalArgumentException {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                if (key[i][j] == c) {

                    return j;
                }
            }
        }


        throw new IllegalArgumentException("The character is not a valid letter in the key matrix");
    }

}


