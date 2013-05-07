package com.sp3.mvc.helper;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.sp3.mvc.dao.OrderItemDao;

public class AMQPMessageHelper {
	
	private static Logger logger = Logger.getLogger(OrderItemDao.class);

	public void sendMessage(String ...varStrings) {
		String msg = varStrings[0];
		String exchangeName = varStrings[1];
		String qName = varStrings[2];
		String rabbitIp = varStrings[3];
		
		logger.debug("RabbitMQ Properties Start...");
		logger.debug("Message - "+msg);
		logger.debug("Exchange Name - "+exchangeName);
		logger.debug("QueueName - "+qName);
		logger.debug("IP Address - "+rabbitIp);
		logger.debug("RabbitMQ Properties End...");
		
		try {
			CachingConnectionFactory factory = new CachingConnectionFactory();
	        factory.setAddresses(rabbitIp);
	        org.springframework.amqp.rabbit.connection.Connection connection = factory.createConnection();
	        Channel channel = connection.createChannel(false);
	        channel.basicPublish(exchangeName, qName, null, msg.getBytes());
	        channel.close();
	        connection.close();
	        logger.debug("Message Sent...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void receiveMessage(String qName){
		try{
			CachingConnectionFactory factory = new CachingConnectionFactory();
	        factory.setAddresses("10.30.135.103:5672,10.30.135.101:5672");
	        org.springframework.amqp.rabbit.connection.Connection connection = factory.createConnection();
	        Channel channel = connection.createChannel(false);

       // channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //ring queueName = channel.queueDeclare().getQueue();
        //channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(qName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
		}catch(Exception e ){
			e.printStackTrace();
		}

	}

	/*public static void main(String args[]) {
		AMQPMessageHelper msgHelper = new AMQPMessageHelper();
		msgHelper.sendMessage("Hello World!");
		msgHelper.receiveMessage();
		
	}*/
}
