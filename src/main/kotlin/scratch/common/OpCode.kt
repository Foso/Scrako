package me.jens.scratch.common

import kotlinx.serialization.Serializable

@Serializable
class OpCode(val value: String) {
    companion object {
        val motion_movesteps = "motion_movesteps"
        val control_stop: String = "control_stop"
        val ControlWait = "control_wait"
        val Whenflagclicked = ("event_whenflagclicked")
        val LooksSay = "looks_say"
        val looks_show = "looks_show"
        val looks_hide = "looks_hide"
        val control_wait = "control_wait"
        val control_repeat = "control_repeat"
        val control_if = "control_if"
        val event_whenkeypressed = "event_whenkeypressed"
        val event_whenbroadcastreceived = "event_whenbroadcastreceived"
        val event_broadcast = "event_broadcast"
        val control_if_else = "control_if_else"
        val control_forever = "control_forever"
        val looks_sayforsecs = "looks_sayforsecs"
        val sensing_answer = "sensing_answer"
        val sensing_askandwait = "sensing_askandwait"
        val operator_equals = "operator_equals"
        val operator_add = "operator_add"
        val operator_gt = "operator_gt"
        val operator_lt = "operator_lt"
        val operator_subtract = "operator_subtract"
        val operator_multiply = "operator_multiply"
        val operator_divide = "operator_divide"
        val sound_playuntildone = "sound_playuntildone"
        val sound_sounds_menu = "sound_sounds_menu"
        val data_addtolist = "data_addtolist"
        val control_delete_this_clone = "control_delete_this_clone"
    }
}