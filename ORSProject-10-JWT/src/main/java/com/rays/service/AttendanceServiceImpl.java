package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.AttendanceDAOInt;
import com.rays.dto.AttendanceDTO;

@Service
@Transactional
public class AttendanceServiceImpl extends BaseServiceImpl<AttendanceDTO, AttendanceDAOInt> implements AttendanceServiceInt {

}
