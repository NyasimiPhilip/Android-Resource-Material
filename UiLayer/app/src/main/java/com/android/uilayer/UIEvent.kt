package com.android.uilayer

sealed class UIEvent{
    data class ShowMessage(val message:String):UIEvent()
}
