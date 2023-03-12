import java.io.File;

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

        TestThread testThread = new TestThread(1);
        TestThread testThread2 = new TestThread(2);

        testThread.start();
        testThread2.start();

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
}