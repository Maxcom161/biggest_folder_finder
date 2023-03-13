import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args) {
        String folderPath = "C:/Users/Admin/Desktop/DCIM";
        String folderPath2 = "C:/Users/Admin/Desktop/DCIM/100APPLE";
        File file = new File(folderPath);
        File file2 = new File(folderPath2);
        System.out.println(file.length()); //длина папки в которой еще есть папки обозначается 0
        System.out.println("Эта папка - файл?" + " - " + file.isFile());
        System.out.println(file2.length()); // длина папки в которой нет больше папок
        System.out.println("Эта папка - файл?" + " - " + file2.isFile());

        System.out.println(System.getProperties().get("user.dir"));

        long start = System.currentTimeMillis();
        System.out.println(getFolderSize(file));
        long end = (System.currentTimeMillis() - start);
        System.out.println(end + " ms");

//        TestThread testThread = new TestThread(1);
//        TestThread testThread2 = new TestThread(2);
//
//        testThread.start();
//        testThread2.start();
        long start2 = System.currentTimeMillis();
        ValueSumCalculator calculator = new ValueSumCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);

        long end2 = (System.currentTimeMillis() - start2);
        System.out.println(end2 + " ms " + " size - " + size);

        System.out.println(getHumanReadableSize(size));

    }


    public static long getFolderSize(File folder){
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files) {
            System.out.println(file);
            sum += getFolderSize(file);
        }
        return sum;
    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static String getHumanReadableSize(long size){

        long sizeB = size;
        long sizeKb = 0;
        long sizeMb = 0;
        long sizeGb = 0;
        long sizeTb = 0;
        for (int i = 0; i < 5; i++) {
            if (size > 0) {
                size /= 1024;
                if (size > 0) {
                    sizeKb = size;
                    size /= 1024;
                    if (size > 0) {
                        sizeMb = size;
                        size /= 1024;
                        if (size > 0) {
                            sizeGb = size;
                            size /= 1024;
                            if (size > 0) {
                                sizeTb = size;
                                size /= 1024;
                            }
                        }
                    }
                }
            } else {
                break;
            }
        }
        return sizeB + "B" + "\n"
                + sizeKb + "Kb" + "\n"
                + sizeMb + "Mb" + "\n"
                + sizeGb + "Gb" + "\n"
                + sizeTb + "Tb" + "\n";
    }


    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    // 24B, 234K, 36M, 34G, 42T
    // 235K => 240640 (1024 * 235)

    public long getSizeFromHumanReadable(String size){

        return 0;
    }
}