import java.util.ArrayList;
import java.util.HashMap;

public class EvilSolution {

    private String keyBest = "";
    private ArrayList<String> candidateList;
    private HashMap<String, ArrayList<String>> candidateMap;
    private ArrayList<Character> partialSolution;
    private int missingChars;

    public EvilSolution(ArrayList<String> wordList) {
        this.candidateList = wordList;
        missingChars = wordList.get(0).length();
        partialSolution = new ArrayList<>(missingChars);
        for (int i = 0; i < missingChars; i++) {
            partialSolution.add('_');
            this.keyBest = keyBest + "-";
        }
    }

    public boolean isSolved() {
        return missingChars == 0;
    }

    public void printProgress() {
        for (char c : partialSolution) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public boolean addGuess(char guess) {
        // step1 build hashmap for guess
        buildMap(guess);

        // step2 update the best word structure with the most options
        updateAll();

        // check: for each guess => updated hashmap and candidate list
//        System.out.println("guess Char: [" + guess + "]");
//        check();
//        System.out.println("Best Key: [" + keyBest + "]");
//        System.out.println("candidate list: " + candidateList);
//        System.out.println();

        // step3 update the demonstration part
        boolean guessCorrect = false;
        for (int i = 0; i < keyBest.length(); i++) {
            if (keyBest.charAt(i) == guess) {
                partialSolution.set(i, guess);
                missingChars--;
                guessCorrect = true;
            }
        }
        return guessCorrect;
    }

    public String getTarget() {
        return candidateList.get(0);
    }

    // get the best key: use in test file
    public String getBestKey(){
        return keyBest;
    }
    // ===== Build A HashMap w/t Given Guess =====

    // use the user guessed char to form a HashMap (ex. "-e--" ---> ["heal", "belt"])
    private void buildMap(char guess){
        this.candidateMap = new HashMap<>();
        // assign each word candidates to the HashMap
        for (String word: candidateList){
            // get the key of that word based on where the guessed char is located in that word
            // and merge it with previous key
            String wordKey = mergeKey(getKey(word, guess));
            if (candidateMap.containsKey(wordKey)){
                candidateMap.get(wordKey).add(word);
            } else {
                candidateMap.put(wordKey, new ArrayList<>());
                candidateMap.get(wordKey).add(word);
            }
        }
    }

    // (HELPER) input: word and char => output: string key (ex. "echo" and "e" => "e---")
    private String getKey(String word, char guess){
        String wordKey = "";
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == guess){
                wordKey = wordKey + guess;
            } else{
                wordKey = wordKey + '-';
            }
        }
        return wordKey;
    }

    // (HELPER) merge new key with previous key
    private String mergeKey(String keyNew){
        String keyMerge = "";
        for (int i = 0; i < keyBest.length(); i++){
            if (keyNew.charAt(i) != '-'){
                keyMerge = keyMerge + keyNew.charAt(i);
            } else {
                keyMerge = keyMerge + keyBest.charAt(i);
            }
        }
        return keyMerge;
    }

    // ===== Update the Candidate List After Guess =====

    // update the best key and set its arraylist as new candidate list
    private void updateAll(){
        String keyMax = "";
        int keyMaxLen = -1;
        for (String key: candidateMap.keySet()){
            int arrLen = candidateMap.get(key).size();
            if (keyMaxLen < arrLen) {
                keyMaxLen = arrLen;
                keyMax = key;
            } else if (keyMaxLen == arrLen){
                sameLenChoice(keyMax, key);
            }
        }
        this.keyBest = keyMax;
        this.candidateList = candidateMap.get(keyMax);
    }

    // if the ArrLists of two keys have the same size => choose the key that has more "-" unknown part
    private String sameLenChoice(String key1, String key2){
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < key1.length(); i++){
            if (key1.charAt(i) == '-'){
                count1++;
            }
            if (key2.charAt(i) == '-'){
                count2++;
            }
        }
        if (count1 >= count2){
            return key1;
        } else {
            return key2;
        }
    }

    // ===== CHECK =====
//    private void check(){
//        System.out.println("<Contents of the HashMap>:");
//        for (String key : candidateMap.keySet()) {
//            System.out.println("Key: [" + key + "] => " + candidateMap.get(key));
//        }
//    }

}
