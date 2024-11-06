package de.jensklingenberg.scratch3.common

import kotlinx.serialization.Serializable

@Serializable
internal class OpCode(val value: String) {
    companion object {
        val sound_changevolumeby = "sound_changevolumeby"
        val sound_cleareffects = "sound_cleareffects"
        val looks_seteffectto = "looks_seteffectto"
        val sensing_of_object_menu = "sensing_of_object_menu"
        val sensing_of = "sensing_of"
        val control_repeat_until = "control_repeat_until"
        val event_whengreaterthan = "event_whengreaterthan"
        val data_insertatlist = "data_insertatlist"
        val sound_play = "sound_play"
        val operator_not = "operator_not"
        val operator_and = "operator_and"
        val looks_size = "looks_size"
        val operator_mathop = "operator_mathop"
        val operator_contains = "operator_contains"
        val motion_pointtowards = "motion_pointtowards"
        val motion_turnright = "motion_turnright"
        val motion_turnleft = "motion_turnleft"
        val control_create_clone_of_menu = "control_create_clone_of_menu"
        val motion_changeyby = "motion_changeyby"
        val sensing_dayssince2000 = "sensing_dayssince2000"
        val sound_volume = "sound_volume"
        val motion_xposition = "motion_xposition"
        val motion_yposition = "motion_yposition"
        val motion_direction = "motion_direction"
        val sensing_distanceto = "sensing_distanceto"
        val sensing_distancetomenu = "sensing_distancetomenu"
        val data_lengthoflist = "data_lengthoflist"
        val data_deletealloflist = "data_deletealloflist"
    }
}