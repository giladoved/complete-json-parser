import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String expected =
                "[\"a\u0000a\"]";
        org.json.JSONArray res = new org.json.JSONArray(expected);
        Object swag = res.get(0);
        System.out.println("~~");
        System.out.println(swag);
        System.out.println("~~");


//        sampleUsage();
    }

    public static void sampleUsage() {
        File baseDir = new File("/Users/giladoved/dev/jsonparser/out/production/jsonparser");
        File sampleJson = new File(baseDir, "sample.json");

        try {
            Object obj = new JSONParser(sampleJson).parse();
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject article = jsonObject.getJSONObject("article");

            String title = article.getString("title");
            System.out.println("title: " + title);

            JSONObject author = article.getJSONObject("author");
            String authorName = author.getString("name");
            String authorAddress = author.getString("address");
            String authorPhone = author.getString("phone");
            System.out.println("authorName: " + authorName);
            System.out.println("authorAddress: " + authorAddress);
            System.out.println("authorPhone: " + authorPhone);

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

            System.out.println("bestSeller: " + bestSeller);
            System.out.println("published: " + published);

        } catch (ParserException e) {
            System.out.println("Error parsing file: " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("Error opening file");
        }
    }
}