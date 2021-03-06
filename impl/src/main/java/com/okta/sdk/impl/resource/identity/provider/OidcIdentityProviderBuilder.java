/*
 * Copyright 2020-Present Okta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.okta.sdk.impl.resource.identity.provider;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.identity.provider.IdentityProvider;
import com.okta.sdk.resource.identity.provider.IdentityProviderCredentials;
import com.okta.sdk.resource.identity.provider.IdentityProviderCredentialsClient;
import com.okta.sdk.resource.identity.provider.OIDCIdentityProviderBuilder;
import com.okta.sdk.resource.identity.provider.Protocol;
import com.okta.sdk.resource.identity.provider.ProtocolAlgorithmType;
import com.okta.sdk.resource.identity.provider.ProtocolAlgorithmTypeSignature;
import com.okta.sdk.resource.identity.provider.ProtocolAlgorithms;
import com.okta.sdk.resource.identity.provider.ProtocolEndpoint;
import com.okta.sdk.resource.identity.provider.ProtocolEndpoints;
import com.okta.sdk.resource.identity.provider.Provisioning;
import com.okta.sdk.resource.identity.provider.ProvisioningConditions;
import com.okta.sdk.resource.identity.provider.ProvisioningDeprovisionedCondition;
import com.okta.sdk.resource.identity.provider.ProvisioningGroups;
import com.okta.sdk.resource.identity.provider.ProvisioningSuspendedCondition;
import com.okta.sdk.resource.policy.IdentityProviderPolicy;
import com.okta.sdk.resource.policy.PolicyAccountLink;
import com.okta.sdk.resource.policy.PolicySubject;
import com.okta.sdk.resource.policy.PolicySubjectMatchType;
import com.okta.sdk.resource.policy.PolicyUserNameTemplate;

public class OidcIdentityProviderBuilder extends DefaultIdentityProviderBuilder<OIDCIdentityProviderBuilder>
    implements OIDCIdentityProviderBuilder {

    private IdentityProvider.IssuerModeEnum issuerMode;
    private String requestSignatureAlgorithm;
    private ProtocolAlgorithmTypeSignature.ScopeEnum requestSignatureScope;
    private String responseSignatureAlgorithm;
    private ProtocolAlgorithmTypeSignature.ScopeEnum responseSignatureScope;
    private ProtocolEndpoint.BindingEnum acsEndpointBinding;
    private ProtocolEndpoint.TypeEnum acsEndpointType;
    private ProtocolEndpoint.BindingEnum authorizationEndpointBinding;
    private String authorizationEndpointUrl;
    private ProtocolEndpoint.BindingEnum tokenEndpointBinding;
    private String tokenEndpointUrl;
    private ProtocolEndpoint.BindingEnum userInfoEndpointBinding;
    private String userInfoEndpointUrl;
    private ProtocolEndpoint.BindingEnum jwksEndpointBinding;
    private String jwksEndpointUrl;
    private String issuerUrl;
    private String userName;
    private PolicySubjectMatchType matchType;

    @Override
    public OidcIdentityProviderBuilder setIssuerMode(IdentityProvider.IssuerModeEnum issuerMode) {
        this.issuerMode = issuerMode;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setRequestSignatureAlgorithm(String requestSignatureAlgorithm) {
        this.requestSignatureAlgorithm = requestSignatureAlgorithm;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setRequestSignatureScope(ProtocolAlgorithmTypeSignature.ScopeEnum requestSignatureScope) {
        this.requestSignatureScope = requestSignatureScope;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setResponseSignatureAlgorithm(String responseSignatureAlgorithm) {
        this.responseSignatureAlgorithm = responseSignatureAlgorithm;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setResponseSignatureScope(ProtocolAlgorithmTypeSignature.ScopeEnum responseSignatureScope) {
        this.responseSignatureScope = responseSignatureScope;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setAcsEndpointBinding(ProtocolEndpoint.BindingEnum acsEndpointBinding) {
        this.acsEndpointBinding = acsEndpointBinding;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setAcsEndpointType(ProtocolEndpoint.TypeEnum acsEndpointType) {
        this.acsEndpointType = acsEndpointType;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setAuthorizationEndpointBinding(ProtocolEndpoint.BindingEnum authorizationEndpointBinding) {
        this.authorizationEndpointBinding = authorizationEndpointBinding;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setAuthorizationEndpointUrl(String authorizationEndpointUrl) {
        this.authorizationEndpointUrl = authorizationEndpointUrl;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setTokenEndpointBinding(ProtocolEndpoint.BindingEnum tokenEndpointBinding) {
        this.tokenEndpointBinding = tokenEndpointBinding;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setTokenEndpointUrl(String tokenEndpointUrl) {
        this.tokenEndpointUrl = tokenEndpointUrl;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setUserInfoEndpointBinding(ProtocolEndpoint.BindingEnum userInfoEndpointBinding) {
        this.userInfoEndpointBinding = userInfoEndpointBinding;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setUserInfoEndpointUrl(String userInfoEndpointUrl) {
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setJwksEndpointBinding(ProtocolEndpoint.BindingEnum jwksEndpointBinding) {
        this.jwksEndpointBinding = jwksEndpointBinding;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setJwksEndpointUrl(String jwksEndpointUrl) {
        this.jwksEndpointUrl = jwksEndpointUrl;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setIssuerUrl(String issuerUrl) {
        this.issuerUrl = issuerUrl;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public OidcIdentityProviderBuilder setMatchType(PolicySubjectMatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    @Override
    public IdentityProvider buildAndCreate(Client client) {
        return client.createIdentityProvider(client.instantiate(IdentityProvider.class)
            .setType(IdentityProvider.TypeValues.OIDC)
            .setName(name)
            .setIssuerMode(issuerMode)
            .setProtocol(client.instantiate(Protocol.class)
                .setAlgorithms(client.instantiate(ProtocolAlgorithms.class)
                    .setRequest(client.instantiate(ProtocolAlgorithmType.class)
                        .setSignature(client.instantiate(ProtocolAlgorithmTypeSignature.class)
                            .setAlgorithm(requestSignatureAlgorithm)
                            .setScope(requestSignatureScope)))
                    .setResponse(client.instantiate(ProtocolAlgorithmType.class)
                        .setSignature(client.instantiate(ProtocolAlgorithmTypeSignature.class)
                            .setAlgorithm(responseSignatureAlgorithm)
                            .setScope(responseSignatureScope))))
                .setEndpoints(client.instantiate(ProtocolEndpoints.class)
                    .setAcs(client.instantiate(ProtocolEndpoint.class)
                        .setBinding(acsEndpointBinding)
                        .setType(acsEndpointType))
                    .setAuthorization(client.instantiate(ProtocolEndpoint.class)
                        .setBinding(authorizationEndpointBinding)
                        .setUrl(authorizationEndpointUrl))
                    .setToken(client.instantiate(ProtocolEndpoint.class)
                        .setBinding(tokenEndpointBinding)
                        .setUrl(tokenEndpointUrl))
                    .setUserInfo(client.instantiate(ProtocolEndpoint.class)
                        .setBinding(userInfoEndpointBinding)
                        .setUrl(userInfoEndpointUrl))
                    .setJwks(client.instantiate(ProtocolEndpoint.class)
                        .setBinding(jwksEndpointBinding)
                        .setUrl(jwksEndpointUrl)))
                .setScopes(scopes)
                .setType(Protocol.TypeEnum.OIDC)
                .setCredentials(client.instantiate(IdentityProviderCredentials.class)
                    .setClient(client.instantiate(IdentityProviderCredentialsClient.class)
                        .setClientId(clientId)
                        .setClientSecret(clientSecret)))
                .setIssuer(client.instantiate(ProtocolEndpoint.class)
                    .setUrl(issuerUrl)))
            .setPolicy(client.instantiate(IdentityProviderPolicy.class)
                .setAccountLink(client.instantiate(PolicyAccountLink.class)
                    .setAction(PolicyAccountLink.ActionEnum.AUTO)
                    .setFilter(null))
                .setProvisioning(client.instantiate(Provisioning.class)
                    .setAction(Provisioning.ActionEnum.AUTO)
                    .setConditions(client.instantiate(ProvisioningConditions.class)
                        .setDeprovisioned(client.instantiate(ProvisioningDeprovisionedCondition.class)
                            .setAction(ProvisioningDeprovisionedCondition.ActionEnum.NONE))
                        .setSuspended(client.instantiate(ProvisioningSuspendedCondition.class)
                            .setAction(ProvisioningSuspendedCondition.ActionEnum.NONE)))
                    .setGroups(client.instantiate(ProvisioningGroups.class)
                        .setAction(ProvisioningGroups.ActionEnum.NONE)))
                .setMaxClockSkew(maxClockSkew)
                .setSubject(client.instantiate(PolicySubject.class)
                    .setUserNameTemplate(client.instantiate(PolicyUserNameTemplate.class)
                        .setTemplate(userName))
                    .setMatchType(matchType))));
    }
}
