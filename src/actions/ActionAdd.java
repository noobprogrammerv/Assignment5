package actions;

import domain.Identifiable;
import repository.IRepository;

public class ActionAdd<ID, T extends Identifiable<ID>> implements IAction {
    private IRepository<ID, T> repo;
    private T addedElement;

    public ActionAdd(IRepository<ID, T> repo, T addedElement) {
        this.repo = repo;
        this.addedElement = addedElement;
    }

    @Override
    public void executeUndo() {
        repo.deleteEntity(addedElement.getId());
    }

    @Override
    public void executeRedo() {
        repo.addEntity(addedElement.getId(), addedElement);
    }
}
