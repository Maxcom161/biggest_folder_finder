import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args) {

        for(int i = 0; i < args.length; i++){
            System.out.println(i + " => " + args[i]);
        }

        ParametersBag bag = new ParametersBag(args);
//        String folderPath = bag.getPath();
//        long sizeLimit = bag.getLimit();

        System.exit(0);

        String folderPath = "C:/Users/Admin/Desktop/ГЕБ";
        long sizeLimit = 1 * 1024 * 1024;
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();
        ValueSumCalculator calculator =
                new ValueSumCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);

        System.out.println(root);
        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms ");

        System.out.println(System.getProperties().get("user.dir"));//todo ПОЛЕЗНАЯ КОМАНДА ДЛЯ ПОИСКА ПУТИ ПРОЕКТА
        //    private static long getFolderSize(File folder){ //todo так происходит расчет файлов в дереве папок.
//        if (folder.isFile()) {
//            return folder.length();
//        }
//        long sum = 0;
//        File[] files = folder.listFiles();
//        for(File file : files) {
//            sum += getFolderSize(file);
//        }
//        return sum;
//    }
//        System.out.println(getFolderSize(file)); //size in bytes


//        TestThread testThread = new TestThread(1);
//        TestThread testThread2 = new TestThread(2);
//
//        testThread.start();
//        testThread2.start();


//        System.out.println(SizeCalculator.getHumanReadableSize(root.getSize()));
//        System.out.println(SizeCalculator.getSizeFromHumanReadable("4Mb"));
    }



}