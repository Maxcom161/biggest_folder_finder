import java.io.File;

public class SizeCalculator {


    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static String getHumanReadableSize(long size){
        int y = 0;
        for (int i = 4; i >= 0; i--){
            long pow = (long) Math.pow(1024, i);
            if (size > 0 && size >= pow) {
                y = i;
                break;
            }
        }
        switch (y) {
            case 0: return size + "B";
            case 1: return Math.round(size/((long) Math.pow(1024, y))) + "Kb";
            case 2: return Math.round(size/((long) Math.pow(1024, y))) + "Mb";
            case 3: return Math.round(size/((long) Math.pow(1024, y))) + "Gb";
            case 4: return Math.round(size/((long) Math.pow(1024, y))) + "Tb";
        }


//
//        if (size > 0 && size < 1024) {
//            return size + "B";
//        } else {
//            size /= 1024;
//        }
//        if (size > 0 && size < 1024) {
//            return size + "Kb";
//        } else {
//            size /= 1024;
//        }
//        if (size > 0 && size < 1024) {
//            return size + "Mb";
//        } else {
//            size /= 1024;
//        }
//        if (size > 0 && size < 1024) {
//            return size + "Gb";
//        } else {
//            size /= 1024;
//        }
//        if (size > 0 && size < 1024) {
//            return size + "Tb";
//        }
        return "";
    }




    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    // 24B, 234K, 36M, 34G, 42T
    // 235K => 240640 (1024 * 235)

    public static long getSizeFromHumanReadable(String size){
        long result = Long.valueOf(size.replaceAll("[^0-9]", ""));
        if (size.contains("B")){
            return Long.valueOf(result);
        }
        if (size.contains("K")){
            return Long.valueOf(result) * 1024;
        }
        if (size.contains("M")){
            return (long) (Long.valueOf(result) * Math.pow(1024, 2));
        }
        if (size.contains("G")){
            return (long) (Long.valueOf(result) * Math.pow(1024, 3));
        }
        if (size.contains("T")){
            return (long) (Long.valueOf(result) * Math.pow(1024, 4));
        }
        return 0;
    }
}
