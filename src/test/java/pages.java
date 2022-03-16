import Base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;


import static utils.util.key;
import static utils.util.token;

public class pages extends Base {

    Logger log = LogManager.getLogger(pages.class);

    public ArrayList cardID = new ArrayList();


    public void cardCreate(String cardName) {
        String list = random(this.boardID);
        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", cardName);
        pathParam.put("idList", list);
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(pathParam.toString());
        response = httpRequest.request(Method.POST, "/cards");
        this.cardID.add(response.jsonPath().getString("id"));
        log.info("CardsID: " + cardID);

    }

    public void cardUpdate(String name, int Index) {
        String cardID = (String) this.cardID.get(Index);
        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", name);
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(pathParam.toString());
        response = httpRequest.request(Method.PUT, "/cards/" + cardID);
        String isUpdatedName = response.jsonPath().getString("name");
        log.info("UpdateCardName: " + isUpdatedName);
    }

    public void cardDelete(int index) {

        String cardID = (String) this.cardID.get(index);
        JSONObject params = new JSONObject();
        params.put("key", key);
        params.put("token", token);
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(params.toString());
        response = httpRequest.request(Method.DELETE, "/cards/" + index);
        log.info("Card DELETE is Complate");

    }

    public String boardID = "";

    public void boardCreate(String name) {
        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", name);
        httpRequest = RestAssured.given();
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(pathParam.toString());
        response = httpRequest.request(Method.POST, "/boards");
        this.boardID = response.jsonPath().getString("id");
        log.info("BoardID: " + boardID);
    }

    public void boardDelete() {
        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(pathParam.toString());
        response = httpRequest.request(Method.DELETE, "/boards/" + boardID);
        log.info("Board is DELETE");
    }
}
