package com.everis.base.stepDefinitions;

import com.everis.base.PetStoreStep;
import com.everis.base.models.Order;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PetStoreSD {
    @Steps
    PetStoreStep petStore;

    @Given("dado que estoy en la pagina")
    public void dadoQueEstoyEnLaPagina() {
    }

    @When("consulto una orden con id {int}")
    public void consultoUnaOrdenConIdId(int orderId) {
        petStore.consultaOrden(orderId);
    }

    @Then("el código de estado de la respuesta debe ser {int}")
    public void elCódigoDeEstadoDeLaRespuestaDebeSer(int arg0) {
        petStore.validarCodigoRespuesta(arg0);
    }

    @When("creo una orden con id {int}, petId {int}, quantity {int}")
    public void creoUnaOrdenConIdIdPetIdPetIdQuantityQuantity(int id, int petId, int quantity) {
        petStore.crearOrden(id, petId, quantity);
    }

    @And("la respuesta debe contener el id {int}, petId {int}, quantity {int}")
    public void laRespuestaDebeContenerElIdPetIdQuantity(int id, int petId, int quantity) {
        Order orden = petStore.getRespuestaOrder();
        assertNotNull(orden);
        assertEquals(id, orden.getId());
        assertEquals(petId, orden.getPetId());
        assertEquals(quantity, orden.getQuantity());
    }}
