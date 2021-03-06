package ilya.ignatov;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;
import ru.vsu.cs.util.ArrayUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class QueueGUI extends JFrame {
    private JTable jTable = new JTable();
    private JPanel jPanel = new JPanel();
    private JTextField value = new JTextField();
    private JButton loadFromFile = new JButton("Загрузить из файла");
    private JButton addElement = new JButton("Добавить элемент");
    private JButton deleteElement = new JButton("Удалить элемент ");
    private JButton deleteAll = new JButton("Удалить всю очередь");
    private JButton execute = new JButton("Выполнить программу");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel enterValue = new JLabel("Введите значение:");
    private JLabel label = new JLabel("Начальная очередь");


    public QueueGUI () {
        super("Дублирование очереди");
        Queue queue = new Queue();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.addChoosableFileFilter(filter);

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.clear();
                jTable.removeAll();
                label.setText("Начальная очередь");
            }
        });

        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.addElement(value.getText());
                JTableUtils.writeArrayToJTable(jTable, queue.toArray());
                value.setText("");
                label.setText("Начальная очередь");
            }
        });

        deleteElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.deleteElement();
                JTableUtils.writeArrayToJTable(jTable, queue.toArray());
                label.setText("Начальная очередь");
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.clear();
                jTable.removeAll();
                label.setText("Начальная очередь");
            }
        });

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.duplicate();
                JTableUtils.writeArrayToJTable(jTable, queue.toArray());
                label.setText("Конечная очередь");
            }
        });

        loadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    queue.clear();
                    jTable.removeAll();
                    if (fileChooser.showOpenDialog(jPanel) == JFileChooser.APPROVE_OPTION) {
                        String[] array = ArrayUtils.readLinesFromFile(fileChooser.getSelectedFile().getPath());
                        for (String s : array) {
                            queue.addElement(s);
                        }
                        JTableUtils.writeArrayToJTable(jTable, array);
                        label.setText("Начальная очередь");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        jPanel.setLayout(null);
        enterValue.setBounds(5, 5, 150, 20);
        value.setBounds(160, 5, 100, 20);
        label.setBounds(5, 35, 150, 20);
        jTable.setBounds(5, 65, 400, 20);
        loadFromFile.setBounds(5, 95, 200, 30);
        addElement.setBounds(5, 130 , 200, 30);
        deleteElement.setBounds(5, 165, 200, 30);
        deleteAll.setBounds(5, 200, 200, 30);
        execute.setBounds(5, 235, 200, 30);


        jPanel.add(enterValue);
        jPanel.add(value);
        jPanel.add(label);
        jPanel.add(jTable);
        jPanel.add(loadFromFile);
        jPanel.add(addElement);
        jPanel.add(deleteElement);
        jPanel.add(deleteAll);
        jPanel.add(execute);

        getContentPane().add(jPanel);
    }
    public static void main (String[] args){
        QueueGUI app = new QueueGUI();
        app.setBounds(100, 100, 500, 500);
        app.setVisible(true);
    }
}