package com.braintreepayments.api.dropin;

import android.content.Intent;
import android.os.Bundle;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

import static com.braintreepayments.api.BraintreeFragmentTestUtils.setHttpClient;
import static com.braintreepayments.api.test.TestTokenizationKey.TOKENIZATION_KEY;

public class BraintreePaymentUnitTestActivity extends BraintreePaymentActivity {

    public BraintreeFragment braintreeFragment;
    public BraintreeUnitTestHttpClient httpClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.bt_add_card_activity_theme);

        if (mPaymentRequest == null) {
            mPaymentRequest= new PaymentRequest().tokenizationKey(TOKENIZATION_KEY);
        }

        Intent intent = new Intent()
                .putExtra(PaymentRequest.EXTRA_CHECKOUT_REQUEST, mPaymentRequest);
        setIntent(intent);

        super.onCreate(savedInstanceState);
    }

    public void setPaymentRequest(PaymentRequest paymentRequest) {
        mPaymentRequest = paymentRequest;
    }

    @Override
    protected BraintreeFragment getBraintreeFragment() throws InvalidArgumentException {
        if (braintreeFragment == null) {
            braintreeFragment = super.getBraintreeFragment();
            setHttpClient(braintreeFragment, httpClient);
        }

        return braintreeFragment;
    }
}
