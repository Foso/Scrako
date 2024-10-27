package de.jensklingenberg.scratch.common

import kotlinx.serialization.Serializable

@Serializable
internal class OpCode(val value: String) {
    companion object {
        val sound_changeeffectby = "sound_changeeffectby"
        val sound_stopallsounds = "sound_stopallsounds"
        val sound_cleareffects = "sound_cleareffects"
        val looks_costume = "looks_costume"
        val looks_seteffectto = "looks_seteffectto"
        val looks_changeeffectby = "looks_changeeffectby"
        val sensing_of_object_menu = "sensing_of_object_menu"
        val sensing_of = "sensing_of"
        val control_repeat_until = "control_repeat_until"
        val event_whengreaterthan = "event_whengreaterthan"
        val event_whenthisspriteclicked = "event_whenthisspriteclicked"
        val sensing_keyoptions = "sensing_keyoptions"
        val sensing_keypressed = "sensing_keypressed"
        val data_hideList = "data_hidelist"
        val data_showlist = "data_showlist"
        val data_listcontainsitem = "data_listcontainsitem"
        val data_insertatlist = "data_insertatlist"
        val data_hidevariable = "data_hidevariable"
        val data_showvariable = "data_showvariable"
        val operator_mod = "operator_mod"
        val operator_join = "operator_join"
        val operator_round = "operator_round"
        val looks_setsizeto = "looks_setsizeto"
        val looks_nextbackdrop = "looks_nextbackdrop"
        val looks_backdrop = "looks_backdrop"
        val looks_nextcostume = "looks_nextcostume"
        val motion_goto_menu = "motion_goto_menu"
        val motion_goto = "motion_goto"
        val sound_play = "sound_play"
        val sensing_coloristouchingcolor = "sensing_coloristouchingcolor"
        val control_start_as_clone = "control_start_as_clone"
        val sensing_touchingcolor = "sensing_touchingcolor"
        val operator_or = "operator_or"
        val operator_not = "operator_not"
        val operator_and = "operator_and"
        val control_wait_until = "control_wait_until"
        val looks_size = "looks_size"
        val sensing_mousey = "sensing_mousey"
        val sensing_mousex = "sensing_mousex"
        val sensing_mousedown = "sensing_mousedown"
        val sensing_username = "sensing_username"
        val sensing_loudness = "sensing_loudness"
        val sensing_timer = "sensing_timer"
        val sensing_resettimer = "sensing_resettimer"
        val operator_mathop = "operator_mathop"
        val motion_glidesecstoxy = "motion_glidesecstoxy"
        val motion_setrotationstyle = "motion_setrotationstyle"
        val operator_contains = "operator_contains"
        val argument_reporter_boolean = "argument_reporter_boolean"
        val argument_reporter_string_number = "argument_reporter_string_number"
        val motion_pointtowards_menu = "motion_pointtowards_menu"
        val motion_pointtowards = "motion_pointtowards"
        val motion_pointindirection = "motion_pointindirection"
        val motion_turnright = "motion_turnright"
        val motion_turnleft = "motion_turnleft"
        val motion_ifonedgebounce = "motion_ifonedgebounce"
        val control_create_clone_of = "control_create_clone_of"
        val control_create_clone_of_menu = "control_create_clone_of_menu"
        val operator_random = "operator_random"
        val event_broadcastandwait = "event_broadcastandwait"
        val looks_gotofrontback = "looks_gotofrontback"
        val looks_switchcostumeto = "looks_switchcostumeto"
        val motion_changexby = "motion_changexby"
        val motion_changeyby = "motion_changeyby"
        val sensing_dayssince2000 = "sensing_dayssince2000"
        val operator_letter_of = "operator_letter_of"
        val procedures_call = "procedures_call"
        val procedures_definition = "procedures_definition"
        val procedures_prototype = "procedures_prototype"
        val data_changevariableby = "data_changevariableby"
        val data_setvariableto = "data_setvariableto"
        val sound_volume = "sound_volume"
        val motion_xposition = "motion_xposition"
        val motion_yposition = "motion_yposition"
        val motion_direction = "motion_direction"
        val looks_thinkforsecs = "looks_thinkforsecs"
        val sensing_distanceto = "sensing_distanceto"
        val sensing_distancetomenu = "sensing_distancetomenu"
        val data_lengthoflist = "data_lengthoflist"
        val data_itemoflist = "data_itemoflist"
        val motion_movesteps = "motion_movesteps"
        val control_stop: String = "control_stop"
        val ControlWait = "control_wait"
        val Whenflagclicked = ("event_whenflagclicked")
        val LooksSay = "looks_say"
        val LooksThink = "looks_think"
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
        val data_deletealloflist = "data_deletealloflist"
        val control_delete_this_clone = "control_delete_this_clone"
        val data_replaceitemoflist = "data_replaceitemoflist"
        val data_itemnumoflist = "data_itemnumoflist"
    }
}