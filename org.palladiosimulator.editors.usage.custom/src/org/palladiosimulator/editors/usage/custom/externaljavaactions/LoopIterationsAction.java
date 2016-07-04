package org.palladiosimulator.editors.usage.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class LoopIterationsAction extends SetRandomVariable {

	public LoopIterationsAction() {
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		Loop l = (Loop) element;
		return l.getLoopIteration_Loop();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.INT;
	}

}
