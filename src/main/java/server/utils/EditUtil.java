package server.utils;

public class EditUtil {
    private static final char maxLetter = 0x7a; // Максимальное значение, которое допустимо при вводе слова (код таблицы ASCII)
    private static final char minLetter = 0x41; // Минимальное значение, которое допустимо при вводе слова (код таблицы ASCII)


    public static Long keyParser(String word, Environment environment) throws WrongIdException, NumberFormatException {
        Long key = Long.parseLong(word);

        if(environment.getCollectionManager().findByKey(key)==null){
            throw new WrongIdException();
        }
        return key;
    }


    public static String nameParser(String name) throws WrongNameException {
        char[] chars = new char[name.length()];

        for (int i = 0; i <chars.length ; i++) {
            chars[i] = name.charAt(i);
            if(((chars[i]< minLetter) && (chars[i] > maxLetter) && (chars[i]!=' ')) ){
                throw new WrongNameException();
            }
        }
        return name;
    }


    public static boolean boolParser(String word) throws WrongHeroException {
        boolean answer;
        word = word.trim();
        if(word.equals("yes")){
            return true;
        }
        if(word.equals("no")){
            return false;
        }
        throw new WrongHeroException();
    }

}

