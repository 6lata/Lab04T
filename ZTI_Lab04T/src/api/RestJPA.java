package api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import database2.Person;

@Path("/jpa/person")
public class RestJPA {
	
	private EntityManagerFactory managerFactory; 
	private EntityManager entityManager; // = managerFactory.createEntityManager();
	private EntityTransaction entityTransaction;

	public RestJPA() {
	   managerFactory = Persistence.createEntityManagerFactory("PU_Postgresql");
	   entityManager = managerFactory.createEntityManager();
	   entityTransaction = entityManager.getTransaction(); 

	}
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Person[] get() {
        System.out.println("GET");
		List<Person> people = null;
		try {
			@SuppressWarnings("unchecked")
			List<Person> resultList = (List<Person>) entityManager.createNamedQuery("findAll").getResultList();
			//people = (List<Person>) entityManager.createNamedQuery("findAll").getResultList();
			people = resultList;
			// manager.close();
		} catch (Exception e) {
			System.out.println("Failed !!! " + e.getMessage());
		}
		return people.toArray(new Person[0]);
    }
    
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person get(@PathParam("id") String id) {
        System.out.println("GET");
        Person entity = (Person) entityManager.find(Person.class, Integer.parseInt(id));
        return entity;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String post(Person person) {
        System.out.println("POST");
		entityTransaction.begin();
		entityManager.persist(person);
		entityManager.flush();
		entityTransaction.commit();
        return "add record" ;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String put(Person person) {
        System.out.println("PUT");
		entityTransaction.begin();
		entityManager.merge(person);
		entityTransaction.commit();
        return "update record" ;
    }

    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String delete(@PathParam("id") String id) {
        System.out.println("DELETE");
		entityTransaction.begin();
        Person entity = (Person) entityManager.find(Person.class, Integer.parseInt(id));
		entityManager.remove(entity);			
		entityManager.flush();
		entityTransaction.commit();
        return "delete record" ;
    }

}