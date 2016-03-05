package com.yzd.android.mcs_phone.udp.InitData;

import android.content.Context;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.other.AirCondition;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;

/**
 * Created by Administrator on 2015/8/12.
 */
public class StringMerge {

    private static StringMerge instances;
    private StringMerge(){}
    public static StringMerge getInstances() {
        if (null == instances) {
            synchronized(StringMerge.class) {
                if (null == instances) {
                    instances = new StringMerge();
                }
            }
        }
        return instances;
    }

    public String readProjectorNum(Context context) {

        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        int position = (int) SPUtils.get(context, Constants.SELECTORROOM, 0);
        if (boardRoomMachineCode != null) {
            String macCode = boardRoomMachineCode.getMachineCode().get(position).getMacCode();
            int i = Integer.parseInt(macCode);
            String s = Integer.toHexString(i);
            return s;
        }


        return "";
    }


    /**
     * 场景模式的控制数据拼接
     * @param situationMode
     * @param mode
     * @return
     */
    public String  situationControl(Context context,int situationMode, int mode){
        String msg = UdpSend.SITUATION_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.SITUATION_CONTROL_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "0600000000";
        switch (mode) {
            case 0:
                msg = msg + "0" + situationMode;
                break;
            case 1:
                if (situationMode == Constants.OFFMODE) {
                    msg = msg + "0" + situationMode;
                } else {
                    msg = msg + "8" + situationMode;
                }
                break;
        }
        return msg + CRC16.getInstances().ccr16(msg);
    }

    /**
     * 获取场景的状态
     * @return
     */
    public String getSituation(Context context){
        String msg = UdpSend.GET_SITUATION_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.GET_SITUATION_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "01";
        return msg;
    }


    /**
     * 获取所有设备的状态
     * @return
     */
    public String getAllEquipMentStatus(Context context){
        String msg = UdpSend.GET_EQUIPMENT_STATUS_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.GET_EQUIPMENT_STATUS_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "01" ;

        return msg + CRC16.getInstances().ccr16(msg);
    }

    /**
     * 灯光的控制
     * @param position
     * @param tag
     * @return
     */
    public String lightControl(Context context,int position, boolean tag){

        String msg = UdpSend.LIGHT_CONTROL_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.LIGHT_CONTROL_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "03" ;

//        if (position >= 10) {
//            msg = msg + position;
//        } else {
//            msg = msg + "0" + position;
//        }

        String s = Integer.toHexString(position);
        if (s.length() < 2) {
            s = "0" + s;
        }
        msg = msg + s;

        if (tag) {
            msg = msg + "01";// 开
        } else {
            msg = msg + "00";
        }

        return msg + CRC16.getInstances().ccr16(msg);
    }

    /**
     * 空调控制命令
     * @param equip
     * @param position
     * @param airCondition
     * @return
     */
    public String airConditionControl(Context context,String equip,String position, AirCondition airCondition){

        String s = ByteMerge.AirConditionMerge(airCondition);
        String msg = UdpSend.INFRARED_CONTROL_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.INFRARED_CONTROL_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "04"
                + equip
                + position
                + s;

        return msg + CRC16.getInstances().ccr16(msg);
    }

    /**
     * 红外控制
     * @param equip
     * @param position
     * @param orderCode
     * @return
     */
    public String infrafedControl(Context context,String equip,String position,String orderCode){
        String msg = UdpSend.INFRARED_CONTROL_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.INFRARED_CONTROL_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "04"
                + equip
                + position
                + orderCode;

        return msg + CRC16.getInstances().ccr16(msg);
    }

    public String curtainControl(Context context,String channel, String status){

        String orderCode = ByteMerge.Curtain(channel, status);
        String msg = UdpSend.CURTAIN_CONTROL_FRAME_LENGTH
                + UdpSend.HMIS
                + UdpSend.TARGET_EQUIPMENT
                + UdpSend.SOURCE_EQUIPMENT
                + UdpSend.MESSAGE_NUM
                + UdpSend.PROJECT_NUM
                + UdpSend.TARGET_MODULE
                + UdpSend.CURTAIN_CONTROL_ORDER_CODE
                + UdpSend.SEND_ASK
                + readProjectorNum(context)
                + "02"
                + orderCode;
        return msg + CRC16.getInstances().ccr16(msg);
    }


}
