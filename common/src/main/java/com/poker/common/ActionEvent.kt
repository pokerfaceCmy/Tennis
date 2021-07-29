package com.poker.common

import kotlinx.coroutines.Job

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/12 17:33
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseActionEvent

class ShowLoadingEvent(val job: Job?) : BaseActionEvent()

class ShowLoadingWithoutJobEvent : BaseActionEvent()

class ShowToastEvent(val message: String) : BaseActionEvent()

class DismissLoadingEvent : BaseActionEvent()
