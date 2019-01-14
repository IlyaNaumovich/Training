package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends BasePage {

	@FindBys({@FindBy(className = "search-page")})
	private List<WebElement> search_result_blocks;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSearchResultBlockExist() {
		return search_result_blocks.size()>0;
	}

	public String getSearchResultBlockText() {
		return search_result_blocks.get(0).getText();
	}
}
