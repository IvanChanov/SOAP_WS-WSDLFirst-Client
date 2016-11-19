package wsdlProjectWSClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.freemonkeys.productorderschema.Order;
import com.freemonkeys.productorderschema.OrdersRequest;
import com.freemonkeys.productorderschema.OrdersResponse;
import com.freemonkeys.productorderschema.Product;
import com.freemonkeysws.CustomerOrdersPortType;
import com.freemonkeysws.CustomerOrdersService;

public class ClientWS {

	public static void main(String[] args) {
	
		try {
			CustomerOrdersService service = new CustomerOrdersService(new URL("http://localhost:8080/wsdlProjectWS/services/customerOrders?wsdl"));
			CustomerOrdersPortType port = service.getCustomerOrderPort();
			
			OrdersRequest request = new OrdersRequest();
			request.setCustomerID(1);
			
			OrdersResponse reseponse = port.getOrders(request);
			
			List<Order> orders = reseponse.getOrder();
			
			
			for(Order or : orders)
			{
				List<Product> products = or.getProduct();
				for(Product product : products)
				{	
					
					System.out.println("-------------------");
					System.out.println("Order ID : " + or.getId());
					System.out.println("Order payment method : " + or.getPaymentMethod().getCreditCard().getCardtype());
					System.out.println("Order shipping company : " + or.getShippingCompany().getName());
					System.out.println("Order shipping speed : " + or.getShippingCompany().getDeliverySpeed());
					System.out.println();
	
					System.out.println("-------------------");
					System.out.println("ProductID : " + product.getId());
					System.out.println("Product name : " + product.getName());
					System.out.println("Product description : " + product.getDescription());
					System.out.println("Quantity : " + product.getQuantity());
				}
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
