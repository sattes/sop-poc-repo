package com.sp3.mvc.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sp3.mvc.dao.AddressDao;
import com.sp3.mvc.dao.CardInfoDao;
import com.sp3.mvc.dao.CategoryDao;
import com.sp3.mvc.dao.CustomerDao;
import com.sp3.mvc.dao.CustomerRoleDao;
import com.sp3.mvc.dao.DirectDebitDao;
import com.sp3.mvc.dao.DiscountDao;
import com.sp3.mvc.dao.OrderDao;
import com.sp3.mvc.dao.OrderItemDao;
import com.sp3.mvc.dao.PaymentDao;
import com.sp3.mvc.dao.ProductDao;
import com.sp3.mvc.dao.TransactionDao;
import com.sp3.mvc.enums.PaymentStatusEnum;
import com.sp3.mvc.helper.AMQPMessageHelper;
import com.sp3.mvc.helper.JaxbHelper;
import com.sp3.mvc.helper.MessageHelper;
import com.sp3.mvc.models.Address;
import com.sp3.mvc.models.Category;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.CustomerRole;
import com.sp3.mvc.models.Discount;
import com.sp3.mvc.models.Order;
import com.sp3.mvc.models.OrderItem;
import com.sp3.mvc.models.Payment;
import com.sp3.mvc.models.Product;

@Controller
public class PaymentController {
	
	private static Logger logger = Logger.getLogger(PaymentController.class);
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	@Resource(name = "ordDao")
	private OrderDao ordDao;
	
	@Resource(name = "addrDao")
	private AddressDao addrDao;
	
	@Resource(name = "custDao")
	private CustomerDao custDao;
	
	@Resource(name = "payDao")
	private PaymentDao payDao;
	
	@Resource(name = "cardDao")
	private CardInfoDao cardDao;
	
	@Resource(name = "debitDao")
	private DirectDebitDao debitDao;
	
	@Resource(name = "txnDao")
	private TransactionDao txnDao;
	
	@Resource(name = "itemDao")
	private OrderItemDao itemDao;
	
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
	
	@RequestMapping(value="/gotopayment", method = RequestMethod.POST)
	public String goToPayment(@ModelAttribute("payment")Payment payment, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("PaymentController::goToPayment Start...");
		
		String orderId = (String)request.getSession().getAttribute("orderId");
		Order order = ordDao.getOrderByOrderId(orderId);
		request.getSession().setAttribute("order", order);
		payment.setOrderId(orderId);
		payment.setPaymentAmount(order.getTotalPrice());
		payment.setPaymentDate(new Date());
		request.getSession().setAttribute("payment", payment);
		logger.debug("PaymentController::goToPayment End...");
		return "payment";
	}
	
	@RequestMapping(value="/backtoviewcart", method = RequestMethod.POST)
	public String doBack(@ModelAttribute("order")Order order) {
		logger.debug("PaymentController::doBack Start");
		return "viewCart";
	}
	
	@RequestMapping(value="/paynow", method = RequestMethod.POST)
	public String payNow(@ModelAttribute("payment")Payment payment, Model model, HttpServletRequest request) {
		logger.debug("PaymentController::payNow Start...");
		logger.debug("PaymentId = "+payment.getPaymentId());
		logger.debug("Payment Mode(Transaction Type) = "+payment.getTxnType());
		logger.debug("PaymentAmount = "+payment.getPaymentAmount());
		
		logger.debug("GatewayType = "+payment.getGatewayType());
		logger.debug("TxnType = "+payment.getTxnType());
		logger.debug("Cardtype = "+payment.getCardType());
		
		String returnPage = null;
		
		Payment pay = (Payment)request.getSession().getAttribute("payment");
		payment.setPaymentAmount(pay.getPaymentAmount());
		payment.setOrderId(pay.getOrderId());
		payment.setPaymentDate(pay.getPaymentDate());
		
		request.getSession().setAttribute("payment", payment);
		if(payment.getTxnType().toString().equals("DebitCard") ||payment.getTxnType().toString().equals("CreditCard")) {
			returnPage = "credit_card_pay";
		} else if(payment.getTxnType().toString().equals("ECS")) {
			returnPage = "ecs_pay";
		} else if(payment.getTxnType().toString().equals("NetBanking")) {
			returnPage = "net_banking_pay";
		} else if(payment.getTxnType().toString().equals("CashOnDelivery")){
			returnPage = "cash_on_delivery_pay";
		}
		logger.debug("PaymentController::payNow End...");
		return returnPage;
	}
	
	@RequestMapping(value="/makepayment", method = RequestMethod.POST)
	public String makePayment(@ModelAttribute("payment")Payment payment, HttpServletRequest request) throws SQLException, ClassNotFoundException, ParseException, DatatypeConfigurationException{
		logger.debug("PaymentController::makePayment Start...");
		
		logger.debug("PaymentId = "+payment.getPaymentId());
		
		logger.debug("PaymentAmount = "+payment.getPaymentAmount());
		
		logger.debug("GatewayType = "+payment.getGatewayType());
		logger.debug("TxnType = "+payment.getTxnType());
		logger.debug("Cardtype = "+payment.getCardType());
		
		Payment pay = (Payment)request.getSession().getAttribute("payment");
		payment.setPaymentAmount(pay.getPaymentAmount());
		payment.setOrderId(pay.getOrderId());
		payment.setPaymentDate(pay.getPaymentDate());
		payment.setGatewayType(pay.getGatewayType());
		payment.setTxnType(pay.getTxnType());
		payment.setSelectedNetBankName(pay.getSelectedNetBankName());
		
		Set<Product> selectedProducts = (HashSet<Product>)request.getSession().getAttribute("selectedProducts");
		Customer login = (Customer)request.getSession().getAttribute("login");
		
		com.sp3.mvc.jaxb.Payment jaxbPayment = null;
		try {
			jaxbPayment = getJaxbPayment(payment,selectedProducts, login);
		} catch (SQLException e) {
			logger.error("",e);
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("",e);
			throw e;
		} catch (ParseException e) {
			logger.error("",e);
			throw e;
		} catch (DatatypeConfigurationException e) {
			logger.error("",e);
			throw e;
		}
		
		String textMessage = getMarshalledString(jaxbPayment);
		
		String exchangeName = myProps.getProperty("exchange.name");
		String qName = myProps.getProperty("payment.qname");
		String ipAddress = myProps.getProperty("ip.address");
		AMQPMessageHelper helper = new AMQPMessageHelper();
		helper.sendMessage(textMessage,exchangeName, qName,ipAddress);
		
		logger.debug("PaymentController::makePayment End...");
		return "paymentInfo";
	}
	
	private String getMarshalledString(com.sp3.mvc.jaxb.Payment jaxbPayment) {
		String textMessage = null;
		
		try {
			final StringWriter out = new StringWriter();
			marshaller.marshal(jaxbPayment, new StreamResult(out));
			textMessage = out.toString();
		} catch (IOException e) {
			logger.error("IOException while marshalling Order object ", e);
		} 
		
		return textMessage;
	}
	
	private com.sp3.mvc.jaxb.Payment getJaxbPayment(Payment payment,Set<Product> selectedProducts, Customer login) throws SQLException, ClassNotFoundException, ParseException, DatatypeConfigurationException {
		String paymentId = payDao.getMaxPaymentId();
		
		if(paymentId == null || paymentId.equals("")) {
			paymentId = "PAYMENT-100";
		} else {
			String[] arr = paymentId.split("-");
			int maxId = Integer.parseInt(arr[1]);
			paymentId = "PAYMENT-"+(maxId+1);
		}
		payment.setPaymentId(paymentId);
		
		com.sp3.mvc.jaxb.Cardinfo jaxbCard = null;
		if(payment.getTxnType().toString().equals("DebitCard") ||payment.getTxnType().toString().equals("CreditCard")) {
			String cardInfoId = cardDao.getMaxCardId();
			
			if(cardInfoId == null || cardInfoId.equals("")) {
				cardInfoId = "CARD-100";
			} else {
				String[] arr = cardInfoId.split("-");
				int maxId = Integer.parseInt(arr[1]);
				cardInfoId = "CARD-"+(maxId+1);
			}
			jaxbCard = JaxbHelper.getJaxbCardinfo(payment);
			jaxbCard.setCardinfoid(cardInfoId);
		}
		
		com.sp3.mvc.jaxb.Directdebit jaxbDebit = null;
		if(payment.getTxnType().toString().equals("ECS") || payment.getTxnType().toString().equals("NetBanking")) {
			String debitId = debitDao.getMaxDebitId();
			
			if(debitId == null || debitId.equals("")) {
				debitId = "DEBIT-100";
			} else {
				String[] arr = debitId.split("-");
				int maxId = Integer.parseInt(arr[1]);
				debitId = "DEBIT-"+(maxId+1);
			}
			jaxbDebit = JaxbHelper.getJaxbDirectdebit(payment);
			jaxbDebit.setDebtid(debitId);
		}
		
		String txnId = txnDao.getMaxTxnId();
		
		if(txnId == null || txnId.equals("")) {
			txnId = "TXN-100";
		} else {
			String[] arr = txnId.split("-");
			int maxId = Integer.parseInt(arr[1]);
			txnId = "TXN-"+(maxId+1);
		}

		com.sp3.mvc.jaxb.Payment jaxbPayment = JaxbHelper.getJaxbPayment(payment);
		
		com.sp3.mvc.jaxb.Transaction jaxbTxn = JaxbHelper.getJaxbTransaction(payment);
		
		jaxbPayment.setPaymentid(paymentId);
		jaxbPayment.setStatus(PaymentStatusEnum.PENDING.toString());
		
		Order order = ordDao.getOrderByOrderId(payment.getOrderId());
		com.sp3.mvc.jaxb.Order jaxbOrder = JaxbHelper.getJaxbOrder(order);
		
		List<OrderItem> items = itemDao.getItemsByOrderId(payment.getOrderId());
		for(OrderItem item : items) {
			com.sp3.mvc.jaxb.Orderitem jaxbOrderitem = new com.sp3.mvc.jaxb.Orderitem();
			jaxbOrderitem.setId(item.getItemId());
			jaxbOrderitem.setQuantity(item.getQuantity());
			com.sp3.mvc.jaxb.Discount disc = new com.sp3.mvc.jaxb.Discount();
			Discount discount = discDao.getDiscountByCustType(login.getCustType().toString());
			disc.setDiscpercent(discount.getDiscPercent());
			disc.setDisctype(discount.getDiscType());
			disc.setId(discount.getDiscId());
			
			com.sp3.mvc.jaxb.Product prod = new com.sp3.mvc.jaxb.Product();
			Product prod2 = prodDao.getProductByProdId(item.getProductId());
			com.sp3.mvc.jaxb.Category cat = new com.sp3.mvc.jaxb.Category();
			Category category = catDao.getCategoryByCatId(prod2.getCategory());
			cat.setId(category.getCatId());
			cat.setDesc(category.getDescription());
			cat.setName(category.getName());
			prod.setCategory(cat);
			
			Product product = prodDao.getProductByProdId(item.getProductId());
			prod.setDesc(product.getDescription());
			prod.setName(product.getName());
			prod.setId(product.getProductId());
			prod.setUnitcost(product.getUnitCost());
			
			jaxbOrderitem.setProduct(prod);
			jaxbOrderitem.setDiscount(disc);
			jaxbOrder.getOrderitem().add(jaxbOrderitem);
		}
		Address address = addrDao.getAddressByAddrId(order.getAddressId());
		com.sp3.mvc.jaxb.Address jaxbAddress = JaxbHelper.getJaxbAddress(address);
		Customer customer = custDao.getCustomerByUserId(address.getUserId());
		CustomerRole custRole = roleDao.getRoleByRoleId(customer.getRoleId());
		customer.setRole(custRole);
		jaxbAddress.setCustomer(JaxbHelper.getJaxbCustomer(customer));
		jaxbOrder.setAddress(jaxbAddress);
		jaxbPayment.setOrder(jaxbOrder);
		if(payment.getTxnType().toString().equals("DebitCard") ||payment.getTxnType().toString().equals("CreditCard")) {
			jaxbTxn.setCardinfo(jaxbCard);
		}
		if(payment.getTxnType().toString().equals("ECS") || payment.getTxnType().toString().equals("NetBanking")) {
			jaxbTxn.setDirectdebit(jaxbDebit);
		}
		jaxbTxn.setPaymentid(paymentId);
		jaxbTxn.setTransactionid(txnId);
		jaxbPayment.getTransaction().add(jaxbTxn);
		return jaxbPayment;
	}

}
