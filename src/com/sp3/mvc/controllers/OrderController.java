package com.sp3.mvc.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sp3.mvc.dao.AddressDao;
import com.sp3.mvc.dao.CategoryDao;
import com.sp3.mvc.dao.CustomerDao;
import com.sp3.mvc.dao.CustomerRoleDao;
import com.sp3.mvc.dao.DBUtils;
import com.sp3.mvc.dao.DiscountDao;
import com.sp3.mvc.dao.OrderDao;
import com.sp3.mvc.dao.OrderItemDao;
import com.sp3.mvc.dao.ProductDao;
import com.sp3.mvc.enums.AddressTypeEnum;
import com.sp3.mvc.enums.OrderItemStatusEnum;
import com.sp3.mvc.enums.OrderStatusEnum;
import com.sp3.mvc.helper.AMQPMessageHelper;
import com.sp3.mvc.helper.DateUtils;
import com.sp3.mvc.helper.JaxbHelper;
import com.sp3.mvc.helper.MessageHelper;
import com.sp3.mvc.models.Address;
import com.sp3.mvc.models.Category;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.CustomerRole;
import com.sp3.mvc.models.Discount;
import com.sp3.mvc.models.Order;
import com.sp3.mvc.models.OrderItem;
import com.sp3.mvc.models.Product;

@Controller
@SessionAttributes("caps")
public class OrderController {
	
	private static Logger logger = Logger.getLogger(OrderController.class);
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	@Resource(name = "ordDao")
	private OrderDao ordDao;
	
	@Resource(name = "itemDao")
	private OrderItemDao itemDao;
	
	@Resource(name = "addrDao")
	private AddressDao addrDao;
	
	@Resource(name = "custDao")
	private CustomerDao custDao;
	
	@Resource(name = "prodDao")
	private ProductDao prodDao;
	
	@Resource(name = "catDao")
	private CategoryDao catDao;
	
	@Resource(name = "discDao")
	private DiscountDao discDao;
	
	@Resource(name = "roleDao")
	private CustomerRoleDao roleDao;
	
	private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
	
	@RequestMapping(value="/submitorder", method = RequestMethod.POST)
	public String submitCart(@ModelAttribute("order")Order order, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {
		logger.debug("Inside OrderController::submitCart method...");
		
		logger.debug("Shipping Address - "+order.getShippingAddress());
		
		return doSubmitCart(order,request);
		
	}
	
	@RequestMapping(value="/backtoproducts", method = RequestMethod.POST)
	public String doBack(HttpServletRequest request) {
		logger.debug("Inside OrderController::doBack method...");
		
		return "viewProducts";
	}
	
	public String doSubmitCart(Order order, HttpServletRequest request) throws SQLException, ClassNotFoundException, ParseException {
		logger.debug("Inside OrderController::doSubmitCart method...");
		Set<Product> selectedProducts = (HashSet<Product>)request.getSession().getAttribute("selectedProducts");
		Customer login = (Customer)request.getSession().getAttribute("login");
		
		logger.debug("getEmail "+login.getEmail());
		logger.debug("getFname "+login.getFname());
		logger.debug("getCustType "+login.getCustType());
		logger.debug("getUserName "+login.getUserName());
		
		Integer itemId = itemDao.getMaxItemId();
		Integer addrId = addrDao.getMaxAddressId();
		String orderId = ordDao.getMaxOrderId();
		
		if(orderId == null || orderId.equals("")) {
			orderId = "ORDER-100";
		} else {
			String[] arr = orderId.split("-");
			int maxId = Integer.parseInt(arr[1]);
			orderId = "ORDER-"+(maxId+1);
		}
		logger.debug("itemId - "+itemId);
		logger.debug("addrId - "+addrId);
		logger.debug("OrderId - "+orderId);
		
		order.getShippingAddress().setAddressId(addrId+1);
		order.getShippingAddress().setAddressType(AddressTypeEnum.SHIPPING);
		order.getShippingAddress().setUserId(login.getUserName());
		order.setAddressId(order.getShippingAddress().getAddressId());
		order.setOrderDate(new Date());
		order.setOrderId(orderId);
		order.setStatus(OrderStatusEnum.ORDERED);
		order.setUserId(login.getUserName());
		order.setTotalPrice(0.0d);
		
		//Enable for Local DB - Start
		/*Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			addrDao.insertAddress(conn, order.getShippingAddress());
			ordDao.insertOrder(conn, order);
			
			for(Product selectedProd : selectedProducts){
				itemId = itemId + 1;
				logger.debug("ProductId - "+selectedProd.getProductId()+", Category - "+selectedProd.getCategory()+", Quantity - "+selectedProd.getQuantity()+", UnitPrice - "+selectedProd.getUnitCost());
				OrderItem item  = new OrderItem();
				item.setItemId(itemId);
				//item.setListPrice(listPrice);
				item.setOrderId(order.getOrderId());
				item.setProductId(selectedProd.getProductId());
				item.setQuantity(selectedProd.getQuantity());
				item.setStatus(OrderItemStatusEnum.ORDERED.toString());
				item.setUnitPrice(selectedProd.getUnitCost());
				itemDao.insertOrderItem(conn, item);
				order.getOrderItemList().add(item);
			}
			
			conn.commit();
		} catch (SQLException e) {
			logger.error("SQLException got from CustomerDao in insertCustomer(customer)", e);
			conn.rollback();
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from CustomerDao in insertCustomer(customer)", e);
			throw e;
		} catch(Exception e) {
			logger.error("Exception occured in doSubmitCart method", e);
			conn.rollback();
		} finally {
			DBUtils.closeConnection(conn);
		}*/
		//Enable for Local DB - End
		
		Discount discount = discDao.getDiscountByCustType(login.getCustType().toString());
		com.sp3.mvc.jaxb.Discount disc = JaxbHelper.getJaxbDiscount(discount);
		
		com.sp3.mvc.jaxb.Order jaxbOrder = new com.sp3.mvc.jaxb.Order();
		
		for(Product selectedProd : selectedProducts){
			itemId = itemId + 1;
			logger.debug("ProductId - "+selectedProd.getProductId()+", Category - "+selectedProd.getCategory()+", Quantity - "+selectedProd.getQuantity()+", UnitPrice - "+selectedProd.getUnitCost());
			OrderItem item  = new OrderItem();
			item.setItemId(itemId);
			//item.setListPrice(listPrice);
			item.setOrderId(order.getOrderId());
			item.setProductId(selectedProd.getProductId());
			item.setQuantity(selectedProd.getQuantity());
			item.setStatus("Available");
			item.setUnitPrice(selectedProd.getUnitCost());
			order.getOrderItemList().add(item);
			
			com.sp3.mvc.jaxb.Orderitem jaxbItem = new com.sp3.mvc.jaxb.Orderitem();
			
			Category category = catDao.getCategoryByCatId(selectedProd.getCategory());
			com.sp3.mvc.jaxb.Category cat = JaxbHelper.getJaxbCategory(category);
			
			Product product = prodDao.getProductByProdId(selectedProd.getProductId());
			com.sp3.mvc.jaxb.Product prod = JaxbHelper.getJaxbProduct(product);
			prod.setCategory(cat);
			
			jaxbItem.setDiscount(disc);
			jaxbItem.setId(itemId);
			jaxbItem.setQuantity(selectedProd.getQuantity());
			
			jaxbItem.setProduct(prod);
			jaxbOrder.getOrderitem().add(jaxbItem);
		}
		
		jaxbOrder.setId(order.getOrderId());
		jaxbOrder.setStatus(order.getStatus().toString());
		jaxbOrder.setTotalprice(0.0d);
		jaxbOrder.setOrderdate(DateUtils.getFormattedDateStr(order.getOrderDate()));
		
		com.sp3.mvc.jaxb.Address shippingAddress = JaxbHelper.getJaxbAddress(order.getShippingAddress());
		
		Customer cust = custDao.getCustomerByUserId(order.getUserId());
		CustomerRole custRole = roleDao.getRoleByRoleId(cust.getRoleId());
		cust.setRole(custRole);
		com.sp3.mvc.jaxb.Customer customer = JaxbHelper.getJaxbCustomer(cust);
		
		shippingAddress.setCustomer(customer);
		jaxbOrder.setAddress(shippingAddress);
		String textMessage = getMarshalledString(jaxbOrder);
		
		textMessage = textMessage.replaceAll("ns2:", "");
		textMessage = textMessage.replaceAll("ns3:", "");
		textMessage = textMessage.replaceAll("ns4:", "");
		textMessage = textMessage.replaceAll("ns5:", "");
		textMessage = textMessage.replaceAll("ns6:", "");
		textMessage = textMessage.replaceAll("ns7:", "");
		textMessage = textMessage.replaceAll("ns8:", "");
		
		String exchangeName = myProps.getProperty("exchange.name");
		String qName = myProps.getProperty("order.qname");
		String ipAddress = myProps.getProperty("ip.address");
		AMQPMessageHelper helper = new AMQPMessageHelper();
		helper.sendMessage(textMessage,exchangeName, qName,ipAddress);
		
		request.getSession().setAttribute("orderId", orderId);
		return "orderInfo";
	}
	
	private String getMarshalledString(com.sp3.mvc.jaxb.Order jaxbOrder) {
		String textMessage = null;
		
		try {
			final StringWriter out = new StringWriter();
			marshaller.marshal(jaxbOrder, new StreamResult(out));
			textMessage = out.toString();
		} catch (IOException e) {
			logger.error("IOException while marshalling Order object ", e);
		} 
		
		return textMessage;
	}

}
