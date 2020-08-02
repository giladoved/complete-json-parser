import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    private static final String resourcesDir = "src/resources";

    public static void main(String[] args) {
        // Uncomment to run the sample usage code
//        sampleUsage();

        // Uncomment to run the performance benchmarks against the org.json lib using a json file of your choice
//        runBenchmarks("big.json");
    }

    public static void runBenchmarks(String filename) {
        try {
            File hugeJson = new File(resourcesDir, filename);
            String hugeJsonString = Files.readString(hugeJson.toPath());

            long orgJson = benchmarkOrgJson(hugeJsonString);
            System.out.println("org.json took: " + orgJson + "ms");

            long myJsonParser = benchmarkMyJsonParser(hugeJsonString);
            System.out.println("my json parser took: " + myJsonParser + "ms");
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    public static long benchmarkMyJsonParser(String jsonStr) throws IOException, ParserException {
        long before = System.currentTimeMillis();
        JSONArray arr = new JSONParser(jsonStr).parseJSONArray();
        long ms = System.currentTimeMillis() - before;
        assert arr.getJSONObject(24).getString("type").equals("TestingValue");
        return ms;
    }

    public static long benchmarkOrgJson(String jsonStr) {
        long before = System.currentTimeMillis();
        org.json.JSONArray arr = new org.json.JSONArray(jsonStr);
        long ms = System.currentTimeMillis() - before;
        assert arr.getJSONObject(24).getString("type").equals("FeatureCollection");
        return ms;
    }

    public static void sampleUsage() {
        File sampleJson = new File(resourcesDir, "sample.json");

        try {
            JSONObject jsonObject = new JSONParser(sampleJson).parseJSONObject();

            JSONObject article = jsonObject.getJSONObject("article");

            String title = article.getString("title");
            System.out.println("title: " + title);

            JSONObject author = article.getJSONObject("author");
            String authorName = author.getString("name");
            String authorAddress = author.getString("address");
            String authorPhone = author.getString("phone");
            System.out.println("author name: " + authorName);
            System.out.println("author address: " + authorAddress);
            System.out.println("author phone: " + authorPhone);

            Integer pages = article.getInteger("pages");
            System.out.println("pages: " + pages);

            JSONArray reviews = article.getJSONArray("reviews");
            for (Object reviewObj : reviews) {
                JSONObject review = (JSONObject) reviewObj;
                String name = review.getString("name");
                Double rating = review.getDouble("rating");
                System.out.println(name + ": " + rating);
            }

            Object coauthor = article.get("coauthor");
            System.out.println("coauthor: " + coauthor);

            Boolean bestSeller = article.getBoolean("bestSeller");
            Boolean published = article.getBoolean("published");

            System.out.println("best seller: " + bestSeller);
            System.out.println("published: " + published);

        } catch (ParserException e) {
            System.err.println("Error parsing file: " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println("Error opening file");
        }
    }
}