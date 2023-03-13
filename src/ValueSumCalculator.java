import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ValueSumCalculator extends RecursiveTask<Long> {

    private File folder;

    public ValueSumCalculator(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        List<ValueSumCalculator> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();
        for(File file : files) {
            ValueSumCalculator task = new ValueSumCalculator(file);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
        }

        for(ValueSumCalculator task : subTasks) {
            sum += task.join(); // wait for the result ждем и суммируем результат
        }

        return sum;
    }
}