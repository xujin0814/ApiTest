package department.api;

import department.Common;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @Author: xujin
 * @Date: 2019/12/31 11:08 下午
 */
public class Department {
    String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/";
    public int parentDepartId = 2;
    public Response list(int id){
        /**
         * department Id
         */
        return given()
                .queryParam("access_token", Common.getInstance().getToken())
                .queryParam("id",parentDepartId)
                .when().log().all()
                .get(baseUrl+"list")
                .then().log().all()
                .body("errcode", equalTo(0))
                .extract().response();
    }

    public Response create(String name, int parentid){
        HashMap<String, Object> data = new HashMap<>();
        data.put("name",name);
        data.put("parentid",parentid);
        return given()
                .queryParam("access_token", Common.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .log().all()
                .post(baseUrl+"create")
                .then()
                .log().all()
                .body("errcode", equalTo(0))
                .extract().response();

    }

    public Response create(String name){
        return create(name,parentDepartId);

    }

    public Response delete(int id){
        return given()
                .queryParam("access_token",Common.getInstance().getToken())
                .queryParam("id",id)
        .when().log().all()
                .get(baseUrl+"delete")
        .then().log().all()
                .body("errcode", equalTo(0))
        .extract().response();


    }
}
