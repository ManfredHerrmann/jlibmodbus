package com.sbpinvertor.modbus.data.response;

import com.sbpinvertor.modbus.Modbus;
import com.sbpinvertor.modbus.ModbusFunction;
import com.sbpinvertor.modbus.data.base.AbstractWriteResponse;
import com.sbpinvertor.modbus.data.base.ModbusMessage;
import com.sbpinvertor.modbus.exception.ModbusNumberException;

/**
 * Copyright (c) 2015 JSC "Zavod "Invertor"
 * [http://www.sbp-invertor.ru]
 * <p/>
 * This file is part of JLibModbus.
 * <p/>
 * JLibModbus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Authors: Vladislav Y. Kochedykov, software engineer.
 * email: vladislav.kochedykov@gmail.com
 */
public class WriteMultipleRegistersResponse extends AbstractWriteResponse {

    public WriteMultipleRegistersResponse(int serverAddress, int startAddress, int register) throws ModbusNumberException {
        super(serverAddress, startAddress, register);
    }

    public WriteMultipleRegistersResponse(ModbusMessage msg) {
        super(msg);
    }

    @Override
    protected boolean checkValue() {
        return Modbus.checkWriteRegisterCount(getValue());
    }

    @Override
    public ModbusFunction getFunction() {
        return ModbusFunction.WRITE_MULTIPLE_REGISTERS;
    }
}