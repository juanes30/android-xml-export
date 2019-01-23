package com.juanlondono.androidxmlexport;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class XmlToCvs extends AnAction {
    public void actionPerformed(@NotNull AnActionEvent event) {
        final Project project = DataKeys.PROJECT.getData(event.getDataContext());
        if (project == null) {
            return;
        }
        final String initialFolder = project.getBasePath();
        GUI gui = new GUI(project, initialFolder);
        gui.show();
    }
}