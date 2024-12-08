package actions;

import domain.Identifiable;
import repository.IRepository;

public class ActionUpdate<ID, T extends Identifiable<ID>> implements IAction {
    private IRepository<ID, T> repo;
    private T oldElement; // starea veche
    private T newElement; // starea nouă

    public ActionUpdate(IRepository<ID, T> repo, T oldElement, T newElement) {
        this.repo = repo;
        this.oldElement = oldElement;
        this.newElement = newElement;
    }

    @Override
    public void executeUndo() {
        // revin la starea veche
        repo.modifyEntity(oldElement.getId(), oldElement);
    }

    @Override
    public void executeRedo() {
        // aplic starea noua
        repo.modifyEntity(newElement.getId(), newElement);
    }
}
