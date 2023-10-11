package nl.the_experts.keycloak.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

public class DynamicClientAuthenticatorFactory implements AuthenticatorFactory {

    private static final Log LOGGER = LogFactory.getLog(DynamicClientAuthenticatorFactory.class);

    public static final String PROVIDER_ID = "dynamic-client";

    private static final Authenticator SINGLETON = new DynamicClientAuthenticator();

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.ALTERNATIVE,
            AuthenticationExecutionModel.Requirement.DISABLED
    };

    @Override
    public String getDisplayType() {
        return "Dynamic-Client";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Configures a client if it doesn't exist";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return null;
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        LOGGER.info("DynamicClientAuthenticatorFactory: create");
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope scope) {
        LOGGER.info("DynamicClientAuthenticatorFactory: init");
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        LOGGER.info("DynamicClientAuthenticatorFactory: postInit");
    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}

