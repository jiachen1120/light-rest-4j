package com.networknt.swagger;

import com.networknt.handler.config.EndpointSource;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Lists endpoints defined in the Swagger spec parsed by SwaggerHelper.
 */
public class SwaggerEndpointSourceTest {

    @Test
    public void testFindBasePath() {
        SwaggerEndpointSource source = new SwaggerEndpointSource();
        String basePath = source.findBasePath();
        Assert.assertEquals("/v2", basePath);
    }

    @Test
    public void testPetstoreEndpoints() {
        SwaggerEndpointSource source = new SwaggerEndpointSource();
        Iterable<EndpointSource.Endpoint> endpoints = source.listEndpoints();

        // Extract a set of string representations of endpoints
        Set<String> endpointStrings = StreamSupport
            .stream(endpoints.spliterator(), false)
            .map(Object::toString)
            .collect(Collectors.toSet());

        // Assert that we got what we wanted
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                "/v2/store/inventory@get",
                "/v2/store/order@post",
                "/v2/store/order/{orderId}@get",
                "/v2/store/order/{orderId}@delete",

                "/v2/user@post",
                "/v2/user/logout@get",
                "/v2/user/login@get",
                "/v2/user/createWithList@post",
                "/v2/user/createWithArray@post",
                "/v2/user/{username}@delete",
                "/v2/user/{username}@put",
                "/v2/user/{username}@get",

                "/v2/pet@post",
                "/v2/pet@put",
                "/v2/pet/findByStatus@get",
                "/v2/pet/findByTags@get",
                "/v2/pet/{petId}@delete",
                "/v2/pet/{petId}@get",
                "/v2/pet/{petId}@post",
                "/v2/pet/{petId}/uploadImage@post"
            )),
            endpointStrings
        );
    }

}
