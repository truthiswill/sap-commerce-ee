/*
 * Shop System Plugins - Terms of Use
 *
 * The plugins offered are provided free of charge by Wirecard AG and are explicitly not part
 * of the Wirecard AG range of products and services.
 *
 * They have been tested and approved for full functionality in the standard configuration
 * (status on delivery) of the corresponding shop system. They are under MIT license
 * and can be used, developed and passed on to third parties under
 * the same terms.
 *
 * However, Wirecard AG does not provide any guarantee or accept any liability for any errors
 * occurring when used in an enhanced, customized shop system configuration.
 *
 * Operation in an enhanced, customized configuration is at your own risk and requires a
 * comprehensive test phase by the user of the plugin.
 *
 * Customers use the plugins at their own risk. Wirecard AG does not guarantee their full
 * functionality neither does Wirecard AG assume liability for any disadvantages related to
 * the use of the plugins. Additionally, Wirecard AG does not guarantee the full functionality
 * for customized shop systems or installed plugins of other vendors of plugins within the same
 * shop system.
 *
 * Customers are responsible for testing the plugin's functionality before starting productive
 * operation.
 *
 * By installing the plugin into the shop system the customer agrees to these terms of use.
 * Please do not use the plugin if you do not agree to these terms of use!
 */

package com.wirecard.hybris.core.strategy.impl;

import com.wirecard.hybris.core.operation.PaymentOperation;
import com.wirecard.hybris.core.operation.impl.DefaultPaymentOperation;
import com.wirecard.hybris.exception.WirecardPaymenException;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.paymentstandard.model.StandardPaymentModeModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultWirecardPaymentOperationStrategyTest {

    private DefaultWirecardPaymentOperationStrategy defaultWirecardPaymentOperationStrategy;

    @Mock
    private Map<String, Map<String, PaymentOperation>> converterMap;

    @Mock
    private PaymentModeModel paymentModeModel;

    @Mock
    private Map<String, PaymentOperation> operationMap;

    @Mock
    private DefaultPaymentOperation paymentOperation;

    private String provider;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        defaultWirecardPaymentOperationStrategy = new DefaultWirecardPaymentOperationStrategy();
        defaultWirecardPaymentOperationStrategy.setConverterMap(converterMap);

        provider = "paypal";
        paymentOperation = new DefaultPaymentOperation();
        operationMap = Collections.singletonMap("AUTHORIZATION", paymentOperation);

        when(paymentModeModel.getCode()).thenReturn(provider);
        when(converterMap.get(provider)).thenReturn(operationMap);

    }

    @Test
    public void testGetOperation() throws WirecardPaymenException {

        assertNotNull("A Payment mode should be returned", defaultWirecardPaymentOperationStrategy.getOperation(paymentModeModel));

    }

}
