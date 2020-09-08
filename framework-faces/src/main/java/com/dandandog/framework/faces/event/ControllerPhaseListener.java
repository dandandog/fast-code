package com.dandandog.framework.faces.event;

import com.dandandog.framework.faces.controller.FacesController;

import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * @author JohnnyLiu
 */
public class ControllerPhaseListener implements PhaseListener {


    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public void afterPhase(PhaseEvent event) {
        UIViewRoot view = event.getFacesContext().getViewRoot();
        if (view.getViewMap(false) == null) {
            FacesController controller = FacesController.getCurrentInstance();
            if (controller != null) {
                controller.onEntry();
            }
        }
    }


    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
