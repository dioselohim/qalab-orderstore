package com.nttdata.glue;

import com.nttdata.steps.OrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OrderStepsDef {

    @Steps
     OrderStep orderStep;

    @Given("que el servicio PetStore está disponible en {string}")
    public void configurarUrlBase(String url) {
        orderStep.setUrlBase(url);
    }

    @When("creo la orden con orderId {int}, petId {int}, quantity {int}, shipDate {string} y status {string}")
    public void crearOrden(int orderId, int petId, int quantity, String shipDate, String status) {
        orderStep.createOrder(orderId, petId, quantity, shipDate, status);
    }

    @Then("valido que la respuesta sea {int}")
    public void validarCodigoDeEstado(int statusCode) {
        orderStep.validateStatusCode(statusCode);
    }

    @And("valido que la orden sea creada con los datos enviados orderId {int} petId {int}, quantity {int}, shipDate {string} y status {string}")
    public void validarCuerpoDeRespuesta(int orderId, int petId, int quantity, String shipDate, String status) {
        orderStep.validateResponseBody(orderId, petId, quantity, shipDate, status);
    }

    @When("consulto la orden con ID {int}")
    public void consultarOrden(int orderId) {
        orderStep.getOrder(orderId);
    }



}
