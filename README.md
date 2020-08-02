# JSON Parser

This is a simple but complete implementation of a JSON parser. It satisfies the
original JSON grammar defined at [json.org](https://www.json.org/json-en.html)

## Tests

I test the example JSON files from [json.org](https://json.org/example.html) for
sanity. Additionally, I test using the JSON checker [test suite](https://json.org/JSON_checker/).
I adapted hundreds of tests from the [nst repository](https://github.com/nst/JSONTestSuite).
I used [this blog post](http://seriot.ch/parsing_json.php) from the same author,
as a guide. In total, my parser is validated by 358 tests.

## Sample Usage

To use this parser, you should first create a `JSONParser` object.
You can initialize it using either a file, or a string of JSON.

You can then parse the JSON file by calling one of the following methods (depending on what the top level value of the JSON file is):

- `parser.parseString()`

- `parser.parseBoolean()`

- `parser.parseInteger()`

- `parser.parseDouble()`

- `parser.parseJSONObject()`

- `parser.parseJSONArray()`


Here is an sample:
```
{
    "data": {
        ...
    },
    "status": 200,
    "metadata": [...]
}
```

```java
File jsonFile = new File("sample.json");
try {
	Object obj = new JSONParser(jsonFile).parse();
	JSONObject jsonObject = (JSONObject) obj;
	JSONObject data = jsonObject.getJSONObject("data");
	Integer status = data.getInteger("status");
	JSONArray metadata = data.getJSONArray("metadata");
} catch (IOException e) {
	System.err.println("There was an error opening the file");
} catch (ParserException e) {
	System.err.println("Error parsing the json file: " + e.getLocalizedMessage());
}
```

There is a larger example in `Main.java` using `sample.json`.

### JSONObject

With a `JSONObject`, you can get its values by using:

- `jsonObject.getString(key)`

- `jsonObject.getBoolean(key)`

- `jsonObject.getInteger(key)`

- `jsonObject.getDouble(key)`

- `jsonObject.getJSONObject(key)`

- `jsonObject.getJSONArray(key)`

### JSONArray

With a `JSONArray`, you can get its items by using:

- `jsonArray.getString(index)`

- `jsonArray.getBoolean(index)`

- `jsonArray.getInteger(index)`

- `jsonArray.getDouble(index)`

- `jsonArray.getJSONObject(index)`

- `jsonArray.getJSONArray(index)`


## Performance Benchmarks

Using a sample json file of about 1100 lines generated from [json-generator](https://www.json-generator.com/).
Here are the times it took to parse:

org.json: 18ms

my json parser: 73ms

## Notes

The maximum allowed depth is 1024. This is arbitrary and can be redefined in the `JSONParser.java` class.