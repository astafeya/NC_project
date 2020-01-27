package repository;

import model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text, Integer> {
    List<Text> findByOwnerIDAndVisibleIsTrue(int ownerID);
    List<Text> findByOwnerIDAndVisibleIsFalse(int ownerID);
    List<Text> findByTitle(String title);
}
