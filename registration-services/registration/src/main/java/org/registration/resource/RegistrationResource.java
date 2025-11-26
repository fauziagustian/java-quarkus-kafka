package org.registration.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.registration.dto.CreatePolishRequest;
import org.registration.dto.CreatePolishResponse;
import org.registration.dto.RegistrationEvent;
import org.registration.entity.PolishRegistration;
import org.registration.repository.PolishRegistrationRepository;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource {

    private static final AtomicInteger POLICY_COUNTER = new AtomicInteger(10000);

    @Inject
    PolishRegistrationRepository repository;

    @Inject
    ObjectMapper objectMapper;

    @Channel("registration-out")
    Emitter<String> registrationEmitter;

    @POST
    @Path("/create-polish")
    @Transactional
    public Response createPolish(CreatePolishRequest request) {

        // 1. Map request ke entity
        PolishRegistration entity = new PolishRegistration();
        entity.setFullname(request.getCustomerName());
        entity.setEmail(request.getEmail());
        entity.setProductName(request.getProductName());
        entity.setAmount(request.getAmount());

        String policyCode = "PPI" + POLICY_COUNTER.incrementAndGet();
        entity.setPolicyCode(policyCode);

        repository.persist(entity);

        RegistrationEvent event = new RegistrationEvent(
                "registration",
                entity.getEmail(),
                entity.getPolicyCode()
        );

        try {
            String json = objectMapper.writeValueAsString(event);
            registrationEmitter.send(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CreatePolishResponse response = new CreatePolishResponse(
                entity.getFullname(),
                entity.getEmail(),
                entity.getProductName(),
                entity.getAmount(),
                entity.getPolicyCode()
        );

        return Response.ok(response).build();
    }
}