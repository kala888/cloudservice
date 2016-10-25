package scd.core.quartz;

import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

/**
 * Simplest possible implementation for accessing the MongoDB to persist job
 * history documents.
 *
 * @author Niko Schmuck
 */
@Repository
public class JobHistoryRepository {

    @Autowired
    private MongoClient mongo;



    public void add(Map<String, Object> keys) {
        // TODO: make configurable / use repository ?
        // TODO: set expire TTL based on 'ts' field (ie. 7 days)
        mongo.getDatabase("jobs-demo").getCollection("job_history").insertOne(new Document(keys));
    }

}
