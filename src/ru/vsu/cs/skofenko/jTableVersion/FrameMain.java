package ru.vsu.cs.skofenko.jTableVersion;

import ru.vsu.cs.skofenko.CommonModule;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JTable table1;
    private JButton loadInputButton;
    private JButton writeInputButton;
    private JButton solveButton;
    private JButton saveButton;
    private JLabel answerLabel;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain(){
        this.setTitle("Task 8");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(table1, 40, true, true, true, true);
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserOpen.addChoosableFileFilter(filter);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        loadInputButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = CommonModule.readMatrixFromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(table1, arr);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        solveButton.addActionListener(e -> {
            try{
                answerLabel.setText(String.valueOf(CommonModule.isSequence(JTableUtils.readIntMatrixFromJTable(table1))));
            }
            catch (Exception exception){
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        saveButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    CommonModule.writeAnswerToFile(file, answerLabel.getText());
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        writeInputButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(table1);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    CommonModule.writeMatrixToFile(file, matrix);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}
