package io.vividcode.webflux.functionalstarter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FunctionalStarterApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void calculate() {
		assertThat(doCalculate("add", "3", "5")).isEqualTo("8");
		assertThat(doCalculate("subtract", "3", "5")).isEqualTo("-2");
		assertThat(doCalculate("multiply", "3", "5")).isEqualTo("15");
		assertThat(doCalculate("divide", "9", "3")).isEqualTo("3");
	}

	private String doCalculate(final String operator, final String operand1, final String operand2) {
		return this.restTemplate.getForObject(String.format("/calculator?operator=%s&v1=%s&v2=%s", operator, operand1, operand2), String.class);
	}
}
