import org.junit.Rule
import junit.framework.Assert.assertExists
import org.junit.Test

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    //compiler可以分辨這個Test是給設備orlocal
    @Test
    fun calculate_20_percent_tip() {
        //這邊的code應該會和
        // MainActivity.kt 檔案中 onCreate() 方法的設定內容中
        // 所編寫的程式碼類似(也就是 -> 設定UI內容啦!)
        composeTestRule.setContent {
            TipTimeComposeTheme {
                TipTimeScreen()
            }
        }
        //設定好就可以開始互動， 一樣要先找到節點
        //UI 元件可透過 composeTestRule 以節點的形式存取
        // 常見的做法是使用 onNodeWithText()方法
        // 來存取含有特定文字的節點。
        composeTestRule.onNodeWithText("Cost of Service").performTextInput("10")
        //呼叫 performTextInput() 方法
        // 並傳入您想輸入以填滿 TextField 可組合項的文字。
        composeTestRule.onNodeWithText("Tip (%)").performTextInput("20")
        composeTestRule.onNodeWithText("Tip Amount: $2.00").assertExists()
    }
}