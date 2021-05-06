package az.azerconnect.checker.msisdn.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonUtils{
    private CommonUtils(){
    }

    public static List<String> convertStringToMaskList(String list){
        return list != null && !list.trim().isEmpty() ? new ArrayList<>(Arrays.asList(list.split(","))) : Collections.emptyList();
    }
}
