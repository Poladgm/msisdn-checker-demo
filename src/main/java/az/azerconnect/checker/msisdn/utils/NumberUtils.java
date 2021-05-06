package az.azerconnect.checker.msisdn.utils;

public class NumberUtils{

    private NumberUtils(){
    }

    public static boolean isNumeric(String str){
        return str.chars().allMatch(Character::isDigit);
    }

    public static boolean isNumericWithSpecificSize(String str,int requiredSize){
        if (!isNumeric(str)){
            return false;
        } else{
            return str.trim().length() == requiredSize;
        }
    }
}
