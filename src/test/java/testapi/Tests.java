package testapi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.AssertJUnit.assertEquals;

public class Tests extends Basetest {
    String url = URL + "users";


    @Test
    public void test1() {
        given()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void response_not_null() {
        given()
                .get(url)
                .then()
                .assertThat()
                .contentType(JSON);
    }

    @Test
    public void search() {
        String json = get(url).asString();
        JsonPath jp = new JsonPath(json);
        Map search = jp.get("find {e -> e.id =~ /3/}");
        assertEquals("Nathan@yesenia.net", search.get("email"));
        assertEquals("Clementine Bauch", search.get("name"));
        assertEquals("Samantha", search.get("username"));

    }

    @Test
    public void searchby() {
        Response res = given().get(url);
        res.jsonPath().getString("Romaguera-Crona");

    }

    @Test
    public void resnoterror() {
        given().log().everything().when().get(url).then().log().ifValidationFails().statusCode(200);

        }


}

