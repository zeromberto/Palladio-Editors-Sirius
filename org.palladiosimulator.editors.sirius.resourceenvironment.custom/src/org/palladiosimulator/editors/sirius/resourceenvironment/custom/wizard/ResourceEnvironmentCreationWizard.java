package org.palladiosimulator.editors.sirius.resourceenvironment.custom.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.resourceenvironment.custom.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;

public class ResourceEnvironmentCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		viewpoint = Activator.getDefault().getViewpoint();
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		representationDescription = Activator.getDefault().getRepresentationDescription();
		
		String defaultRepresentationName = "new " + Activator.REPRESENTATION_NAME;
		this.representationCreationPage.setDefaultRepresentationName(defaultRepresentationName);

		ResourceEnvironment obj = ResourceenvironmentFactory.eINSTANCE.createResourceEnvironment();
		obj.setEntityName("New ResourceEnvironment");
		
		
		modelObject = obj;
	}

}
