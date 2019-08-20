package com.juanlondono.androidxmlexport;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.util.ImageLoader;
import com.juanlondono.app.TranslationType;
import com.juanlondono.app.XMLToCSVKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class GUI extends DialogWrapper {
    private JPanel rootPanel;
    private JTabbedPane tabbedPanel;
    private JPanel xmlToCvsPanel;
    private JLabel lblAndroidResources;
    private JTextField txtSourceFolder;
    private JTextField txtDestinationFolder;
    private JButton btnSourceFolder;
    private JButton btnDestinationFolder;
    private JButton btnAceptar;
    private JLabel lblResult;
    private JLabel imageLabel;

    GUI(Project project, String initialFolder) {
        super(project, true);
        this.setTitle("Android Generator Cvs From Xml");
        this.setResizable(false);
        lblResult.setText("");

        txtSourceFolder.setText(initialFolder);
        btnSourceFolder.addActionListener(e -> {
            String folder = chooseFolder();
            if (folder != null)
                txtSourceFolder.setText(folder);
        });
        btnDestinationFolder.addActionListener(e -> {
            String folder = chooseFolder();
            if (folder != null) {
                txtDestinationFolder.setText(folder);
            }
        });
        btnAceptar.addActionListener(e -> {
            String sourceFolder = txtSourceFolder.getText();
            String destinationFolder = txtDestinationFolder.getText();
            writeCSV(sourceFolder, destinationFolder);
        });
        this.setOKActionEnabled(false);
        this.init();

        try {
            Image image = new ImageIcon(getClass().getResource("/loading.gif")).getImage().getScaledInstance(250, 20, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
            imageLabel.setVisible(false);
        } catch (Exception ex) {
            // todo show error
        }
    }

    private String chooseFolder() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = jFileChooser.showOpenDialog(getContentPane());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    private void writeCSV(final String sourceFolder, final String destinationFolder) throws ArrayIndexOutOfBoundsException {
        lblResult.setText("");
        imageLabel.setVisible(true);

        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    XMLToCSVKt.processXMLToCSV(sourceFolder, destinationFolder, TranslationType.NORMAL);
                    Thread.sleep(1000);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imageLabel.setVisible(false);
                            lblResult.setText("");
                            lblResult.setText("SUCCESS!");
                            lblResult.setForeground(JBColor.GREEN);
                        }
                    });
                } catch (Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imageLabel.setVisible(false);
                            lblResult.setText("Error Message: " + e.getMessage());
                            lblResult.setForeground(JBColor.RED);
                        }
                    });
                }
                return null;
            }

        };
        worker.execute();
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return this.rootPanel;
    }

    @NotNull
    protected Action[] createActions() {
        return new Action[0];
    }
}
