/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.cdi.tck.tests.lookup.modules;

import static org.jboss.cdi.tck.cdi.Sections.INTER_MODULE_INJECTION;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.cdi.tck.AbstractTest;
import org.jboss.cdi.tck.shrinkwrap.WebArchiveBuilder;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecAssertions;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author Matej Briskar
 */
@SpecVersion(spec = "cdi", version = "2.0-EDR2")
public class EnabledProducerFieldInjectionAvailability02Test extends AbstractTest {

    @Deployment
    public static WebArchive createTestArchive() {
        WebArchive webArchive = new WebArchiveBuilder().withTestClass(EnabledProducerFieldInjectionAvailability02Test.class)
                .withClasses(FooFieldProducer.class, ProducedFoo.class).withBeanLibrary(Foo.class, Bar.class).withBeanLibrary(WebBar.class).build();
        return webArchive;
    }

    @Inject
    Bar bar;

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTER_MODULE_INJECTION, id = "e") })
    public void testInjection() throws Exception {
        Assert.assertEquals(bar.ping(), 0);
        assert bar.getFoo() instanceof ProducedFoo;
    }

}