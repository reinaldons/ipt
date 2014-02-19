#!/bin/bash
TERMINAL_CMD="${1-xfce4-terminal --tab --drop-down -e}"

open_tab() {

 CMD="bash -c 'V_CMD; bash'"
 CMD_PARSED=`echo $CMD | sed "s#V_CMD#$2/$1#"`

 read -p "[$TERMINAL_CMD \"$CMD_PARSED\"] (press ENTER to confirm)"
 $TERMINAL_CMD "$CMD_PARSED"

}
