package edu.wright.dase;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class OplaUI extends JPanel
{
	/** Bookkeeping */
	private static final long	serialVersionUID	= 1L;


	/** Panels! */
	private JPanel				entityPanel;
	private JPanel				editorPanel;
	/** The Controller */
	private OplaController oplaController;
	
	public OplaUI(OplaController oplaController)
	{
		// Save a reference to the controller
		this.oplaController = oplaController;
		
		// Populate the panels
		createEntityPanel();
		createEditorPanel();

		// Construct top level panel
		setLayout(new BorderLayout());
		this.add(this.entityPanel, BorderLayout.NORTH);
		this.add(this.editorPanel, BorderLayout.CENTER);
	}

	private void createEntityPanel()
	{
		// Create the buttons
		JRadioButton classes = new JRadioButton("Classes");
		JRadioButton individuals = new JRadioButton("Individuals");
		JRadioButton objProp = new JRadioButton("Object Properties");
		JRadioButton dataProp = new JRadioButton("Data Properties");
		JRadioButton dataType = new JRadioButton("Data Types");
		JRadioButton annot = new JRadioButton("Annotations");
		JRadioButton classAx = new JRadioButton("Class Axioms");

		// TODO: set default selection
		// Add the buttons to a buttongroup
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(classes);
		buttons.add(individuals);
		buttons.add(objProp);
		buttons.add(dataProp);
		buttons.add(dataType);
		buttons.add(annot);
		buttons.add(classAx);

		// Create the panel for options
		this.entityPanel = new JPanel();
		this.entityPanel.setLayout(new FlowLayout());
		this.entityPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Add the buttons to the panel
		this.entityPanel.add(classes);
		this.entityPanel.add(individuals);
		this.entityPanel.add(objProp);
		this.entityPanel.add(dataProp);
		this.entityPanel.add(dataType);
		this.entityPanel.add(annot);
		this.entityPanel.add(classAx);

		// Create the ItemListeners for the buttons
		for(Enumeration<AbstractButton> e = buttons.getElements(); e.hasMoreElements();)
		{
			JRadioButton jrb = (JRadioButton) e.nextElement();
			jrb.addItemListener(new ItemListener()
			{
				@Override
				public void itemStateChanged(ItemEvent ie)
				{
					if(ie.getStateChange() == ItemEvent.SELECTED)
					{
						JOptionPane.showMessageDialog(null, ((JRadioButton) ie.getSource()).getText());
					}
				}
			});
		}
	}

	private void createEditorPanel()
	{
		// TODO: remove hardcoded list data and replace with default selection
		String[] classes = {"data"}; //this.oplaController.retireveClasses(oplaController.getModelManager());
		JList<String> entityList = new JList<String>(classes);
		JScrollPane scrollPane = new JScrollPane(entityList);
		scrollPane.setPreferredSize(new Dimension(100, 100));
		
		// Create the dropdown menu
		JComboBox<String> comboAnnotations = new JComboBox<String>();
		comboAnnotations.addItem("isNativeTo");
		comboAnnotations.addItem("ofExternalType");
		comboAnnotations.addItem("reusesPatternAsTemplate");
		comboAnnotations.addItem("specializationOfModule");
		comboAnnotations.addItem("generatlizationOfModule");
		comboAnnotations.addItem("derivedFromModule");
		comboAnnotations.addItem("hasRelatedModule");
		comboAnnotations.addItem("specializationOfPattern");
		comboAnnotations.addItem("generatlizationOfPattern");
		comboAnnotations.addItem("derivedFromPattern");
		comboAnnotations.addItem("hasRelatedPattern");
		
		// Create targetTextField
		JTextField targetTextField = new JTextField();
		targetTextField.setPreferredSize(new Dimension(100, 30));
		
		// Create the button for saving the annotation
		JButton saveButton = new JButton("Save");
		// TODO: add actionlistener for saving the annotation
		
		// Create the "editor" panel
		this.editorPanel = new JPanel();
		this.editorPanel.setLayout(new FlowLayout());
		this.editorPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Add everything to the panel
		this.editorPanel.add(scrollPane);
		this.editorPanel.add(comboAnnotations);
		this.editorPanel.add(targetTextField);
		this.editorPanel.add(saveButton);
	}
}
