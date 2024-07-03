package nl.nl0e0.orchestrationamqp.repository;


import nl.nl0e0.petclinicentity.owner.Pet;
import org.springframework.data.repository.Repository;


public interface PetRepository extends Repository<Pet,Integer> {
   Pet findById(Integer id);
}
