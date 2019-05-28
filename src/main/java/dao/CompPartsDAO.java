package dao;
import models.ComputerPart;

import java.util.List;

public interface CompPartsDAO {
    public ComputerPart getByName(String name);
    public ComputerPart getById(int id);
    public void save(ComputerPart part);
    public void update(ComputerPart part);
    public void delete(ComputerPart part);
    public List<ComputerPart> getAll();
    public List<ComputerPart> getAll(int page);
    public int getCount();
    public int getCountNecessary();
    public int getCountUnnecessary();
    public List<ComputerPart> getAllNecessary(int page);
    public List<ComputerPart> getAllUnnecessary(int page);
}
