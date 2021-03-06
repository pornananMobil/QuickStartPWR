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
package com.okta.sdk.resource.identity.provider;

import com.okta.commons.lang.Classes;

public class IdentityProviderBuilders {

    public static OIDCIdentityProviderBuilder oidc() {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.OidcIdentityProviderBuilder");
    }

    public static IdentityProviderBuilder google() {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.GoogleIdentityProviderBuilder");
    }

    public static IdentityProviderBuilder facebook() {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.FacebookIdentityProviderBuilder");
    }

    public static IdentityProviderBuilder microsoft() {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.MicrosoftIdentityProviderBuilder");
    }

    public static IdentityProviderBuilder linkedin() {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.LinkedInIdentityProviderBuilder");
    }

    public static IdentityProviderBuilder ofType(String type) {
        return Classes.newInstance("com.okta.sdk.impl.resource.identity.provider.StringTypeIdentityProviderBuilder", type);
    }
}
