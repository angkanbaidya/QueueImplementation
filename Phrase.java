//ANGKAN BAIDYA
//112309655
//R01



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for a queue, it extends the LinkedList class which implements the
 * Queue
 */
public class Phrase extends LinkedList<Bigram> implements Queue {

    /**
     * Array that contains the alphabet
     */
    private final static List<Character> ALPHABET = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    /**
     * Builds and returns a new Phrase object, which is a queue containing bigrams that represents the input message
     *
     */
    public static Phrase buildPhraseFromStringforEnc(String s) {


        String filtredInput = new String();

        for (char c : s.toUpperCase().toCharArray()) {


            if (ALPHABET.contains(c)) {

                filtredInput += (c == 'J') ? 'I' : c;
            }
        }


        Phrase phrase = new Phrase();


        for (int i = 0; i < filtredInput.length(); i++) {

            char current = filtredInput.charAt(i);


            if (filtredInput.length() > i + 1) {


                char next = filtredInput.charAt(i + 1);


                if (current == next) {

                    Bigram bigramX = new Bigram(current, 'X');


                    phrase.enqueue(bigramX);
                } else {

                    Bigram bigram = new Bigram(current, next);


                    phrase.enqueue(bigram);

                    i++;
                }
            } else {

                Bigram bigramX = new Bigram(current, 'X');


                phrase.enqueue(bigramX);
            }
        }

        return phrase;
    }

    /**
     * Encrypts the current phrase using the keyTable in input. Throws an illegal argument exception if the key is null.
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException {

        if (key == null) {
            throw new IllegalArgumentException("The encryption key is null");
        } else {
            Phrase encryptedPhrase = new Phrase();

            while (!this.isEmpty()) {
                Bigram bigramToEncrypt = this.dequeue();

                int firstRow = key.findRow(bigramToEncrypt.getFirst());
                int firstCol = key.findCol(bigramToEncrypt.getFirst());

                int secondRow = key.findRow(bigramToEncrypt.getSecond());
                int secondCol = key.findCol(bigramToEncrypt.getSecond());

                Bigram bigramEncrypted = null;

                if (firstRow == secondRow) {
                    char firstEncrypted = key.getKeyTable()[firstRow][(firstCol + 1) % 5];
                    char secondEncrypted = key.getKeyTable()[secondRow][(secondCol + 1) % 5];


                    bigramEncrypted = new Bigram(firstEncrypted, secondEncrypted);

                }

                else if (firstCol == secondCol) {

                    char firstEncrypted = key.getKeyTable()[(firstRow + 1) % 5][firstCol];
                    char secondEncrypted = key.getKeyTable()[(secondRow + 1) % 5][secondCol];

                    bigramEncrypted = new Bigram(firstEncrypted, secondEncrypted);
                }

                else {

                    char firstEncrypted = key.getKeyTable()[firstRow][secondCol];
                    char secondEncrypted = key.getKeyTable()[secondRow][firstCol];


                    bigramEncrypted = new Bigram(firstEncrypted, secondEncrypted);
                }


                encryptedPhrase.enqueue(bigramEncrypted);

            }


            return encryptedPhrase;
        }
    }

    /**
     * Decrypt the current phrase using the keyTable in input. It also throws an illegal argument exception if the key
     * is null.
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException {

        if (key == null) {

            throw new IllegalArgumentException("The decryption key is null");
        } else {

            Phrase decryptedPhrase = new Phrase();


            while (!this.isEmpty()) {
                Bigram bigramToDecrypt = this.dequeue();

                int firstRow = key.findRow(bigramToDecrypt.getFirst());
                int firstCol = key.findCol(bigramToDecrypt.getFirst());

                int secondRow = key.findRow(bigramToDecrypt.getSecond());
                int secondCol = key.findCol(bigramToDecrypt.getSecond());

                Bigram bigramDecrypted = null;

                if (firstRow == secondRow) {

                    char firstDecrypted = key.getKeyTable()[firstRow][(firstCol + 4) % 5];
                    char secondDecrypted = key.getKeyTable()[secondRow][(secondCol + 4) % 5];

                    bigramDecrypted = new Bigram(firstDecrypted, secondDecrypted);

                }
                else if (firstCol == secondCol) {

                    char firstDecrypted = key.getKeyTable()[(firstRow + 4) % 5][firstCol];
                    char secondDecrypted = key.getKeyTable()[(secondRow + 4) % 5][secondCol];

                    bigramDecrypted = new Bigram(firstDecrypted, secondDecrypted);
                }

                else {

                    char firstDecrypted = key.getKeyTable()[firstRow][secondCol];
                    char secondDecrypted = key.getKeyTable()[secondRow][firstCol];


                    bigramDecrypted = new Bigram(firstDecrypted, secondDecrypted);
                }
                decryptedPhrase.enqueue(bigramDecrypted);

            }

            return decryptedPhrase;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i<this.size();i++ ){
            result +=  this.get(i).toString();
        }
        return result;
    }

    /**
     * This method calls the enqueue method of the linkedList
     */
    public void enqueue(Bigram b) {
        this.add(b);

    }

    /**
     * This method calls the dequeue method of the linkedList
     */
    public Bigram dequeue() {
        return this.poll();
    }

}







