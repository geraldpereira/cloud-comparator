package fr.byob.cloud.comparator.gwt.client.widget.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fr.byob.cloud.comparator.gwt.client.widget.constants.ProviderConstants;

/**
 * Warning : this test needs the rest application to be deployed on tomcat
 *
 *
 * @author Kojiro
 *
 */
public class ConstantsTest extends GWTTestCase {

	//	private ProviderConstants providerConstants;


	/**
	 * Add as many tests as you like.
	 */
	public void testConstants() {
		final ProviderConstants
		providerConstants = GWT.create(ProviderConstants.class);
		System.out.println(providerConstants.aws_ec2_details_site());
		System.out.println(providerConstants.getString("aws_ec2_site"));
	}

	@Override
	public String getModuleName() {
		return "fr.byob.cloud.comparator.gwt.Cloud_client";
	}

}
