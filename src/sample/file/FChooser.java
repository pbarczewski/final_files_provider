package sample.file;


import javax.swing.*;

public abstract class FChooser extends JFileChooser implements Runnable {

    public FChooser() {
        super();
        this.setFileSelectionMode(DIRECTORIES_ONLY);
        this.setApproveButtonText("Select");
    }

    public void setMultiSelecton(Boolean type) {
        this.setMultiSelectionEnabled(type);
    }

}
