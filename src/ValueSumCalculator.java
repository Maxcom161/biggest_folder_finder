import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ValueSumCalculator extends RecursiveTask<Long> {

    private Node node;

    public ValueSumCalculator(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        File folder = node.getFolder();
        if (folder.isFile()) {
            long length = folder.length();
            node.setSize(length); // присваиваем ноде размер
            return length;
        }
        long sum = 0;
        List<ValueSumCalculator> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();
        for(File file : files) {
            Node child = new Node(file, node.getLimit());
            ValueSumCalculator task = new ValueSumCalculator(child);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
            node.addChild(child);
        }

        for(ValueSumCalculator task : subTasks) {
            sum += task.join(); // wait for the result ждем и суммируем результат
        }
        node.setSize(sum);
        return sum;
    }
}