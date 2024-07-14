//package nl.nl0e0.orchestrationamqp.contract.consumer;
//
//import nl.nl0e0.orchestrationamqp.OrchestrationAmqpApplication;
//import nl.nl0e0.orchestrationamqp.contract.TestConfig;
//import nl.nl0e0.orchestrationamqp.service.AmqpReceiver;
//import org.assertj.core.api.BDDAssertions;
//import org.awaitility.Awaitility;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.stubrunner.StubTrigger;
//import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
//import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.RabbitMQContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.time.Duration;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, OrchestrationAmqpApplication.class}, properties = "stubrunner.amqp.mockConnection=false")
//@AutoConfigureStubRunner(ids = "nl.nl0e0:Appointment-Amqp", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@Testcontainers
//@ActiveProfiles("test")
//public class ApplicationTests {
//
//
//	@Container
//	static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3-management")
//			.withExposedPorts(5672, 15672);  // 暴露 AMQP 和管理界面的端口
////	@Container static RabbitMQContainer rabbit = new RabbitMQContainer();
//
//	@DynamicPropertySource
//	static void rabbitProperties(DynamicPropertyRegistry registry) {
//		rabbit.start();
//		registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
//		registry.add("spring.rabbitmq.management.port", () -> rabbit.getMappedPort(15672));
//	}
//
//	@Autowired
//	StubTrigger trigger;
//	@Autowired
//	AmqpReceiver amqpReceiver;
//	@Autowired
//	private RabbitTemplate template;
//
////	@Test
////	public void contextLoads() {
////		this.trigger.trigger("send_message_to_orchestrator");
////
//////		template.convertAndSend("createAppointmentExchange", "createAppointment", new CreateAppointmentDTO());
////
////		// 打印实际映射的管理界面端口
////		System.out.println("RabbitMQ Management Interface is available at http://localhost:" + rabbit.getMappedPort(15672));
////
////		Awaitility.await().atMost(Duration.ofMinutes(10)).untilAsserted(() -> {
////			BDDAssertions.then(this.amqpReceiver.medicalStore).isNotNull();
////			BDDAssertions.then(this.amqpReceiver.medicalStore.getOwnerId()).isEqualTo(1);
////		});
////
////	}
//}
//
//
