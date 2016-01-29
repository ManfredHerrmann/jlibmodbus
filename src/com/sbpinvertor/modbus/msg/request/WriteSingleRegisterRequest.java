package com.sbpinvertor.modbus.msg.request;

import com.sbpinvertor.modbus.Modbus;
import com.sbpinvertor.modbus.ModbusFunction;
import com.sbpinvertor.modbus.exception.ModbusNumberException;
import com.sbpinvertor.modbus.msg.base.AbstractDataRequest;
import com.sbpinvertor.modbus.net.stream.base.ModbusInputStream;
import com.sbpinvertor.modbus.net.stream.base.ModbusOutputStream;

import java.io.IOException;

/**
 * Copyright (c) 2015-2016 JSC "Zavod "Invertor"
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
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Authors: Vladislav Y. Kochedykov, software engineer.
 * email: vladislav.kochedykov@gmail.com
 */
public class WriteSingleRegisterRequest extends AbstractDataRequest {

    private int value;

    public WriteSingleRegisterRequest(int serverAddress) throws ModbusNumberException {
        super(serverAddress);
    }

    public WriteSingleRegisterRequest(int serverAddress, int startAddress, int value) throws ModbusNumberException {
        super(serverAddress, startAddress);

        if (!Modbus.checkRegisterValue(value)) {
            throw new ModbusNumberException("Register value out of range", value);
        }

        this.value = value;
    }

    @Override
    protected void readPDU(ModbusInputStream fifo) throws IOException {
        value = fifo.readShortBE();
    }

    @Override
    public ModbusFunction getFunction() {
        return ModbusFunction.WRITE_SINGLE_REGISTER;
    }

    @Override
    public void writeData(ModbusOutputStream fifo) throws IOException {
        fifo.writeShortBE(value);
    }

    @Override
    protected int dataSize() {
        return 2;
    }
}