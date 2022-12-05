import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PikabuTest {

    @BeforeAll
    public static void setUp(){
        open("https://pikabu.ru/");
    }


    @ValueSource( strings = {"Горячее",
            "Лучшее",
            "Свежее",
            "Подписки",
            "Сообщества",
            "Блоги",
            "Курсы"})
    @DisplayName("Checking menu buttons from a ValueSource")
    @ParameterizedTest( name = "Checking the presence of a button {0}")
    @Tag("BLOCKER")
    public void pikabuButtonValueSourceTest(String str){
        $(".header-menu").$(byText(str)).shouldBe(Condition.visible);
    }

    @CsvSource( {"Горячее",
            "Лучшее",
            "Свежее",
            "Подписки",
            "Сообщества",
            "Блоги",
            "Курсы"})
    @DisplayName("Checking menu buttons from a CsvSource")
    @ParameterizedTest( name = "Checking the presence of a button {0}")
    @Tag("BLOCKER")
    public void pikabuButtonCSVSourceTest(String str){
        $(".header-menu").$(byText(str)).shouldBe(Condition.visible);
    }

    @CsvFileSource(resources = "/pikabuButton.csv")
    @DisplayName("Checking menu buttons from a CsvFileSource")
    @ParameterizedTest( name = "Checking the presence of a button {0}")
    @Tag("BLOCKER")
    public void pikabuButtonCsvFileSourceTest(String resources){
        $(".header-menu ").$(byText(resources)).shouldHave(Condition.exist);
    }

    static Stream<String> argsProviderFactory(){
        return Stream.of("Горячее",
                "Лучшее",
                "Свежее",
                "Подписки",
                "Сообщества",
                "Блоги",
                "Курсы");
    }

    @DisplayName("Checking menu buttons from a MethodSource")
    @MethodSource("argsProviderFactory")
    @ParameterizedTest( name = "Checking the presence of a button {0}")
    @Tag("BLOCKER")
    public void pikabuButtonMethodSourceTest(String args){
        $(".header-menu ").$(byText(args)).shouldHave(Condition.exist);
    }

}
