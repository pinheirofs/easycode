package com.s2.easycode.mainframe;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.s2.easycode.Translate;

public class MainFrameSwing extends JFrame implements MainFrame {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private static final long serialVersionUID = -96244502747449961L;
    private JTextField projectTextField;
    private JTextField pathTextField;
    private JTextField classTextField;
    private AttributeTableModel attributeTableMode;
    private final MainFrameCtrl mainFrameCtrl;
    private JTable atttributeTable;

    public MainFrameSwing(final MainFrameCtrl mainFrameCtrl) {
        this.mainFrameCtrl = mainFrameCtrl;
        config();
        buildLayout();
    }

    private void buildLayout() {

        final Translate translate = Translate.getInstance();
        final JLabel projectLabel = new JLabel(translate.tr("MainFrameSwing.project.label"));
        final JLabel pathLabel = new JLabel(translate.tr("MainFrameSwing.path.label"));
        final JLabel classLabel = new JLabel(translate.tr("MainFrameSwing.class.label"));
        final JLabel attributeLabel = new JLabel(translate.tr("MainFrameSwing.attribute.label"));
        projectTextField = new JTextField();
        pathTextField = new JTextField();
        classTextField = new JTextField();
        attributeTableMode = new AttributeTableModel();
        atttributeTable = new JTable(attributeTableMode);
        atttributeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(atttributeTable);
        final JButton addAttributeButton = new JButton(translate.tr("MainFrameSwing.attribute.button.add"));
        addAttributeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                addNewAttributeLine();
            }
        });
        final JButton removeAttributeButton = new JButton(translate.tr("MainFrameSwing.attribute.button.remove"));
        removeAttributeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                removeAttributeLine();
            }
        });
        final JButton createButton = new JButton(translate.tr("MainFrameSwing.create.button"));
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                mainFrameCtrl.createEntity();
            }
        });

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
                                        .addComponent(pathLabel) //
                                        .addComponent(classLabel) //
                                        .addComponent(attributeLabel)) //
                                .addGroup(layout.createParallelGroup() //
                                        .addComponent(projectTextField) //
                                        .addComponent(pathTextField) //
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
                                .addComponent(pathLabel) //
                                .addComponent(pathTextField)) //
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

    protected void removeAttributeLine() {
        final int selectedRow = atttributeTable.getSelectedRow();
        attributeTableMode.removeLine(selectedRow);
    }

    protected void addNewAttributeLine() {
        attributeTableMode.addLine();
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

        public void addLine() {
            values.add(new String[2]);
            fireTableRowsInserted(0, values.size() - 1);

        }

        public void removeLine(final int rowIndex) {
            values.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
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

        @Override
        public boolean isCellEditable(final int rowIndex, final int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            final String[] line = values.get(rowIndex);
            line[columnIndex] = aValue.toString();

            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public String getProjectName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEntityName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String[]> getAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void showProjectNameErrorMsg() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showEntityClassNameErrorMsg() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showEntityArttributeErrorMsg() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getProjectPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void showProjectPathErrorMsg() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showUndefineErrorMsg() {
        // TODO Auto-generated method stub

    }
}
