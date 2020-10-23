package uk.gov.hmrc.screens

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import uk.gov.hmrc.R

class TestHomeScreen : Screen<TestHomeScreen>() {
    val textView = KTextView {
        withId(R.id.text_view)
    }

    val primaryButton = KButton {
        withId(R.id.primary_button)
    }
}