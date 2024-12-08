package actions;

import domain.Identifiable;
import repository.IRepository;

public class ActionRemove<ID, T extends Identifiable<ID>> implements IAction {
    private IRepository<ID, T> repo;
    private T deletedElement;

    public ActionRemove(IRepository<ID, T> repo, T deletedElement) {
        this.repo = repo;
        this.deletedElement = deletedElement;
    }

    @Override
    public void executeUndo() {
        // adaug elementul inapoi in repos
        repo.addEntity(deletedElement.getId(), deletedElement);
    }

    @Override
    public void executeRedo() {
        // sterg elementul din repo
        repo.deleteEntity(deletedElement.getId());
    }
}
