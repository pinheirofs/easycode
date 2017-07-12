package com.s2.easycode.mainframe;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.s2.easycode.Translate;
import com.s2.easycode.sourcegenerator.AttributeType;

public class MainFrameSwing extends JFrame implements MainFrame {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private static final long serialVersionUID = -96244502747449961L;
    private JTextField projectNameTextField;
    private JTextField projectGroupTextField;
    private JTextField projectPathTextField;
    private JTextField classNameTextField;
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
        final JLabel projectNameLabel = new JLabel(translate.tr("MainFrameSwing.project.name.label"));
        final JLabel projectGroupLabel = new JLabel(translate.tr("MainFrameSwing.project.group.label"));
        final JLabel projectPathLabel = new JLabel(translate.tr("MainFrameSwing.path.label"));
        final JLabel classLabel = new JLabel(translate.tr("MainFrameSwing.class.label"));
        final JLabel attributeLabel = new JLabel(translate.tr("MainFrameSwing.attribute.label"));
        projectNameTextField = new JTextField();
        projectGroupTextField = new JTextField();
        projectPathTextField = new JTextField();
        classNameTextField = new JTextField();

        final JComboBox<String> typeComboBox = new JComboBox<>();
        for (final AttributeType attributeType : AttributeType.values()) {
            typeComboBox.addItem(attributeType.getJavaType());
        }
        attributeTableMode = new AttributeTableModel();
        atttributeTable = new JTable(attributeTableMode);
        atttributeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final TableColumn typeColumn = atttributeTable.getColumnModel().getColumn(1);
        typeColumn.setCellEditor(new DefaultCellEditor(typeComboBox));
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
                                        .addComponent(projectNameLabel) //
                                        .addComponent(projectGroupLabel) //
                                        .addComponent(projectPathLabel) //
                                        .addComponent(classLabel) //
                                        .addComponent(attributeLabel)) //
                                .addGroup(layout.createParallelGroup() //
                                        .addComponent(projectNameTextField) //
                                        .addComponent(projectGroupTextField) //
                                        .addComponent(projectPathTextField) //
                                        .addComponent(classNameTextField))) //
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
                                .addComponent(projectNameLabel) //
                                .addComponent(projectNameTextField)) //
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE) //
                                .addComponent(projectGroupLabel) //
                                .addComponent(projectGroupTextField)) //
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE) //
                                .addComponent(projectPathLabel) //
                                .addComponent(projectPathTextField)) //
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE) //
                                .addComponent(classLabel) //
                                .addComponent(classNameTextField)) //
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
            if (aValue == null) {
                return;
            }

            final String[] line = values.get(rowIndex);
            line[columnIndex] = aValue.toString();

            fireTableCellUpdated(rowIndex, columnIndex);
        }

        public List<String[]> getValues() {
            return new ArrayList<>(values);
        }
    }

    @Override
    public String getProjectName() {
        return projectNameTextField.getText();
    }

    @Override
    public String getProjectGroup() {
        return projectGroupTextField.getText();
    }

    @Override
    public String getEntityName() {
        return classNameTextField.getText();
    }

    @Override
    public List<String[]> getAttributes() {
        return attributeTableMode.getValues();
    }

    @Override
    public String getProjectPath() {
        return projectPathTextField.getText();
    }

    @Override
    public void showProjectNameErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.project.name");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showEntityClassNameErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.entity.class");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showEntityArttributeNameErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.entity.arrtibute.name");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showEntityArttributeTypeErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.entity.arrtibute.type");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showProjectPathErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.project.path");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showProjectGroupErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("MainFrameSwing.error.project.group");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showUndefineErrorMsg() {
        final Translate translate = Translate.getInstance();
        final String message = translate.tr("MainFrameSwing.error.title");
        final String title = translate.tr("");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
