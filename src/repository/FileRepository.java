package repository;

import domain.Identifiable;

public abstract class FileRepository<ID, T extends Identifiable<ID>> extends MemoryRepository<ID, T> {
    protected String filename;

    public FileRepository(String filename) {
        this.filename = filename;
        this.readFromFile();
    }

    abstract void readFromFile();
    abstract void writeToFile();

    @Override
    public void addEntity(ID id, T elem) {
        super.addEntity(id, elem);
        this.writeToFile();
    }
    @Override
    public void deleteEntity(ID id) {
        super.deleteEntity(id);
        writeToFile();
    }
    @Override
    public void modifyEntity(ID id, T type) {
        super.modifyEntity(id, type);
        writeToFile();
    }

}
