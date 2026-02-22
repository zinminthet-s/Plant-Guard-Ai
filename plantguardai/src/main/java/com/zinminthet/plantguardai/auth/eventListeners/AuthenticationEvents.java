package com.zinminthet.plantguardai.auth.eventListeners;

import jakarta.persistence.EntityListeners;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent){

    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent){

    }

}
