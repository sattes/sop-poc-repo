package com.sp3.mvc.helper;

import java.text.ParseException;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import com.sp3.mvc.enums.CardTypeEnum;
import com.sp3.mvc.enums.DebtStatusEnum;
import com.sp3.mvc.enums.PaymentStatusEnum;
import com.sp3.mvc.models.Address;
import com.sp3.mvc.models.Category;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.Discount;
import com.sp3.mvc.models.Order;
import com.sp3.mvc.models.Payment;
import com.sp3.mvc.models.Product;

public class JaxbHelper {
	
	public static com.sp3.mvc.jaxb.Address getJaxbAddress(Address address) {
		com.sp3.mvc.jaxb.Address jaxbAddress = new com.sp3.mvc.jaxb.Address();
		jaxbAddress.setAddr1(address.getAddress1());
		jaxbAddress.setAddr2(address.getAddress2());
		jaxbAddress.setAddtype(address.getAddressType().toString());
		jaxbAddress.setCity(address.getCity());
		jaxbAddress.setCountry(address.getCountry());
		jaxbAddress.setId(address.getAddressId());
		jaxbAddress.setPhone(address.getPhone());
		jaxbAddress.setState(address.getState());
		jaxbAddress.setZip(address.getZip());
		return jaxbAddress;
	}
	
	public static com.sp3.mvc.jaxb.Address getJaxbAddress(Address address, Customer customer) {
		com.sp3.mvc.jaxb.Address jaxbAddress = getJaxbAddress(address);
		jaxbAddress.setCustomer(getJaxbCustomer(customer));
		return jaxbAddress;
	}
	
	public static com.sp3.mvc.jaxb.Customer getJaxbCustomer(Customer customer) {
		com.sp3.mvc.jaxb.Customer jaxbCustomer = new com.sp3.mvc.jaxb.Customer();
		jaxbCustomer.setCusttype(customer.getCustType().toString());
		jaxbCustomer.setEmail(customer.getEmail());
		jaxbCustomer.setFirstname(customer.getFname());
		jaxbCustomer.setLastname(customer.getLname());
		jaxbCustomer.setPassword(customer.getPassword());
		jaxbCustomer.setStatus(customer.getStatus().toString());
		jaxbCustomer.setUserid(customer.getUserName());
		jaxbCustomer.setEnabled(customer.getEnabled().toString());
		jaxbCustomer.setCustomerRole(getJaxbCustomerRole(customer));
		return jaxbCustomer;
	}
	
	public static com.sp3.mvc.jaxb.CustomerRole getJaxbCustomerRole(Customer customer) {
		com.sp3.mvc.jaxb.CustomerRole jaxbCustomerRole = new com.sp3.mvc.jaxb.CustomerRole();
		jaxbCustomerRole.setRole(customer.getRole().getRoleName());
		jaxbCustomerRole.setRoleId(customer.getRole().getRoleId());
		return jaxbCustomerRole;
	}
	
	public static com.sp3.mvc.jaxb.Order getJaxbOrder(Order order) {
		com.sp3.mvc.jaxb.Order jaxbOrder = new com.sp3.mvc.jaxb.Order();
		jaxbOrder.setId(order.getOrderId());
		jaxbOrder.setOrderdate(DateUtils.getFormattedDateStr(order.getOrderDate()));
		jaxbOrder.setStatus(order.getStatus().toString());
		jaxbOrder.setTotalprice(order.getTotalPrice());
		return jaxbOrder;
	}
	
	public static com.sp3.mvc.jaxb.Payment getJaxbPayment(Payment payment) throws ParseException, DatatypeConfigurationException {
		com.sp3.mvc.jaxb.Payment jaxbPayment = new com.sp3.mvc.jaxb.Payment();
		jaxbPayment.setStatus(PaymentStatusEnum.PENDING.toString());
		jaxbPayment.setTotalamount(payment.getPaymentAmount());
		jaxbPayment.setPaymentdate(DateUtils.getFormattedDateStr(payment.getPaymentDate()));
		return jaxbPayment;
	}
	
	public static com.sp3.mvc.jaxb.Transaction getJaxbTransaction(Payment payment) throws ParseException, DatatypeConfigurationException {
		com.sp3.mvc.jaxb.Transaction jaxbTransaction = new com.sp3.mvc.jaxb.Transaction();
		jaxbTransaction.setTransamount(payment.getPaymentAmount());
		jaxbTransaction.setTransdate(DateUtils.getFormattedDateStr(payment.getPaymentDate()));
		jaxbTransaction.setTranstype(payment.getTxnType().toString());
		return jaxbTransaction;
	}
	
	public static com.sp3.mvc.jaxb.Cardinfo getJaxbCardinfo(Payment payment) throws ParseException, DatatypeConfigurationException {
		com.sp3.mvc.jaxb.Cardinfo jaxbCardinfo = new com.sp3.mvc.jaxb.Cardinfo();
		jaxbCardinfo.setCardcvvnum(payment.getCardInfo().getCvvNumber());
		jaxbCardinfo.setCardgatewaytype(payment.getGatewayType().toString());
		if(payment.getTxnType().toString().equals("CreditCard")) {
			jaxbCardinfo.setCardtype(CardTypeEnum.CREDITCARD.toString());
		}
		if(payment.getTxnType().toString().equals("DebitCard")) {
			jaxbCardinfo.setCardtype(CardTypeEnum.DEBITCARD.toString());
		}
		jaxbCardinfo.setNameoncard(payment.getCardInfo().getNameOnCard());
		jaxbCardinfo.setCardnum(DateUtils.convertLongToBigInt(payment.getCardInfo().getCardNumber()));
		jaxbCardinfo.setExpdate(payment.getCardInfo().getExpiryYear().toString()+"-"+payment.getCardInfo().getExpiryMonth());
		return jaxbCardinfo;
	}
	
	public static com.sp3.mvc.jaxb.Directdebit getJaxbDirectdebit(Payment payment) throws ParseException, DatatypeConfigurationException {
		com.sp3.mvc.jaxb.Directdebit jaxbDirectdebit = new com.sp3.mvc.jaxb.Directdebit();
		if(payment.getTxnType().toString().equals("ECS")) {
			jaxbDirectdebit.setAccholdername(payment.getDirectDebit().getAccHolderName());
			jaxbDirectdebit.setAccnum(payment.getDirectDebit().getAccNumber());
			jaxbDirectdebit.setAcctype(payment.getDirectDebit().getAccType());
			jaxbDirectdebit.setBankbranch(payment.getDirectDebit().getBranchName());
			jaxbDirectdebit.setBankname(payment.getDirectDebit().getBankName());
			jaxbDirectdebit.setDebtamount(payment.getPaymentAmount()/payment.getDirectDebit().getDebtFrequency());
			jaxbDirectdebit.setDebtdate(DateUtils.getFormattedDateStr(new Date()));
			jaxbDirectdebit.setDebtfrequency(payment.getDirectDebit().getDebtFrequency());
			jaxbDirectdebit.setDebtstatus(DebtStatusEnum.DEBITED.toString());
			jaxbDirectdebit.setIfsccode(payment.getDirectDebit().getIfscCode());
		}
		if(payment.getTxnType().toString().equals("NetBanking")) {
			jaxbDirectdebit.setAccholdername(payment.getDirectDebit().getAccHolderName());
			jaxbDirectdebit.setAccnum(payment.getDirectDebit().getAccNumber());
			jaxbDirectdebit.setAcctype(payment.getDirectDebit().getAccType());
			jaxbDirectdebit.setBankbranch(payment.getDirectDebit().getBranchName());
			jaxbDirectdebit.setBankname(payment.getSelectedNetBankName());
			jaxbDirectdebit.setDebtamount(payment.getPaymentAmount());
			jaxbDirectdebit.setDebtdate(DateUtils.getFormattedDateStr(new Date()));
			jaxbDirectdebit.setIfsccode(payment.getDirectDebit().getIfscCode());
			jaxbDirectdebit.setDebtfrequency(0);
			jaxbDirectdebit.setDebtstatus(DebtStatusEnum.DEBITED.toString());
		}
		
		return jaxbDirectdebit;
	}
	
	public static com.sp3.mvc.jaxb.Discount getJaxbDiscount(Discount discount) {
		com.sp3.mvc.jaxb.Discount jaxbDiscount = new com.sp3.mvc.jaxb.Discount();
		jaxbDiscount.setDiscpercent(discount.getDiscPercent());
		jaxbDiscount.setDisctype(discount.getDiscType());
		jaxbDiscount.setId(discount.getDiscId());
		return jaxbDiscount;
	}
	
	public static com.sp3.mvc.jaxb.Category getJaxbCategory(Category category) {
		com.sp3.mvc.jaxb.Category jaxbCategory = new com.sp3.mvc.jaxb.Category();
		jaxbCategory.setId(category.getCatId());
		jaxbCategory.setDesc(category.getDescription());
		jaxbCategory.setName(category.getName());
		return jaxbCategory;
	}
	
	public static com.sp3.mvc.jaxb.Product getJaxbProduct(Product product) {
		com.sp3.mvc.jaxb.Product jaxbProduct = new com.sp3.mvc.jaxb.Product();
		jaxbProduct.setDesc(product.getDescription());
		jaxbProduct.setName(product.getName());
		jaxbProduct.setId(product.getProductId());
		jaxbProduct.setUnitcost(product.getUnitCost());
		return jaxbProduct;
	}
	
	/*public static com.sp3.mvc.jaxb.Orderitem getJaxbProduct(OrderItem orderItem) {
		com.sp3.mvc.jaxb.Orderitem jaxbOrderitem = new com.sp3.mvc.jaxb.Orderitem();
		jaxbProduct.setDesc(product.getDescription());
		jaxbProduct.setName(product.getName());
		jaxbProduct.setId(product.getProductId());
		jaxbProduct.setUnitcost(product.getUnitCost());
		return jaxbOrderitem;
	}*/

}
