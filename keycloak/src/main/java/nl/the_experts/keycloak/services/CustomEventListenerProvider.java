package nl.the_experts.keycloak.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.*;

public class CustomEventListenerProvider implements EventListenerProvider {
    private static final Log LOGGER = LogFactory.getLog(CustomEventListenerProvider.class);

    private final RealmProvider model;

    public CustomEventListenerProvider(KeycloakSession session) {
        this.model = session.realms();
    }

    @Override
    public void onEvent(Event event) {
        if (EventType.LOGIN_ERROR.equals(event.getType())) {
            LOGGER.info("Login Error Event for clientId: " + event.getClientId());

            RealmModel realm = this.model.getRealm(event.getRealmId());

            ClientModel client = realm.getClientByClientId(event.getClientId());

            if (client == null) {
                LOGGER.info("Client does not exist yet");
                // Create new client
                realm.addClient(event.getClientId());
            }
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {

    }
}
