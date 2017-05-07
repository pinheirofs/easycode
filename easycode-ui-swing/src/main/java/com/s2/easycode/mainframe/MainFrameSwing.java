package com.s2.easycode.mainframe;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.s2.easycode.Translate;

public class MainFrameSwing extends JFrame implements MainFrame {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private static final long serialVersionUID = -96244502747449961L;
    private JTextField projectTextField;
    private JTextField classTextField;
    private AttributeTableModel attributeTableMode;

    public MainFrameSwing(final MainFrameCtrl mainFrameCtrl) {
        config();
        buildLayout();
    }

    private void buildLayout() {

        final Translate translate = Translate.getInstance();
        final JLabel projectLabel = new JLabel(translate.tr("MainFrameSwing.project.label"));
        final JLabel classLabel = new JLabel(translate.tr("MainFrameSwing.class.label"));
        final JLabel attributeLabel = new JLabel(translate.tr("MainFrameSwing.attribute.label"));
        projectTextField = new JTextField();
        classTextField = new JTextField();
        attributeTableMode = new AttributeTableModel();
        final JTable atttributeTable = new JTable(attributeTableMode);
        final JScrollPane scrollPane = new JScrollPane(atttributeTable);
        final JButton addAttributeButton = new JButton(translate.tr("MainFrameSwing.attribute.button.add"));
        final JButton removeAttributeButton = new JButton(translate.tr("MainFrameSwing.attribute.button.remove"));
        final JButton createButton = new JButton(translate.tr("MainFrameSwing.create.button"));

        final Container contentPane = getContentPane();
        final GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup( //
                layout.createParallelGroup() //
                        .addGroup(layout.createSequentialGroup() //
                                .addGroup(layout.createParallelGroup() //
                                        .addComponent(projectLabel) //
                                        .addComponent(classLabel) //
                                        .addComponent(attributeLabel)) //
                                .addGroup(layout.createParallelGroup() //
                                        .addComponent(projectTextField) //
                                        .addComponent(classTextField))) //
                        .addGroup(layout.createSequentialGroup() //
                                .addComponent(scrollPane) //
                                .addGroup(layout.createParallelGroup() //
                                        .addComponent(addAttributeButton) //
                                        .addComponent(removeAttributeButton))) //
                        .addComponent(createButton, Alignment.TRAILING) //

        );

        layout.setVerticalGroup( //
                layout.createSequentialGroup() //
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE) //
                                .addComponent(projectLabel) //
                                .addComponent(projectTextField)) //
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE) //
                                .addComponent(classLabel) //
                                .addComponent(classTextField)) //
                        .addComponent(attributeLabel) //
                        .addGroup(layout.createParallelGroup() //
                                .addComponent(scrollPane) //
                                .addGroup(layout.createSequentialGroup() //
                                        .addComponent(addAttributeButton) //
                                        .addComponent(removeAttributeButton))) //
                        .addComponent(createButton) //
        );

    }

    private void config() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        final Translate instance = Translate.getInstance();
        setTitle(instance.tr("MainFrameSwing.title"));
    }

    @Override
    public void showFrame() {
        pack();
        setVisible(true);
    }

    private class AttributeTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 2172476534961424981L;
        private final List<String[]> values = new ArrayList<>();
        private final String[] columnNames;

        AttributeTableModel() {
            final Translate translate = Translate.getInstance();
            columnNames = new String[] { //
                    translate.tr("MainFrameSwing.attribute.tablecolumn.name"), //
                    translate.tr("MainFrameSwing.attribute.tablecolumn.type") };
        }

        @Override
        public int getRowCount() {
            return values.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(final int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            final String[] line = values.get(rowIndex);
            return line[columnIndex];
        }

    }
}
