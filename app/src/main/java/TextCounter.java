public class TextCounter {
    public static int getCharsCount(String phrase){
        return phrase.length();
    }

    public static int getWordsCount(String phrase){
        return phrase.split("\\s+").length;
    }
}
