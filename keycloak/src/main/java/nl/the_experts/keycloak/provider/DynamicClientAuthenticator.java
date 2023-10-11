package nl.the_experts.keycloak.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Map;

public class DynamicClientAuthenticator implements Authenticator {

    private static final Log LOGGER = LogFactory.getLog(DynamicClientAuthenticator.class);

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        LOGGER.info("Dynamic Client Authenticator authenticate was called");

        context.challenge(context.form().createLoginUsernamePassword());
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        LOGGER.info("Dynamic Client Authenticator action was called");

        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();

        // username, password, credentialId
        for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
            LOGGER.info(entry.getKey() + ":" + entry.getValue());
        }

        context.success();
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {
    }
}
