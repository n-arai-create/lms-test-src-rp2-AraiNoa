package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms/");
		assertEquals("ログイン | LMS", webDriver.getTitle());
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		WebElement id = webDriver.findElement(By.id("loginId"));
		id.clear();
		id.sendKeys("StudentAA01");
		WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("StudentAA01test");
		WebElement login = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		login.click();

		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		List<WebElement> status = webDriver.findElements(By.className("w10per"));
		for (int i = 0; i < status.size(); i++) {
			List<WebElement> st = webDriver.findElements(By.className("w10per"));
			String text = st.get(i).getText();
			if ("未提出".equals(text)) {
				System.out.println("未提出");

				WebElement detail = webDriver.findElement(By.cssSelector(".btn.btn-default[value='詳細']"));
				detail.click();

				assertEquals("セクション詳細 | LMS", webDriver.getTitle());

				getEvidence(new Object() {

				});
				break;
			}
		}
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		WebElement insert = webDriver.findElement(By.cssSelector(".btn.btn-default[value='提出済み日報【デモ】を確認する']"));
		insert.click();

		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		WebElement detail = webDriver.findElement(By.id("content_0"));
		detail.clear();
		detail.sendKeys("理解する。");
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));
		submit.click();

		WebElement insert = webDriver.findElement(By.cssSelector(".btn.btn-default[value='提出済み日報【デモ】を確認する']"));

		assertEquals("提出済み日報【デモ】を確認する", insert.getAttribute("value"));

		getEvidence(new Object() {
		});
	}
}
