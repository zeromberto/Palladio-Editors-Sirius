package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.architecturaltemplates.Role;
import org.palladiosimulator.architecturaltemplates.ui.dialogs.AssemblyConnectorSelectionDialog;
import org.palladiosimulator.architecturaltemplates.ui.dialogs.RoleStereotypeSelectionDialog;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.system.System;

/**
 * 
 * 
 * @author Matthias Rombach
 *
 */

public class AddATRoleAssemblyConnectorAction implements IExternalJavaAction {
	/**
	 * Message displayed in the selection dialog.
	 */
	private static final String DIALOG_MESSAGE = "Select the assembly connector to be bound with a AT role.";

	/**
	 * Asks the user to select a {@link Role} and attaches it to the given {@link AssemblyContext}.
	 */
	@Override
	public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
		EObject selection = selections.iterator().next();

		// Treat the selection of assembly connectors
		Connector selectedConnector = null;
		EList<Connector> assemblyConnectors = null;
		try {
			assemblyConnectors = ((System) selection).getConnectors__ComposedStructure();
		} catch (final Exception e) {
			e.printStackTrace(); // TODO proper error handling
		}

		final AssemblyConnectorSelectionDialog connectorDialog = new AssemblyConnectorSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		connectorDialog.setMessage(DIALOG_MESSAGE);
		connectorDialog.setElements(assemblyConnectors.toArray(new Connector[0]));
		if (connectorDialog.open() != Dialog.OK) {
			return;
		}
		selectedConnector = connectorDialog.getResultConnector();
		selection = selectedConnector;



		final LinkedList<Stereotype> unapplyedStereotypes = new LinkedList<>();

		for (final Stereotype stereotype : StereotypeAPI.getApplicableStereotypes(selection)) {
			if (!StereotypeAPI.isStereotypeApplied(selection, stereotype.getName())) {
				unapplyedStereotypes.add(stereotype);
			}
		}


		final RoleStereotypeSelectionDialog dialog = new RoleStereotypeSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		dialog.setMessage(DIALOG_MESSAGE + selection.eClass().getName());

		dialog.setElements(unapplyedStereotypes.toArray(new Stereotype[0]));

		if (dialog.open() != Dialog.OK) {
			return;
		}

		final Stereotype selectedRoleStereotype = dialog.getResultRoleStereotype();

		StereotypeAPI.applyStereotype(selection, selectedRoleStereotype);
	}

	/**
	 * Tests whether the object this tool is applied to is a {@link System}.
	 */
	@Override
	public boolean canExecute(final Collection<? extends EObject> selections) {
		if (selections.size() != 1) {
			return false;
		}
		return true;
//		for (final EObject object : selections) {
//			return true;
//		}
//		return false;
	}

}
