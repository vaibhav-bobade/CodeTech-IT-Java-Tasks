package task4_ai_based_recommendation_system;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // 1. Load the data from the CSV file
            DataModel model = new FileDataModel(new File("data.csv"));

            // 2. Calculate Similarity (how alike are two users?)
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // 3. Define the "Neighborhood" (who are the closest neighbors?)
            // Threshold of 0.1 means users must be at least 10% similar
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

            // 4. Build the Recommender
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // 5. Get 3 recommendations for User ID 1
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            System.out.println("Recommendations for User 1:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() + " | Predicted Rating: " + recommendation.getValue());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}