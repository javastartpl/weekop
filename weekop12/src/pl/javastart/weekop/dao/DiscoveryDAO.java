package pl.javastart.weekop.dao;
 
import java.util.List;
 
import pl.javastart.weekop.model.Discovery;
 
public interface DiscoveryDAO extends GenericDAO<Discovery, Long>{
 
    List<Discovery> getAll();
     
}