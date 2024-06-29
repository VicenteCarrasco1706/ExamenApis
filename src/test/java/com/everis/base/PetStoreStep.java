package com.everis.base;

import com.everis.base.models.Order;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PetStoreStep {

    private String URL_BASE = "https://petstore.swagger.io/v2";
    private int codigoRespuesta;
    private Order respuestaOrder;

    public void consultaOrden(int orderId){
        respuestaOrder = given()
                .baseUri(URL_BASE)
                .log()
                .all()
                .when()
                .get("/store/order/" + orderId)
                .as(Order.class);

        codigoRespuesta = given().baseUri(URL_BASE).when().get("/store/order/" + orderId).getStatusCode();
        System.out.println("ID: " + respuestaOrder.getId());
        System.out.println("Pet ID: " + respuestaOrder.getPetId());
        System.out.println("Quantity: " + respuestaOrder.getQuantity());
        System.out.println("Ship Date: " + respuestaOrder.getShipDate());
        System.out.println("Status: " + respuestaOrder.getStatus());
        System.out.println("Complete: " + respuestaOrder.isComplete());
    }


    public void crearOrden(int id, int petId, int quantity) {
        Order nuevaOrden = new Order(id, petId, quantity);

        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(nuevaOrden)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .statusCode();

        respuestaOrder = given()
                .baseUri(URL_BASE)
                .when()
                .get("/store/order/" + id)
                .as(Order.class);

        System.out.println("Creada ID: " + respuestaOrder.getId());
        System.out.println("Creada Pet ID: " + respuestaOrder.getPetId());
        System.out.println("Creada Quantity: " + respuestaOrder.getQuantity());
    }

    public void validarCodigoRespuesta(int codigoEsperado) {
        if (codigoRespuesta != codigoEsperado) {
            throw new AssertionError("Expected status code: " + codigoEsperado + " but got: " + codigoRespuesta);
        }
    }

    public Order getRespuestaOrder() {
        return respuestaOrder;
    }


}
