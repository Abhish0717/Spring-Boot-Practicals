package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.HotelDTO;
import com.rays.form.HotelForm;
import com.rays.service.HotelServiceInt;

@RestController
@RequestMapping(value = "Hotel")
public class HotelCtl extends BaseCtl<HotelForm, HotelDTO, HotelServiceInt> {

	@Autowired
	private HotelServiceInt hotelService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<HotelDTO> list = hotelService.search(new HotelDTO(), userContext);
		res.addResult("hotelList", list);
		return res;
	}
}
