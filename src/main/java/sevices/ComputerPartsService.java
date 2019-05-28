package sevices;

import models.ComputerPart;
import dao.CompPartsDAO;
import dao.MySQLDao;
import models.ComputerPartWrapper;

import javax.persistence.PersistenceException;
import java.util.List;

public class ComputerPartsService {
    private CompPartsDAO dao = new MySQLDao();

    public ComputerPart getPartByName(String name){
        return  dao.getByName(name);
    }

    public ComputerPart getPartById(int id) {return dao.getById(id);}

    public void updatePart(ComputerPartWrapper part){
        try {
            dao.update(new ComputerPart(part));
        }
        catch (PersistenceException e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void deletePart(ComputerPart part){
        dao.delete(part);
        System.out.println(getCountParts()+"  "+ getCountNecessaryParts()+"  "+ getCountUnnecessaryParts());
    }

    public void savePart(ComputerPartWrapper part){
        try{
            dao.save(new ComputerPart(part));
        }
        catch (PersistenceException e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public List<ComputerPart> getAllParts(){
        return dao.getAll();
    }

    public int getWholeComputers(){
        List<ComputerPart> parts = getAllParts();
        int result = Integer.MAX_VALUE;
        for(ComputerPart part : parts){
            if(part.getIsNecessary() && part.getQuantity() < result){
                result = part.getQuantity();
            }
        }
        if (result == Integer.MAX_VALUE){
            result = 0;
        }
        return  result;
    }

    public List<ComputerPart> getParts(int page){
        return dao.getAll(page);
    }

    public int getCountParts(){
        return  dao.getCount();
    }

    public List<ComputerPart> getNecessaryParts(int page){
        return dao.getAllNecessary(page);
    }

    public List<ComputerPart> getUnnecessaryParts(int page){
        return dao.getAllUnnecessary(page);
    }

    public int getCountNecessaryParts(){
        return dao.getCountNecessary();
    }

    public int getCountUnnecessaryParts(){
        return dao.getCountUnnecessary();
    }
}