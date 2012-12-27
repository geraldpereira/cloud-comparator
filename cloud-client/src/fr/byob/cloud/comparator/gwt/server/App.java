package fr.byob.cloud.comparator.gwt.server;


/**
 * Hello world!
 *
 */
public class App {
//	public static String getProvider() throws JsonProcessingException, IOException {
//		// Should specifiy we want json here
//
//		final byte[] pwd = Base64.encode("cloud-rest-user:niancat");
//		final String value = new String(pwd);
//
//		final Client client = Client.create();
//
//		client.addFilter(new ClientFilter() {
//			@Override
//			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
//				final ClientResponse response = getNext().handle(cr);
//				return response;
//			}
//		});
//		final WebResource webResource = client.resource("http://94.23.242.144:10080/cloud-rest/api/v1.0");
//		// If we want to add an auth header :
//		// http://blogs.oracle.com/enterprisetechtips/entry/consuming_restful_web_services_with
//		//		webResource.header("authorization", Base64.encode("tomcat:tomcat"));
//		final String message = webResource.path("provider/list").header("authorization", "Basic " + value).get(
//				String.class);
//
//		//		String message = webResource.get(String.class);
//		final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//		// Skip the container object and go directly to its array named contact
//		final JsonNode rootNode = mapper.readTree(message);
//		final JsonNode contactsNode = rootNode.get("contact");
//		// Read the list of contacts
//		//		ProviderDTO provider = null;
//		//		if (contactsNode.isArray()) {
//		//			final List<ProviderDTO> contacts = mapper.readValue(contactsNode, new TypeReference<List<ProviderDTO>>() {
//		//			});
//		//			provider = contacts.get(0);
//		//			System.out.println(contacts);
//		//		} else {
//		//			provider = mapper.readValue(contactsNode, ProviderDTO.class);
//		//			System.out.println(provider);
//		//		}
//		//		return provider;
//		return "TODO";
//
//	}
}
