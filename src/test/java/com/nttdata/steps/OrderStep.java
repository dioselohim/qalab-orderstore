package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import net.thucydides.core.annotations.Step;

public class OrderStep {

    private static String URL_BASE = null;

    public void setUrlBase(String urlBase) {
        URL_BASE = urlBase;
    }

    @Step("Crear order en PetStore")
    public void createOrder(int orderId, int petId, int quantity, String shipDate, String status) {
        System.out.println("Creando orden: "+orderId);
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .body("{\n" +
                        "  \"id\": \""+orderId+"\",\n" +
                        "  \"petId\": \""+petId+"\",\n" +
                        "  \"quantity\": \""+quantity+"\",\n" +
                        "  \"shipDate\": \""+ shipDate+"\",\n" +
                        "  \"status\": \""+status+"\",\n" +
                        "  \"complete\": \""+true+"\"\n" +
                        "}")
                .log().all()
                .post("store/order")
                .then()
                .log().all()
        ;
    }

    @Step("Obtener order de PetStore")
    public void getOrder(int orderId) {
        System.out.println("Consultando orden: "+orderId);
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .log().all()
                .get("store/order/"+orderId)
                .then()
                .log().all()
        ;
    }

    @Step("Validar que el código de respuesta sea {0}")
    public void validateStatusCode(int expectedStatusCode) {
        int actualStatusCode = SerenityRest.lastResponse().statusCode();
        Assert.assertEquals("El código de respuesta no es el esperado", expectedStatusCode, actualStatusCode);
    }

    @Step("Validar el cuerpo de la respuesta para la orden con ID: {0}")
    public void validateResponseBody(int expectedOrderId, int expectedPetId, int expectedQuantity, String expectedShipDate, String expectedStatus) {
        int actualOrderId = SerenityRest.lastResponse().jsonPath().getInt("id");
        int actualPetId = SerenityRest.lastResponse().jsonPath().getInt("petId");
        int actualQuantity = SerenityRest.lastResponse().jsonPath().getInt("quantity");
        String actualShipDate = SerenityRest.lastResponse().jsonPath().getString("shipDate");
        String actualStatus = SerenityRest.lastResponse().jsonPath().getString("status");

        Assert.assertEquals("El orderId no coincide", expectedOrderId, actualOrderId);
        Assert.assertEquals("El petId no coincide", expectedPetId, actualPetId);
        Assert.assertEquals("La cantidad no coincide", expectedQuantity, actualQuantity);
        Assert.assertEquals("La fecha de envío no coincide", expectedShipDate, actualShipDate);
        Assert.assertEquals("El estado no coincide", expectedStatus, actualStatus);
    }

}
