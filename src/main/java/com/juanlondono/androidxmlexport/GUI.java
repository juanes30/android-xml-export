package com.juanlondono.androidxmlexport;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.juanlondono.app.TranslationType;
import com.juanlondono.app.XMLToCSVKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


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


    GUI(Project project, String initialFolder) {
        super(project, true);
        this.setTitle("Android Generator Cvs From Xml");
        this.setResizable(true);
        lblResult.setText("");

        txtSourceFolder.setText(initialFolder);
        txtSourceFolder.setText("/Users/juanlondonotabares/Proyectos/StudioProjects/Soldi/app/src/main/res");
        txtDestinationFolder.setText("/Users/juanlondonotabares/Downloads");
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
        SwingUtilities.invokeLater(() -> {
            try {
                XMLToCSVKt.processXMLToCSV(sourceFolder, destinationFolder, TranslationType.NORMAL);
                lblResult.setText("");
                lblResult.setText("SUCCESS!");
                lblResult.setForeground(JBColor.GREEN);
            } catch (Exception e) {
                lblResult.setText("Error Message: " + e.getMessage());
                lblResult.setForeground(JBColor.RED);
            }

        });

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
