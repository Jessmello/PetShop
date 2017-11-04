package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import model.Animal;
import model.DAOFactory;
import service.AnimalRESTClient;

/**
 *
 * @author Jess
 */
@Named(value = "animalBean")
@SessionScoped
public class AnimalBean implements Serializable{
    
    @Inject @New
    private Animal animal;
    private List<Animal> animais;

    public AnimalBean(){
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    
    
    public String crudAnimal() throws SQLException, ClassNotFoundException {
        animais = new AnimalRESTClient().findAll();
        return "/protected/crudAnimal";
    }
    
    public String telaIncluir() {
        animal = new Animal();
        return "/protected/incluirAnimal";
    }
    
    public String novoAnimal() throws SQLException, ClassNotFoundException {
        new AnimalRESTClient().create(animal);
        animal = new Animal();
        return crudAnimal();
    }
    
    public void consultar() throws SQLException, ClassNotFoundException{
        animais = DAOFactory.getAnimalDao().consultar(animal.getNome());
    }
    
    public String alterarAnimal(Animal a) throws SQLException, ClassNotFoundException{
        animal = a;
        return "/protected/alterarAnimal";
    }
    
    public String excluir(Animal a) throws SQLException, ClassNotFoundException{
        new AnimalRESTClient().delete(a.getId());
        return crudAnimal();
    }
    
    public String salvarAlteracaoAnimal() throws SQLException, ClassNotFoundException{
        new AnimalRESTClient().edit(animal);
        return crudAnimal();
    }
    
    public List<SelectItem> getListarAnimais() throws SQLException, ClassNotFoundException{
        animais = DAOFactory.getAnimalDao().listar();
        List<SelectItem> animaisList = new ArrayList<>();
        for (Animal a : animais) {
            animaisList.add(new SelectItem(a.getId(),a.getNome()));
        }
        return animaisList;
    }
}
