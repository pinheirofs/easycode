package com.s2.easycode.mainframe;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrameSwing extends JFrame implements MainFrame {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private static final long serialVersionUID = -96244502747449961L;

    public MainFrameSwing(final MainFrameCtrl mainFrameCtrl) {
        config();
        buildLayout();
    }

    private void buildLayout() {
        final JPanel panel = new JPanel();
        final GroupLayout layout = new GroupLayout(panel);

    }

    private void config() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

    @Override
    public void showFrame() {
        pack();
        setVisible(true);
    }

}
