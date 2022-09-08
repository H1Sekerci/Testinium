package BaseTestClass;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TestiniumApiTest {

String key = "5118f41560eb8853b6c3b57f7f4a1200";
String token = "46845979215d2a79895ed8640c558f53c59f71ae1790e0fd049fcdc7f0db9d9d";
@Test
public void CreateBoard() throws UnirestException {

    given().contentType(ContentType.JSON)
            .queryParam("name","hikmetBoard")
            .queryParam("key",key)
            .queryParam("token",token)
            .post("https://api.trello.com/1/boards/");

}

@Test
public  void CreateCards() throws UnirestException {

    given().contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .queryParam("name","Kart1")
            .queryParam("desc","Bu Kart 1")
            .queryParam("idList","631995d1406d49017a625eae") // o anki To Do listesinin Id'si
            .queryParam("key",key)
            .queryParam("token",token)
            .post("https://api.trello.com/1/cards")
            .jsonPath();

    given().contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .queryParam("name","Kart2")
            .queryParam("desc","Bu Kart 2")
            .queryParam("idList","631995d1406d49017a625eae") // o anki To Do listesinin Id'si
            .queryParam("key",key)
            .queryParam("token",token)
            .post("https://api.trello.com/1/cards")
            .jsonPath();

}

@Test
public void KartGuncelle() throws UnirestException {

        String[] Ids = {"63199a5560a11b00984d7659","63199a56ac22e502f460069b"}; //kart 1 ve kart 2  idleri
        int idx = new Random().nextInt(Ids.length);
        String random = (Ids[idx]);

        given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .queryParam("name","Güncellenmiş " + String.valueOf(idx) +  "  Kart")
                .queryParam("desc","Bu kart " + String.valueOf(idx) +   "  Güncellendi")
                .queryParam("key",key)
                .queryParam("token",token)
                .put("https://api.trello.com/1/cards/" + random)
                .jsonPath();


    }

@Test
public  void KartSil() throws UnirestException {
    String[] Ids = {"63199a5560a11b00984d7659","63199a56ac22e502f460069b"};

    given().contentType(ContentType.JSON)
            .queryParam("key",key)
            .queryParam("token",token)
            .delete("https://api.trello.com/1/cards/"+ Ids[0]);

    given().contentType(ContentType.JSON)
            .queryParam("key",key)
            .queryParam("token",token)
            .delete("https://api.trello.com/1/cards/"+ Ids[1]);


}

@Test
public void BoardSil() throws UnirestException {

    String boardID = "631995d1406d49017a625ea7"; // o andaki board'un ID'si

    given().contentType(ContentType.JSON)
            .queryParam("key",key)
            .queryParam("token",token)
            .delete("https://api.trello.com/1/boards/"+ boardID);


    }
}

